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
            Cursor cursor=db.selectword(n);
            while (cursor.moveToNext()){
                String word = cursor.getString(cursor.getColumnIndex("word"));
                String mean = cursor.getString(cursor.getColumnIndex("mean"));
                String example = cursor.getString(cursor.getColumnIndex("example"));
                String _id = cursor.getString(cursor.getColumnIndex("_id"));
                Integer id = i;
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String degree = cursor.getString(cursor.getColumnIndex("degree"));
               if(!degree.equals("0")){
                    hasRead = true;
                    break;
                }
                Word words = new Word(id,_id,word,mean,example,degree,date);
                updatewordAndUser(word, i);
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
    public ArrayList<Word> getLastWord(String lastDate){
        DBManager dbManager = new DBManager(this.context);
        ArrayList<Word> arrayListLastWords = new ArrayList<>();
        Cursor cursor = dbManager.selectWordByDate(lastDate);
        while (cursor.moveToNext()){
            String word = cursor.getString(cursor.getColumnIndex("word"));
            String mean = cursor.getString(cursor.getColumnIndex("mean"));
            String example = cursor.getString(cursor.getColumnIndex("example"));
            String _id = cursor.getString(cursor.getColumnIndex("_id"));
            Integer id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("id")));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String degree = cursor.getString(cursor.getColumnIndex("degree"));
            Word words = new Word(id,_id,word,mean,example,degree,date);
            arrayListLastWords.add(words);
        }
        return arrayListLastWords;
    }



    private void updatewordAndUser(String word,int i){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        DBManager dbManager = new DBManager(this.context);
        dbManager.updata(word,str,i);
        dbManager.userTimeUpdata(str);
    }


}
