package com.HY.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class DBDao {

    public static final String TABLE_NAME = "PHB";//表名
    public static final String TABLE_NAME2 = "PHB2";//表名

    private static final String MC = "mc";//id名次
    private static final String TIME = "time";//用时
    private static final String DAY = "day";//日期
    private static final String BUSU = "busu";//步数
    private static final String TU = "mosi";//步数
    //DB_Version2增加新字段
    private DBHelper dbHelper;

    //创建表结构
    public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            MC + " integer," +
            TIME + " integer," +
            DAY + " text," +
            BUSU + " text," +
            TU + " text" +
            ")";
    public static final String SQL_CREATE_TABLE2 = "create table " + TABLE_NAME2 + "(" +
            MC + " integer," +
            TIME + " integer," +
            DAY + " text," +
            BUSU + " text," +
            TU + " text" +
            ")";

    private DBDao() {
        dbHelper = new DBHelper(MyApplication.getContext());
    }
    public static DBDao getInstance() {
        return InnerDB.instance;
    }

    private static class InnerDB {
        private static DBDao instance = new DBDao();
    }
    /**
     * 数据库插入数据
     *
     * @param bean 实体类
     * @param <T>  T
     */
    public synchronized <T> void insert(T bean) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if (bean != null && bean instanceof PHB) {
                PHB student = (PHB) bean;
                ContentValues cv = new ContentValues();
                cv.put(MC, student.getMc());
                cv.put(TIME, student.getTime());
                cv.put(DAY, student.getDay());
                cv.put(BUSU, student.getBusu());
                cv.put(TU, student.getMosi());
                db.insert(TABLE_NAME, null, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    public synchronized <T> void insert2(T bean) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if (bean != null && bean instanceof PHB) {
                PHB student = (PHB) bean;
                ContentValues cv = new ContentValues();
                cv.put(MC, student.getMc());
                cv.put(TIME, student.getTime());
                cv.put(DAY, student.getDay());
                cv.put(BUSU, student.getBusu());
                cv.put(TU, student.getMosi());
                db.insert(TABLE_NAME2, null, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    /**
     * 删除表中所有的数据
     */
    public synchronized void clearAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "delete from " + TABLE_NAME;

        try {
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    /**
     * 删除表中所有的数据
     */
    public synchronized void clearAll2() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "delete from " + TABLE_NAME2;

        try {
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    /**
     * 查询数据
     *
     * @return List
     */
    public synchronized <T> List<T> query() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<T> list = new ArrayList<>();
        String querySql = "select * from " + TABLE_NAME;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(querySql, null);
            while (cursor.moveToNext()) {
                PHB student = new PHB();
                student.setMc(cursor.getInt(cursor.getColumnIndex(MC)));
                student.setTime(cursor.getInt(cursor.getColumnIndex(TIME)));
                student.setDay(cursor.getString(cursor.getColumnIndex(DAY)));
                student.setBusu(cursor.getString(cursor.getColumnIndex(BUSU)));
                student.setMosi(cursor.getString(cursor.getColumnIndex(TU)));
                list.add((T) student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return list;
    }
    /**
     * 查询数据
     *
     * @return List
     */
    public synchronized <T> List<T> query2() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<T> list = new ArrayList<>();
        String querySql = "select * from " + TABLE_NAME2;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(querySql, null);
            while (cursor.moveToNext()) {
                PHB student = new PHB();
                student.setMc(cursor.getInt(cursor.getColumnIndex(MC)));
                student.setTime(cursor.getInt(cursor.getColumnIndex(TIME)));
                student.setDay(cursor.getString(cursor.getColumnIndex(DAY)));
                student.setBusu(cursor.getString(cursor.getColumnIndex(BUSU)));
                student.setMosi(cursor.getString(cursor.getColumnIndex(TU)));
                list.add((T) student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return list;
    }
}
