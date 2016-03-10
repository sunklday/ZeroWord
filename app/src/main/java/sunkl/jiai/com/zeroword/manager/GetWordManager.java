package sunkl.jiai.com.zeroword.manager;

import android.content.Context;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import sunkl.jiai.com.zeroword.db.DBManager;
import sunkl.jiai.com.zeroword.model.Word;

/**
 * Created by admin on 2016/3/10.
 */
public class GetWordManager {
    private Context context;
    public GetWordManager(Context context){
        this.context = context;
    }
    public ArrayList<Word> getTodayWord(int amount){
        DBManager db= new DBManager(this.context);
        ArrayList<Word> arrayListWords = new ArrayList<>();
        int i = 0;
        while (i<amount) {
            double d  = Math.random();
            boolean hasRead =false;
            //400为单词数，这边随机找
            int n = (int)(d*400);
            Cursor c=db.selectword(n);
            while (c.moveToNext()){
                String word = c.getString(c.getColumnIndex("word"));
                String mean = c.getString(c.getColumnIndex("mean"));
                String example = c.getString(c.getColumnIndex("example"));
                String _id = c.getString(c.getColumnIndex("_id"));
                String id = String.valueOf(i);
                String date = c.getString(c.getColumnIndex("date"));
                String degree = c.getString(c.getColumnIndex("degree"));
                if(!degree.equals("0")){
                    hasRead = true;
                    break;
                }
                Word words = new Word(id,_id,word,mean,example,degree,date);
              //  updateword(word, i);
                arrayListWords.add(words);
            }
            if (hasRead){
                continue;
            }
            i++;
        }

        return arrayListWords;
    }
    public ArrayList<Word> getReviewWord(){


        return null;
    }
    private void updateword(String word,int i){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        DBManager dbManager = new DBManager(this.context);
        dbManager.updata(word,str,i);
    }
}
