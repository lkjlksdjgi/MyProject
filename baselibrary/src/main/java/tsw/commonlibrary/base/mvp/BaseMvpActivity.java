package tsw.commonlibrary.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tsw.commonlibrary.base.BaseActivity;
import tsw.commonlibrary.utils.ReflexUtil;

public abstract class BaseMvpActivity<P extends BasePresenter, M extends BaseModel> extends BaseActivity {
    protected P mPresenter;
    protected M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = ReflexUtil.getClazz(this, 0);
        mModel = ReflexUtil.getClazz(this, 1);
        if (this instanceof BaseView) {
            mPresenter.attachVM(this, mModel);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachVM();
        }
    }
}
