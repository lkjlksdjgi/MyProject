package tsw.commonlibrary.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tsw.commonlibrary.constants.Constants;

public class RxService {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private HttpLoggingInterceptor interceptor = null;
    private CacheInterceptor cacheInterceptor = null;
    private TrustManagers trustManagers = null;
    private OkHttpClient okHttpClient = null;
    private Retrofit retrofit;

    private RxService() {
        init();
    }

    private static RxService rxService = null;

    public static RxService getInstance() {
        if (rxService == null) {
            synchronized (RxService.class) {
                if (rxService == null) {
                    rxService = new RxService();
                }
            }
        }
        return rxService;
    }

    private void init() {
        interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        cacheInterceptor = new CacheInterceptor();
        trustManagers = new TrustManagers();
        okHttpClient = new OkHttpClient.Builder()
                //SSL证书
//            .sslSocketFactory(TrustManagers.getSafeOkHttpClient(CommonUtils.getContext(), R.raw.demo,"password","alias"))//需要验证证书
//            .hostnameVerifier(TrustManagers.getHostnameVerifier())
                .sslSocketFactory(trustManagers.getUnsafeOkHttpClient())//这是信任所有证书
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                //打印日志
                .addInterceptor(interceptor)
                //设置Cache
                .addNetworkInterceptor(cacheInterceptor)//缓存方面需要加入这个拦截器
                .addInterceptor(cacheInterceptor)
                .cache(HttpCache.getCache())
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public <T> T createApi(Class<T> clazz, String url) {
        Retrofit otherUrlRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return otherUrlRetrofit.create(clazz);
    }

    public void clear() {
        interceptor = null;
        cacheInterceptor = null;
        trustManagers = null;
        okHttpClient = null;
        rxService = null;
        retrofit = null;
    }
}
