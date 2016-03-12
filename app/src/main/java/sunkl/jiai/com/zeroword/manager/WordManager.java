package sunkl.jiai.com.zeroword.manager;

import android.content.Context;
import android.database.Cursor;

import java.lang.annotation.ElementType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sunkl.jiai.com.zeroword.db.DBManager;
import sunkl.jiai.com.zeroword.model.User;
import sunkl.jiai.com.zeroword.model.Word;

/**
 * Created by admin on 2016/3/10.
 */
public class WordManager {
    private Usermanager usermanager;
    private GetWordManager getWordManager;
    private Context context;
    public WordManager(Context context){
        this.context=context;
        this.usermanager = new Usermanager(context);
        this.getWordManager = new GetWordManager(context);
    }
    public ArrayList<Word> getTodayWordList(){
        User user = usermanager.getuser();
        int amout =user.getAmount();
        String time = user.getTime();
        String lastDate = DataManager.getDate();
        if (time!=null&&time.equals(lastDate))
        {
            return getWordManager.getLastWord(lastDate);
        }
        else {
            DBManager dbManager = new DBManager(this.context);
            dbManager.dateInsert(lastDate);
            return getWordManager.getTodayWord(amout);
        }
    }
    public ArrayList getDateWordList(String   date){
            return getWordManager.getLastWord(date);
    }

}
