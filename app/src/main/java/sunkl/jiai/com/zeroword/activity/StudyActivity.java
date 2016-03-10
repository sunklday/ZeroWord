package sunkl.jiai.com.zeroword.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.db.DBManager;

/**
 * 开始学习单词的界面
 */
public class StudyActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<HashMap<String,String>> wordArraylist;
    private TextView textViewWord;
    private TextView textViewMean;
    private TextView textViewExample;
    private int  i;
    private int amount;
    private Button buttonShowMore;
    private Button buttonNextWOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        wordArraylist = new ArrayList<>();
        textViewWord = (TextView) findViewById(R.id.txv_word);
        textViewMean = (TextView) findViewById(R.id.txv_mean);
        textViewExample = (TextView) findViewById(R.id.txv_example);
        buttonNextWOrd = (Button) findViewById(R.id.btn_nextword);
        buttonShowMore = (Button) findViewById(R.id.btn_showmore);
        buttonShowMore.setOnClickListener(this);
        buttonNextWOrd.setOnClickListener(this);
        initData();
    }
    private void initData(){
        i=0;
        amount = getAmount();
        ArrayList<String> listIntent = getIntent().getStringArrayListExtra("data");
        String mark = getWordMark();
        for(String s: listIntent){
            String[] word = s.split("\\*",3);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("word",word[0]);
            hashMap.put("mean",word[1]);
            hashMap.put("example", word[2]);
            if (hashMap.get("word").equals(mark)){
                i=wordArraylist.size();
                System.out.println(i+"-"+mark);
            }
            wordArraylist.add(hashMap);
        }
        setDataTextView();

    }

    private void setDataTextView(){
        textViewWord.setText(wordArraylist.get(i).get("word"));
        textViewMean.setText(wordArraylist.get(i).get("mean"));
        textViewExample.setText(wordArraylist.get(i).get("example"));
        textViewMean.setVisibility(View.INVISIBLE);
        textViewExample.setVisibility(View.INVISIBLE);
    }

    private String getWordMark(){
        String strMark = null;
        DBManager dbManager = new DBManager(StudyActivity.this);
        Cursor cursor = dbManager.userSelect();
        while(cursor.moveToNext()){
            strMark = cursor.getString(cursor.getColumnIndex("wordmark"));
        }
        return strMark;
    }

    private int getAmount(){
        int amount = 20;
        DBManager dbManager = new DBManager(StudyActivity.this);
        Cursor cursor = dbManager.userSelect();
        while(cursor.moveToNext()){
            amount = Integer.parseInt(cursor.getString(cursor.getColumnIndex("amount")));
        }
        return amount;
    }
    private void showMore(){
        if (textViewExample.getVisibility() == View.VISIBLE){
            nextWord();
            return;
        }
        if (textViewMean.getVisibility() == View.VISIBLE){
            textViewExample.setVisibility(View.VISIBLE);
            return;
        }
        if (textViewWord.getVisibility() == View.VISIBLE){
            textViewMean.setVisibility(View.VISIBLE);
        }
    }
    private void nextWord(){
        if(i<amount-1){
            i++;
        }else {
            finish();
        }
      setDataTextView();
    }
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.btn_nextword:
                nextWord();
                break;
            case R.id.btn_showmore:
                showMore();
                break;
        }
    }
    @Override
    public void finish() {
        String word = textViewWord.getText().toString();

        DBManager dbManager = new DBManager(this);
        dbManager.userUpdate(word);
        super.finish();
    }
}
