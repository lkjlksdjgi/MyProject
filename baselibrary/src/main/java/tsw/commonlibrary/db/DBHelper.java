package tsw.commonlibrary.db;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDaoSession;

import java.util.ArrayList;

public abstract class DBHelper<T> {

    protected AbstractDaoSession mDaoSession;

    protected SQLiteDatabase writableDatabase;
    protected SQLiteDatabase readDatabase;

    public void close() {
        if (writableDatabase != null) {
            writableDatabase.close();
            writableDatabase = null;
        }
        if (readDatabase != null) {
            readDatabase.close();
            readDatabase = null;
        }
    }

    public abstract T getDaoSession();

    /**
     * 插入
     *
     * @param sql      ：sql语句
     * @param bindArgs ：条件参数，不能为空
     *                 sql语句示例：
     *                 第一种 ：String sql = "insert into TEST_DB (name, sex,age) VALUES ('lisiguang','nan',27);";此时后面的bindArgs可以传一个new Object[]{}。
     *                 第二种： String sql = "insert into TEST_DB (name, sex,age) VALUES (？,？,？);";;此时后面的bindArgs传new Object[]{"lisi","nv",32}
     */
    public void insert(String sql, Object[] bindArgs) {
        execSQL(sql, bindArgs);
    }

    /**
     * 删除
     *
     * @param sql      ：sql语句
     * @param bindArgs ：条件参数，不能为空
     *                 sql语句示例：
     *                 第一种 ： String sql = "delete from TEST_DB where age < 30;";此时后面的bindArgs可以传一个new Object[]{}。
     *                 第二种：  String sql = "delete from TEST_DB where age < ?;";此时后面的bindArgs传new Object[]{30}
     */
    public void delete(String sql, Object[] bindArgs) {
        execSQL(sql, bindArgs);
    }

    /**
     * 修改
     *
     * @param sql      ：sql语句
     * @param bindArgs ：条件参数，不能为空
     *                 sql语句示例：
     *                 第一种 ：String sql = "update TEST_DB set age = 25 where name = 'lisi' and sex = 'nan' and age = 21 ";此时后面的bindArgs可以传一个new Object[]{}。
     *                 第二种： String sql = "update TEST_DB set age = ? where name = ? and sex = ? and age = ?";此时后面的bindArgs传new Object[]{2,lisi","nv",56}
     */
    public void update(String sql, Object[] bindArgs) {
        execSQL(sql, bindArgs);
    }

    /**
     * 查询，这个方法只能返回查询语句中需要的第一个字段的值
     *
     * @param sql      ：sql语句
     * @param bindArgs ：条件参数，不能为空
     * @return sql语句示例：
     * 第一种 ：String sql = "select name ,age from TEST_DB where sex = 'nan'";此时后面的bindArgs可以传一个new String[]{}。
     * 第二种：String sql = "select name ,age from TEST_DB where sex = ?";此时后面的bindArgs可以传一个new String[]{"nan"}。
     */
    public ArrayList<String> query(String sql, String[] bindArgs) {
        synchronized (DBHelper.class) {
            Cursor cursor = mDaoSession.getDatabase().rawQuery(sql, bindArgs);
            ArrayList<String> arrayList = new ArrayList<>();
            while (cursor.moveToNext()) {
                arrayList.add(cursor.getString(0));
            }
            cursor.close();
            return arrayList;
        }
    }

    /**
     * 查询，
     *
     * @param sql      ：sql语句
     * @param bindArgs ：条件参数，不能为空
     * @param length   ：查询结果的字段是几个就传几。length只能小于或等于查询字段的个数。
     * @return sql语句示例：
     * 第一种 ：String sql = "select name ,age from TEST_DB where sex = 'nan'";此时后面的bindArgs可以传一个new String[]{}；length传2，因为要查询2个字段。
     * 第二种：String sql = "select name ,age from TEST_DB where sex = ?";此时后面的bindArgs可以传一个new String[]{"nan"}；length传2，因为要查询2个字段。
     */
    public ArrayList<ArrayList<String>> query(String sql, String[] bindArgs, int length) {
        synchronized (DBHelper.class) {
            Cursor cursor = mDaoSession.getDatabase().rawQuery(sql, bindArgs);
            ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
            while (cursor.moveToNext()) {
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    list.add(cursor.getString(i));
                }
                arrayList.add(list);
            }
            cursor.close();
            return arrayList;
        }
    }

    /**
     * 查询，返回Cursor
     *
     * @param sql      ：sql语句
     * @param bindArgs ：条件参数，不能为空
     * @return Cursor
     * sql语句示例：
     * 第一种 ：String sql = "select name ,age from TEST_DB where sex = 'nan'";此时后面的bindArgs可以传一个new String[]{}。
     * 第二种：String sql = "select name ,age from TEST_DB where sex = ?";此时后面的bindArgs可以传一个new String[]{"nan"}。
     */
    public Cursor queryReturnCursor(String sql, String[] bindArgs) {
        Cursor cursor = mDaoSession.getDatabase().rawQuery(sql, bindArgs);
        return cursor;
    }


    public void execSQL(String sql, Object[] bindArgs) {
        synchronized (DBHelper.class) {
            mDaoSession.getDatabase().execSQL(sql, bindArgs);
        }
    }

}
