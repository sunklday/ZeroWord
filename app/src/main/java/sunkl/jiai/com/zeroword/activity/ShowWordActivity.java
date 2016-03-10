package sunkl.jiai.com.zeroword.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.db.DBManager;
import sunkl.jiai.com.zeroword.manager.GetWordManager;
import sunkl.jiai.com.zeroword.manager.WordManager;
import sunkl.jiai.com.zeroword.model.Word;

/**
 * 进入学习单词前的显示列表
 * 列表将随机选出单词。
 * 并且记录word数据库添加date
 */
public class ShowWordActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewShowWord;
    private Button btnStartStudy;
    private Integer wordNumber;
    private  List<HashMap<String,String>> list;
    private    ArrayList<String> listIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_word);
        listIntent = new ArrayList<>();
        list= new ArrayList<>();
        btnStartStudy = (Button) findViewById(R.id.btn_startStudy);
        btnStartStudy.setOnClickListener(this);
        listViewShowWord = (ListView) findViewById(R.id.lsv_showWord);
        initListView();
    }
    private void initListView(){
        //setData();
        WordManager wordManager= new WordManager(ShowWordActivity.this);
        ArrayList<Word> arrayList = wordManager.getTodayWordList();
        for (Word word:arrayList){
            HashMap<String ,String> hashMap = new HashMap<>();
            hashMap.put("word",word.getWord());
            hashMap.put("mean",word.getMean());
            list.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.show_word_listview,
                //动态数组与ListItem对应的子项
                new String[]{"word", "mean"},

                //ListItem的XML文件里面的两个TextView ID
                new int[]{R.id.textview_danci, R.id.textview_shiyi});
        listViewShowWord.setAdapter(simpleAdapter);

    }
/*    private void setData(){
        DBManager db= new DBManager(ShowWordActivity.this);
        Cursor cursor = db.userSelect();
        while (cursor.moveToNext()){
            wordNumber = Integer.valueOf(cursor.getString(cursor.getColumnIndex("amount")));
        }
        int i = 0;
        while (i<wordNumber) {
            double d  = Math.random();
            int id = (int)(d*400);
            Cursor c=db.selectword(id);
            while (c.moveToNext()){
                String word = c.getString(c.getColumnIndex("word"));
                String mean = c.getString(c.getColumnIndex("mean"));
                String example = c.getString(c.getColumnIndex("example"));
                HashMap<String,String> hashmap = new HashMap<>();
                hashmap.put("word",word);
                hashmap.put("mean", mean);
                list.add(hashmap);
                listIntent.add(word + "*" + mean + "*" + example);
                updateword(word, i);
            }
            i++;
        }
    }*/

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,StudyActivity.class);
        intent.putStringArrayListExtra("data",listIntent);
        startActivity(intent);
    }

    private void updateword(String word,int i){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        DBManager dbManager = new DBManager(ShowWordActivity.this);
        dbManager.updata(word,str,i);
    }
}
