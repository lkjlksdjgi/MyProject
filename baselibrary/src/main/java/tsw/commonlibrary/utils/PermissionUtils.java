package tsw.commonlibrary.utils;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理工具类
 */
public class PermissionUtils {

    /**
     * android6.0权限管理。
     * @param requestCode 标识，不解释了
     * @param permissions 权限组的String数组，这里传入的是一个权限组数组，详细传参如{PermissionGroup.STORAGE,PermissionGroup.CALENDAR},基本上这里传一个就好，更多参数请看{@link PermissionGroup}
     * @return {@code true}:都有权限；{@code false}：没有权限。<br/>用户拒绝或者允许之后，系统返回的结果请在{@link FragmentActivity#onRequestPermissionsResult}方法里获取
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean requestPermissions(int requestCode, String[] permissions) {
        //获取当前栈顶Activity
        FragmentActivity fragmentActivity = AppManager.getAppManager().currentActivity();
        if (checkPermissions(fragmentActivity, permissions)) {//都有权限
            return true;
        } else {
            List<String> deniedPermissions = getDeniedPermissions(fragmentActivity, permissions);
            if (deniedPermissions.size() > 0) {
                fragmentActivity.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            }
            return false;
        }
    }


    private static boolean checkPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                for (String aPermission : PermissionGroup.getPermissions(permission)) {
                    if (ContextCompat.checkSelfPermission(context, aPermission) == PackageManager.PERMISSION_DENIED) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                for (String aPermission : PermissionGroup.getPermissions(permission)) {
                    deniedPermissions.add(aPermission);
                }
            }
        }
        return deniedPermissions;
    }


    @SuppressLint("InlinedApi")
    public static class PermissionGroup {
        public static final String CALENDAR = Manifest.permission_group.CALENDAR;
        public static final String CAMERA = Manifest.permission_group.CAMERA;
        public static final String CONTACTS = Manifest.permission_group.CONTACTS;
        public static final String LOCATION = Manifest.permission_group.LOCATION;
        public static final String MICROPHONE = Manifest.permission_group.MICROPHONE;
        public static final String PHONE = Manifest.permission_group.PHONE;
        public static final String SENSORS = Manifest.permission_group.SENSORS;
        public static final String SMS = Manifest.permission_group.SMS;
        public static final String STORAGE = Manifest.permission_group.STORAGE;

        private static final String[] GROUP_CALENDAR = {
                Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR
        };
        private static final String[] GROUP_CAMERA = {
                Manifest.permission.CAMERA
        };
        private static final String[] GROUP_CONTACTS = {
                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS
        };
        private static final String[] GROUP_LOCATION = {
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        };
        private static final String[] GROUP_MICROPHONE = {
                Manifest.permission.RECORD_AUDIO
        };
        private static final String[] GROUP_PHONE = {
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.CALL_PHONE,
                Manifest.permission.ANSWER_PHONE_CALLS, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.ADD_VOICEMAIL, Manifest.permission.USE_SIP, Manifest.permission.PROCESS_OUTGOING_CALLS
        };
        private static final String[] GROUP_SENSORS = {
                Manifest.permission.BODY_SENSORS
        };
        private static final String[] GROUP_SMS = {
                Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH, Manifest.permission.RECEIVE_MMS,
        };
        private static final String[] GROUP_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        public static String[] getPermissions(final String permission) {
            switch (permission) {
                case CALENDAR:
                    return GROUP_CALENDAR;
                case CAMERA:
                    return GROUP_CAMERA;
                case CONTACTS:
                    return GROUP_CONTACTS;
                case LOCATION:
                    return GROUP_LOCATION;
                case MICROPHONE:
                    return GROUP_MICROPHONE;
                case PHONE:
                    return GROUP_PHONE;
                case SENSORS:
                    return GROUP_SENSORS;
                case SMS:
                    return GROUP_SMS;
                case STORAGE:
                    return GROUP_STORAGE;
            }
            return new String[]{permission};
        }
    }
}
