package tsw.commonlibrary.rx;


import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import tsw.commonlibrary.net.CallBackListener;
import tsw.commonlibrary.utils.GsonUtil;


/**
 * 管理RX生命周期，防止内存泄漏
 */
public class RxManager<T> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();// 管理订阅者者
    private ArrayList<CallBackListener<T>> listCallBack = new ArrayList<>();


    public void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    /**
     * @param observable
     * @param callBackListener
     */
    public void add(Observable<T> observable, final CallBackListener<T> callBackListener) {
        Disposable disposable = observable.compose(RxSchedulers.<T>rxSchedulerHelper()).subscribe(new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {
                callBackListener.onSuccess(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                callBackListener.onError(throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                callBackListener.onPre(true);
            }
        });
        compositeDisposable.add(disposable);
        listCallBack.add(callBackListener);
    }

    /**
     * 加一个Class<T> cla 参数，统一处理服务器返回结果时，gson解析会有一个bug。
     * 主要是为了解决 Retrofit 泛型解析遇到com.google.gson.internal.LinkedTreeMap cannot be cast to object的bug
     * @param observable
     * @param cla
     * @param callBackListener
     */
    public void add(Observable<T> observable, final Class<T> cla, final CallBackListener<T> callBackListener) {
        Disposable disposable = observable.compose(RxSchedulers.<T>rxSchedulerHelper()).subscribe(new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {
                Gson gson = new Gson();
                T t1 = GsonUtil.gsonToBean(gson.toJson(t).toString(), cla);
                callBackListener.onSuccess(t1);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                callBackListener.onError(throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                callBackListener.onPre(true);
            }
        });
        compositeDisposable.add(disposable);
        listCallBack.add(callBackListener);
    }


    public void clear() {
        compositeDisposable.dispose();
        compositeDisposable.clear();
        for (CallBackListener<T> callBackListener : listCallBack) {
            callBackListener = null;
        }
        listCallBack.clear();
        listCallBack = null;
        compositeDisposable = null;
    }
}