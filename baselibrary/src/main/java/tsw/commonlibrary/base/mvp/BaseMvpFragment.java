package tsw.commonlibrary.base.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import tsw.commonlibrary.base.BaseFragment;
import tsw.commonlibrary.utils.ReflexUtil;

public abstract class BaseMvpFragment<P extends BasePresenter, M extends BaseModel> extends BaseFragment {
    protected P mPresenter;
    protected M mModel;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = ReflexUtil.getClazz(this, 0);
        mModel = ReflexUtil.getClazz(this, 1);
        if (this instanceof BaseView) {
            mPresenter.attachVM(this, mModel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachVM();
        }
    }
}
