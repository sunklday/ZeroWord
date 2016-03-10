package sunkl.jiai.com.zeroword.manager;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by admin on 2016/3/10.
 */
public class WordManager {
    private Context context;
    private Usermanager usermanager;
    private GetWordManager getWordManager;
    public WordManager(Context context){
        this.context=context;
        this.usermanager = new Usermanager(context);
        this.getWordManager = new GetWordManager(context);
    }
    public ArrayList getTodayWordList(){
        int amout =usermanager.getuser().getAmount();
        return getWordManager.getTodayWord(amout);

    }
}
