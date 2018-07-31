package tsw.commonlibrary.utils;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class DevicesInfoUtils {


    /**
     * 获取MAC地址。
     * 在获取MAC地址之前先判断当前WiFi状态，若开启了Wifi，则直接获取MAC地址，若没开启Wifi，则用代码开启Wifi，然后马上关闭，再获取MAC地址。
     *目前此方法测试成功，无论在哪种状态下都能正确取得设备的MAC地址（包括开机后未启动过Wifi的状态下），且在未开启Wifi的状态下，用代码开启Wifi并马上关闭，过程极短，不会影响到用户操作。
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = null;
        WifiManager wifiManager = (WifiManager) CommonUtils.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());
        if (!wifiManager.isWifiEnabled()) {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        if (null != info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }
}
