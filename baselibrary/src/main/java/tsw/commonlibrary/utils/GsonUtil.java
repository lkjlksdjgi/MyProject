package tsw.commonlibrary.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GsonUtil {

    /**
     * 转成json
     * @param object :json字符串
     * @return
     */
    public static String gsonToString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * 转成bean
     * @param gsonString :json字符串
     * @param cls ：class类型。如 Bean.class
     * @return
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(gsonString, cls);
    }

    /**
     * 转成list
     * @param gsonString :json字符串
     * @param cls ：数组的class类型。如 Bean[].class
     * @return
     */
    public static <T> List<T> gsonToList(String gsonString, Class<T[]> cls) {
        Gson gson = new Gson();
        T[] list = gson.fromJson(gsonString, cls);
        return Arrays.asList(list);
    }

    /**
     * 转成list中有map的
     * @param gsonString :json字符串
     * @return
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        Gson gson = new Gson();
        List<Map<String, T>> list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {}.getType());
        return list;
    }

    /**
     * 转成map的
     * @param gsonString :json字符串
     * @return
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Gson gson = new Gson();
        Map<String, T> map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {}.getType());
        return map;
    }
}
