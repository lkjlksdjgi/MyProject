package tsw.commonlibrary.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * 常用的方法工具类
 * 1、获取常用的全局context
 * 2、获取app的VersionName和VersionCode
 * 3、获取Resource
 */
public class CommonUtils {

    private static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

    public static void clear() {
        mApplication = null;
    }

    public static Application getApplication() {
        if (mApplication == null) {
            throw new NullPointerException("mApplication is null,Please initialize the mApplication first");
        }
        return mApplication;
    }

    public static Context getContext() {
        if (mApplication == null) {
            throw new NullPointerException("mApplication is null,Please initialize the mApplication first");
        }
        return mApplication.getApplicationContext();
    }

    /**
     * 获取版本名称
     */
    public static String getAppVersionName() {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = getContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getContext().getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    /**
     * 获取版本号
     */
    public static int getAppVersionCode() {
        int versioncode = -1;
        try {
            // ---get the package info---
            PackageManager pm = getContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getContext().getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String getString(int stringID){
        return getResources().getString(stringID);
    }

    public static float getDimension(int dimenID){
        return getResources().getDimension(dimenID);
    }

    public static float getDimensionPixelSize(int dimenID){
        return getResources().getDimensionPixelSize(dimenID);
    }




    public static int getRecycleViewHeight(RecyclerView mRecyclerView) {
        RecyclerView.Adapter myAdapter = mRecyclerView.getAdapter();
        int height = 0;
        if (myAdapter != null) {
            int size = myAdapter.getItemCount();
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = myAdapter.createViewHolder(mRecyclerView, myAdapter.getItemViewType(i));
                myAdapter.onBindViewHolder(holder, i);
                holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(mRecyclerView.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(),
                        holder.itemView.getMeasuredHeight());
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
                if (layoutParams == null) {
                    height += holder.itemView.getMeasuredHeight();
                } else {
                    int itemMarginTop = layoutParams.topMargin;
                    int itemMarginBottom = layoutParams.bottomMargin;
                    height += holder.itemView.getMeasuredHeight() + itemMarginTop + itemMarginBottom;
                }
            }
        }
        return height;
    }
}
