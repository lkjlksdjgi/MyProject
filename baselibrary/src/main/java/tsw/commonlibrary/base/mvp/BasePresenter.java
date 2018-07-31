package tsw.commonlibrary.base.mvp;
import java.lang.ref.SoftReference;

import tsw.commonlibrary.rx.RxManager;

public abstract class BasePresenter<M, V> {

    protected M mModel;
    protected SoftReference<V> mViewRef;

    public RxManager mRxManager = new RxManager();

    public void attachVM(V v, M m) {
        this.mViewRef = new SoftReference<V>(v);
        this.mModel = m;
    }

    public void detachVM() {
        if (mRxManager != null) {
            mRxManager.clear();
            mRxManager = null;
        }
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected V getView() {
        return mViewRef.get();
    }
}
