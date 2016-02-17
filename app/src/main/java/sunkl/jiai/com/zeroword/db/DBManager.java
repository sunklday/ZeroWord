package sunkl.jiai.com.zeroword.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by admin on 16/2/17.
 */
public class DBManager extends SQLiteOpenHelper {

    /**
     * 常规字段
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_WORD + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_MEAN + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "word.db";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 定义表结构
     */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "word";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_MEAN = "mean";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * 插入
     * @param word 单词
     * @param mean 释义
     * @return
     */
    public long insert(String word,String mean){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FeedEntry.COLUMN_NAME_WORD, word);
        cv.put(FeedEntry.COLUMN_NAME_MEAN, mean);
        long newRowId;
        newRowId = db.insert(
                FeedEntry.TABLE_NAME,
                null,
                cv);
        return newRowId;
    }

    /**
     * sql select *
     * @return
     */
    public Cursor select()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(FeedEntry.TABLE_NAME, null, null, null, null, null,  " _id desc");
        return cursor;
    }

    public void delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String where=FeedEntry._ID+"=?";
        String[] whereValue={Integer.toString(id)};
        db.delete(FeedEntry.TABLE_NAME, where, whereValue);
    }
    public void delete(String word)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String where=FeedEntry.COLUMN_NAME_WORD+"=?";
        String[] whereValue={word};
        db.delete(FeedEntry.TABLE_NAME, where, whereValue);
    }

}