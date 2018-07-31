package tsw.accountnumber.ui;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import tsw.accountnumber.MyApplication;
import tsw.accountnumber.R;
import tsw.accountnumber.services.DownLoadServices;
import tsw.commonlibrary.base.BaseActivity;
import tsw.commonlibrary.utils.barutils.ImmersionBar;
import tsw.commonlibrary.utils.fileutils.FileIoUtils;
import tsw.commonlibrary.utils.fileutils.FilePathUtils;
import tsw.commonlibrary.utils.fileutils.FileUtils;

public class SplashActivity extends BaseActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable subscribe;
    private Toolbar toolbar;

    @Override
    public int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        ImmersionBar.with(this)
//                .titleBar(R.id.toolbar, false)
//                .transparentBar()
//                .init();
//        mImmersionBar.titleBar(R.id.toolbar).init();
//        mImmersionBar.statusBarColor(R.color.c_transparent).init();
        initData();
    }

    private void initData() {
        //将assets中的那个数据库放到包名缓存下
//        if (!FileUtils.getFileByPath(FilePathUtils.getInAppDbPath(MyApplication.DB_NAME)).exists()) {
//            FileIoUtils.save(FileUtils.getFileByPath(FilePathUtils.getInAppDbPath(MyApplication.DB_NAME)), FileIoUtils.readISFromAssets(MyApplication.DB_NAME), false);
//        }
        //开启下载服务
        startService(new Intent(this, DownLoadServices.class));

        subscribe = Observable.timer(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
        compositeDisposable.add(subscribe);

        //TODO 广告图，这里加上广告图


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.delete(subscribe);
    }
}
