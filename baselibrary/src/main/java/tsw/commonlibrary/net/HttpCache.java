package tsw.commonlibrary.net;
import java.io.File;
import okhttp3.Cache;
import tsw.commonlibrary.utils.CommonUtils;

public class HttpCache {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(CommonUtils.getContext().getCacheDir().getAbsolutePath() + File.separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
