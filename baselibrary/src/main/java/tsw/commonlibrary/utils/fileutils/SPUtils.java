package tsw.commonlibrary.utils.fileutils;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;
import java.util.Set;

import tsw.commonlibrary.utils.CommonUtils;

public final class SPUtils {

    //默认的SharedPreferences文件名
    private final static String SP_Default_Name = "SP";

    /**
     * @param key   键
     * @param value 保存的值
     * @return {@code true}: 保存成功 <br>{@code false}: 保存失败
     */
    public static boolean put(String key, String value) {
        return put(SP_Default_Name, key, value);
    }

    /**
     * @param name  保存的SharedPreferences文件的名字，默认为"SP.xml"
     * @param key   键
     * @param value 保存的值
     * @return {@code true}: 保存成功 <br>{@code false}: 保存失败
     */
    public static boolean put(String name, String key, String value) {
        return getSharedPreferencesEditor(name).putString(key, value).commit();
    }

    public static boolean put(String key, int value) {
        return put(SP_Default_Name, key, value);
    }

    public static boolean put(String name, String key, int value) {
        return getSharedPreferencesEditor(name).putInt(key, value).commit();
    }

    public static boolean put(String key, long value) {
        return put(SP_Default_Name, key, value);
    }


    public static boolean put(String name, String key, long value) {
        return getSharedPreferencesEditor(name).putLong(key, value).commit();
    }

    public static boolean put(String key, boolean value) {
        return put(SP_Default_Name, key, value);
    }

    public static boolean put(String name, String key, boolean value) {
        return getSharedPreferencesEditor(name).putBoolean(key, value).commit();
    }

    public static boolean put(String key, float value) {
        return put(SP_Default_Name, key, value);
    }

    public static boolean put(String name, String key, float value) {
        return getSharedPreferencesEditor(name).putFloat(key, value).commit();
    }

    public static boolean put(String key, Set<String> value) {
        return put(SP_Default_Name, key, value);
    }

    public static boolean put(String name, String key, Set<String> value) {
        return getSharedPreferencesEditor(name).putStringSet(key, value).commit();
    }


    /**
     * @param key          键
     * @param defaultValue 默认值
     * @return 如果查找的键存在，就返回键所对应的值，如果不存在就返回默认值{@code defaultValue}
     */
    public static String getString(String key, String defaultValue) {
        return getString(SP_Default_Name, key, defaultValue);
    }

    public static String getString(String name, String key, String defaultValue) {
        return getSharedPreferences(name).getString(key, defaultValue);
    }


    public static int getInt(String key, int defaultValue) {
        return getInt(SP_Default_Name, key, defaultValue);
    }

    public static int getInt(String name, String key, int defaultValue) {
        return getSharedPreferences(name).getInt(key, defaultValue);
    }


    public static float getFloat(String key, float defaultValue) {
        return getFloat(SP_Default_Name, key, defaultValue);
    }

    public static float getFloat(String name, String key, float defaultValue) {
        return getSharedPreferences(name).getFloat(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return getLong(SP_Default_Name, key, defaultValue);
    }

    public static long getLong(String name, String key, long defaultValue) {
        return getSharedPreferences(name).getLong(key, defaultValue);
    }


    public static boolean getBoolean(String key, boolean defaultValue) {
        return getBoolean(SP_Default_Name, key, defaultValue);
    }

    public static boolean getBoolean(String name, String key, boolean defaultValue) {
        return getSharedPreferences(name).getBoolean(key, defaultValue);
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValue) {
        return getStringSet(SP_Default_Name, key, defaultValue);
    }

    public static Set<String> getStringSet(String name, String key, Set<String> defaultValue) {
        return getSharedPreferences(name).getStringSet(key, defaultValue);
    }

    public static Map<String, ?> getAll() {
        return getAll(SP_Default_Name);
    }

    public static Map<String, ?> getAll(String name) {
        return getSharedPreferences(name).getAll();
    }


    public static boolean contains(String key) {
        return contains(SP_Default_Name, key);
    }

    public static boolean contains(String name, String key) {
        return getSharedPreferences(name).contains(key);
    }

    public static boolean remove(String key) {
        return remove(SP_Default_Name, key);
    }

    public static boolean remove(String name, String key) {
        return getSharedPreferencesEditor(name).remove(key).commit();
    }

    public static boolean clear() {
        return clear(SP_Default_Name);
    }

    public static boolean clear(String name) {
        return getSharedPreferencesEditor(name).clear().commit();
    }

    private static SharedPreferences.Editor getSharedPreferencesEditor(String spName) {
        if (TextUtils.isEmpty(spName)) {
            spName = SP_Default_Name;
        }
        return CommonUtils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences getSharedPreferences(String spName) {
        if (TextUtils.isEmpty(spName)) {
            spName = SP_Default_Name;
        }
        return CommonUtils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }
}
