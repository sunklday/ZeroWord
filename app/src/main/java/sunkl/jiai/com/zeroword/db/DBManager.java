package sunkl.jiai.com.zeroword.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by admin on 16/2/17.
 * 数据库管理
 */
public class DBManager extends SQLiteOpenHelper {


    /**
     * 常规字段
     * text integer 数据库数据类型
     * 逗号是分隔符
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    /**
     * 建立user表sql语句
     */
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + USERTABLE.TABLE_NAME + " (" +
                    USERTABLE._ID + " INTEGER PRIMARY KEY," +
                    USERTABLE.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    USERTABLE.COLUMN_NAME_TIME + TEXT_TYPE + COMMA_SEP +
                    USERTABLE.COLUMN_NAME_WORDMARK + TEXT_TYPE + COMMA_SEP +
                    USERTABLE.COLUMN_NAME_AMOUNT + INTEGER_TYPE +
                    " )";
    /**
     * 建立word表sql语句
     */
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WORDTABLE.TABLE_NAME + " (" +
                    WORDTABLE._ID + " INTEGER PRIMARY KEY," +
                    WORDTABLE.COLUMN_NAME_ENTRY_ID + INTEGER_TYPE + COMMA_SEP +
                    WORDTABLE.COLUMN_NAME_WORD + TEXT_TYPE + COMMA_SEP +
                    WORDTABLE.COLUMN_NAME_MEAN + TEXT_TYPE + COMMA_SEP +
                    WORDTABLE.COLUMN_NAME_EXAMPLE + TEXT_TYPE + COMMA_SEP +
                    WORDTABLE.COLUMN_NAME_DEGREE + INTEGER_TYPE + COMMA_SEP +
                    WORDTABLE.COLUMN_NAME_DATE + TEXT_TYPE +
                    " )";

    /**
     * 建立date表sql语句
     */
    private static final String SQL_CREATE_DATE =
            "CREATE TABLE " + DATETABLE.TABLE_NAME + " (" +
                    DATETABLE._ID + " INTEGER PRIMARY KEY," +
                    DATETABLE.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    DATETABLE.COLUMN_NAME_DATE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WORDTABLE.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "word.db";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 定义word表结构
     * word 单词
     * id 单词的id 暂时不用
     * mean 释义
     * example 例句
     * degree 单词的程度 用来标记单词难度或者复习程度
     */
    public static abstract class WORDTABLE implements BaseColumns {
        public static final String TABLE_NAME = "word";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_MEAN = "mean";
        public static final String COLUMN_NAME_EXAMPLE = "example";
        public static final String COLUMN_NAME_DEGREE = "degree";
        public static final String COLUMN_NAME_DATE = "date";

    }

    /**
     * 定义user表结构
     * user 用户名字
     * time 倒计时用来闹钟
     * wordmark 标记当前背单词的那个单词
     * amount 设置每日背单词的数量
     */
    public static abstract class USERTABLE implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_WORDMARK = "wordmark";
        public static final String COLUMN_NAME_AMOUNT = "amount";
    }
    /**
     * 定义date表结构
     * user 用户名字
     * date 记录日期
     */
    public static abstract class DATETABLE implements BaseColumns {
        public static final String TABLE_NAME = "date";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATE = "date";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_DATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        switch (oldVersion) {
            case 1:
                onCreate(db);
            case 2:
                onCreate(db);
            default:
                break;
        }

    }

    /**
     * 插入
     *
     * @param word 单词
     * @param mean 释义
     * @return
     */
    public long insert(String word, String mean, String example, int degree) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WORDTABLE.COLUMN_NAME_WORD, word);
        cv.put(WORDTABLE.COLUMN_NAME_MEAN, mean);
        cv.put(WORDTABLE.COLUMN_NAME_EXAMPLE, example);
        cv.put(WORDTABLE.COLUMN_NAME_DEGREE, degree);
        long newRowId;
        newRowId = db.insert(
                WORDTABLE.TABLE_NAME,
                null,
                cv);
        return newRowId;
    }

    /**
     * sql select *
     * query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
     *
     * @return
     */
    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WORDTABLE.TABLE_NAME, null, null, null, null, null, " _id desc");
        return cursor;
    }

    /**
     * sql select *
     *
     * @return
     */
    public Cursor select(int amount) {
        String limit = String.valueOf(amount);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WORDTABLE.TABLE_NAME, null, null, null, null, null, " _id desc", limit);
        return cursor;
    }

    /**
     * sql select 10
     *
     * @return
     */
    public Cursor select(int amount, int degree) {
        String limit = String.valueOf(amount);
        String[] selectionArgs = {String.valueOf(degree)};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WORDTABLE.TABLE_NAME, null, "degree=?", selectionArgs, null, null, " _id desc", limit);
        return cursor;
    }

    public Cursor selectword(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(WORDTABLE.TABLE_NAME, null, "_id=?", selectionArgs, null, null, " _id desc");
        return cursor;
    }

    public Cursor selectWordByDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(date)};
        Cursor cursor = db.query(WORDTABLE.TABLE_NAME, null, "date=?", selectionArgs, null, null, " id desc");
        return cursor;
    }
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = WORDTABLE._ID + "=?";
        String[] whereValue = {Integer.toString(id)};
        db.delete(WORDTABLE.TABLE_NAME, where, whereValue);
    }

    public void delete(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = WORDTABLE.COLUMN_NAME_WORD + "=?";
        String[] whereValue = {word};
        db.delete(WORDTABLE.TABLE_NAME, where, whereValue);
    }

    public void update(String word, int degree) {
        String[] whereArgs = {word};
        ContentValues cv = new ContentValues();
        cv.put("degree", String.valueOf(degree));
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(WORDTABLE.TABLE_NAME, cv, "word=?", whereArgs);

    }

    public void updata(String word, String date, int id) {
        String[] whereArgs = {word};
        ContentValues cv = new ContentValues();
        cv.put("date",date);
        cv.put("id", id);
        SQLiteDatabase db =this.getWritableDatabase();
        db.update(WORDTABLE.TABLE_NAME, cv, "word=?", whereArgs);
    }
    public void userDegreeUpdata(String word,Integer degree){
        String[] whereArgs = {word};
        ContentValues cv = new ContentValues();
        cv.put("degree", degree);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(WORDTABLE.TABLE_NAME, cv, "word=?", whereArgs);
    }

    /**
     * user表的操作
     *
     * @param name
     * @param time
     * @param wordmark
     * @return
     */
    public long userInsert(String name, String time, String wordmark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERTABLE.COLUMN_NAME_NAME, name);
        cv.put(USERTABLE.COLUMN_NAME_TIME, time);
        cv.put(USERTABLE.COLUMN_NAME_WORDMARK, wordmark);
        long newRowId;
        newRowId = db.insert(
                USERTABLE.TABLE_NAME,
                null,
                cv);
        return newRowId;
    }

    public void userUpdate(String wordmark) {
        String[] whereArgs = {"sun"};
        ContentValues cv = new ContentValues();
        cv.put("wordmark", wordmark);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(USERTABLE.TABLE_NAME, cv, "name=?", whereArgs);
    }

    public Cursor userSelect() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USERTABLE.TABLE_NAME, null, null, null, null, null, " _id desc");

        return cursor;
    }

    public void userAmountUpdate(String number) {
        String[] whereArgs = {"sun"};
        ContentValues cv = new ContentValues();
        cv.put("amount", number);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(USERTABLE.TABLE_NAME, cv, "name=?", whereArgs);
    }
    public void userTimeUpdata(String time){
        String[] whereArgs = {"sun"};
        ContentValues cv = new ContentValues();
        cv.put("time", time);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(USERTABLE.TABLE_NAME, cv, "name=?", whereArgs);
    }
/**
 * date表的操作
 */
    public Cursor dateSelect(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATETABLE.TABLE_NAME, null, null, null, null, null, null);

        return cursor;
    }

    public long dateInsert(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATETABLE.COLUMN_NAME_NAME, "sun");
        cv.put(DATETABLE.COLUMN_NAME_DATE, date);
        long newRowId;
        newRowId = db.insert(
                DATETABLE.TABLE_NAME,
                null,
                cv);
        return newRowId;
    }
}