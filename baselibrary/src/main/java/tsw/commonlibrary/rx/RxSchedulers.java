package tsw.commonlibrary.rx;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Rx线程相关的切换
 */
public class RxSchedulers {

    /**
     * 统一线程处理，子线程到主线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return rxSchedulerHelper(THREAD_TO_MAIN);
    }

    //子线程到主线程
    public static final int THREAD_TO_MAIN = 0;
    //主线程到子线程
    public static final int MAIN_TO_THREAD = 1;
    //主线程到子线程
    public static final int THREAD_TO_THREAD = 2;

    public static <T> ObservableTransformer<T, T> rxSchedulerHelper(final int flag) {    //compose简化线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<T> upstream) {
                Observable<T> observable = null;
                switch (flag) {
                    case THREAD_TO_MAIN:
                        observable = upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                        break;
                    case MAIN_TO_THREAD:
                        observable = upstream.subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io());
                        break;
                    case THREAD_TO_THREAD:
                        observable = upstream.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
                        break;
                }
                return observable;
            }
        };
    }
}