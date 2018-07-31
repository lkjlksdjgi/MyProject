package tsw.accountnumber;


import android.app.Application;

import tsw.commonlibrary.utils.CommonUtils;

public class MyApplication  extends Application{
    public static final String DB_NAME = "accountnumber.sqlite";
    @Override
    public void onCreate() {
        super.onCreate();
        CommonUtils.init(this);
    }
}
