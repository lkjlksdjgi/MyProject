package tsw.commonlibrary.utils;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import java.util.Stack;
import tsw.commonlibrary.net.RxService;

/**
 * 管理Activity的工具类。入栈和出栈
 */
public class AppManager {
    private Stack<FragmentActivity> activityStack;
    private static AppManager instance = null;
    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if(instance == null){
            synchronized (AppManager.class){
                if(instance == null){
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(FragmentActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<FragmentActivity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public FragmentActivity currentActivity() {
        FragmentActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        FragmentActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(FragmentActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
            if(activityStack.size() == 0){
                activityStack = null;
                instance = null;
                RxService.getInstance().clear();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (FragmentActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        activityStack = null;
        instance = null;
        //退出应用的时候清除相关资源
        RxService.getInstance().clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public boolean isAppExit() {
        return activityStack == null || activityStack.isEmpty();
    }
}
