package sunkl.jiai.com.zeroword.manager;

import sunkl.jiai.com.zeroword.db.DBManager;
import sunkl.jiai.com.zeroword.model.User;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by admin on 2016/3/10.
 */
public class Usermanager {
    private Context centext;
    public Usermanager(Context centext){
        this.centext = centext;
    }

    public User getuser(){
        DBManager dbManager = new DBManager(this.centext);
        Cursor cursor = dbManager.userSelect();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            Integer amount = Integer.valueOf(cursor.getString(cursor.getColumnIndex("amount")));
            String wordmark = cursor.getString(cursor.getColumnIndex("wordmark"));
            return new User(name,_id,time,wordmark,amount);
        }
        return  null;
    }
}
