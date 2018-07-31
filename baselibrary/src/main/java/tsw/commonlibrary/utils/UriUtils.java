package tsw.commonlibrary.utils;


import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * URI相关的工具类
 */
public class UriUtils {

    /**
     * 获取文件的uri路径
     *
     * @param file 文件
     * @return 文件的uri
     */
    public static Uri getUriForFile(final File file) {
        if (file == null) return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = CommonUtils.getContext().getPackageName() + ".utilcode.provider";
            return FileProvider.getUriForFile(CommonUtils.getContext(), authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
