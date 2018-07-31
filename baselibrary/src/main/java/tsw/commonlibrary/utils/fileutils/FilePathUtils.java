package tsw.commonlibrary.utils.fileutils;

import android.os.Environment;
import tsw.commonlibrary.utils.CommonUtils;
/**
 * 常用文件夹路径工具类
 */
public class FilePathUtils {


    /**
     * 获取此应用的包名目录
     * <pre>path: /data/data/package/pre>
     * @return 此应用的包名目录
     */
    public static String getInAppPackagePath() {
        return CommonUtils.getContext().getCacheDir().getParentFile().getAbsolutePath();
    }

    /**
     * 获取此应用的缓存目录
     * <pre>path: /data/data/package/cache</pre>
     * @return 此应用的缓存目录
     */
    public static String getInAppCachePath() {
        return CommonUtils.getContext().getCacheDir().getAbsolutePath();
    }

    /**
     * 获取此应用的文件目录
     * <pre>path: /data/data/package/files</pre>
     * @return 此应用的文件目录
     */
    public static String getInAppFilesPath() {
        return CommonUtils.getContext().getFilesDir().getAbsolutePath();
    }
    /**
     * 获取此应用的数据库文件目录
     * <pre>path: /data/data/package/databases/name</pre>
     * @param name 数据库文件名
     * @return 数据库文件目录
     */
    public static String getInAppDbPath(String name) {
        return CommonUtils.getContext().getDatabasePath(name).getAbsolutePath();
    }

    /**
     * 获取 Android 外置储存的根目录
     * <pre>path: /storage/emulated/0</pre>
     * @return 外置储存根目录
     */
    public static String getExtStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取此应用在外置储存中的包名所在目录
     * <pre>path: /storage/emulated/0/Android/data/package</pre>
     *
     * @return 此应用在外置储存中的缓存目录
     */
    public static String getAppExtPackagePath() {
        return CommonUtils.getContext().getExternalCacheDir().getParentFile().getAbsolutePath();
    }
    /**
     * 获取此应用在外置储存中的缓存目录
     * <pre>path: /storage/emulated/0/Android/data/package/cache</pre>
     *
     * @return 此应用在外置储存中的缓存目录
     */
    public static String getAppExtCachePath() {
        return CommonUtils.getContext().getExternalCacheDir().getAbsolutePath();
    }
    /**
     * 获取此应用在外置储存中的文件目录
     * <pre>path: /storage/emulated/0/Android/data/package/files</pre>
     *
     * @return 此应用在外置储存中的文件目录
     */
    public static String getAppExtDBPath() {
        return  CommonUtils.getContext().getExternalFilesDir(null).getAbsolutePath();
    }

    /**
     * 获取此应用在外置储存中的文件目录
     * <pre>path: /storage/emulated/0/Android/data/package/files</pre>
     *
     * @return 此应用在外置储存中的文件目录
     */
    public static String getAppExtFilePath() {
        return  CommonUtils.getContext().getExternalFilesDir(null).getAbsolutePath();
    }
}
