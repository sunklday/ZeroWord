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
import sunkl.jiai.com.zeroword.manager.WordManager;
import sunkl.jiai.com.zeroword.model.Word;

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
    private Button buttonSimple;
    private Button buttonDifficult;

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
        buttonSimple = (Button) findViewById(R.id.btn_simple);
        buttonDifficult = (Button) findViewById(R.id.btn_difficult);
        buttonShowMore.setOnClickListener(this);
        buttonNextWOrd.setOnClickListener(this);
        buttonSimple.setOnClickListener(this);
        buttonDifficult.setOnClickListener(this);
        initData();
    }
    private void initData(){
        i=0;
        amount=getAmount();
        String mark = getWordMark();
        WordManager wordManager= new WordManager(StudyActivity.this);
        ArrayList<Word> arrayList = wordManager.getTodayWordList();
        for (Word word:arrayList){
            System.out.println(word.toString());
            HashMap<String ,String> hashMap = new HashMap<>();
            hashMap.put("word",word.getWord());
            hashMap.put("mean",word.getMean());
            hashMap.put("example",word.getExample());
            if (hashMap.get("word").equals(mark)) {
                i = wordArraylist.size();
                System.out.println(i + "-" + mark);
            }
            wordArraylist.add(hashMap);
        }
       /* amount = getAmount();
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
        }*/

        setDataTextView();

    }

    private void setDataTextView(){
        textViewWord.setText(wordArraylist.get(i).get("word"));
        textViewMean.setText(wordArraylist.get(i).get("mean"));
        textViewExample.setText(wordArraylist.get(i).get("example"));
        textViewMean.setVisibility(View.INVISIBLE);
        textViewExample.setVisibility(View.INVISIBLE);
        buttonDifficult.setVisibility(View.INVISIBLE);
        buttonSimple.setVisibility(View.INVISIBLE);
        buttonShowMore.setVisibility(View.VISIBLE);
        buttonNextWOrd.setVisibility(View.VISIBLE);
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
            buttonSimple.setVisibility(View.VISIBLE);
            buttonDifficult.setVisibility(View.VISIBLE);
            buttonShowMore.setVisibility(View.INVISIBLE);
            buttonNextWOrd.setVisibility(View.INVISIBLE);
            return;
        }else if(i==amount-1){
            buttonNextWOrd.setText("学完啦");
            buttonShowMore.setVisibility(View.INVISIBLE);
            i++;
            return;
        }else {
            finish();
            return;
        }
     // setDataTextView();
    }
    private void  setWordDegree(Integer degree){
        String word = textViewWord.getText().toString();

        DBManager dbManager = new DBManager(this);
        dbManager.userDegreeUpdata(word, degree);
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
            case R.id.btn_simple:
                setWordDegree(3);
                break;
            case R.id.btn_difficult:
                setWordDegree(1);
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
