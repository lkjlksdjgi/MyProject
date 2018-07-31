package tsw.commonlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import tsw.commonlibrary.utils.AppManager;
import tsw.commonlibrary.utils.barutils.ImmersionBar;

public abstract class BaseActivity extends AppCompatActivity {

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        AppManager.getAppManager().addActivity(this);
        initView();
    }


    /**
     * 是否可以使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        //这里根据业务逻辑或者根据不同的系统版本去判断是否使用沉浸式
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //在BaseActivity里销毁
        }
    }

    public abstract int getLayoutID();

    public abstract void initView();
}
