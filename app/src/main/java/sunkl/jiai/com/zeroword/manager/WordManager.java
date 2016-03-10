package sunkl.jiai.com.zeroword.manager;

import android.content.Context;

import java.lang.annotation.ElementType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sunkl.jiai.com.zeroword.db.DBManager;
import sunkl.jiai.com.zeroword.model.User;

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
    public ArrayList getTodayWordList(){
        User user = usermanager.getuser();
        int amout =user.getAmount();
        String time = user.getTime();
        String lastDate = DataManager.getDate();
        if (time!=null&&time.equals(lastDate))
        {
            return getWordManager.getLastWord(lastDate);
        }
        else
            return getWordManager.getTodayWord(amout);

    }
}
