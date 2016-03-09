package sunkl.jiai.com.zeroword.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import sunkl.jiai.com.zeroword.R;

public class StudyActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<String> listIntent;
    private ArrayList<HashMap<String,String>> wordArraylist;
    private TextView textViewWord;
    private TextView textViewmean;
    private TextView textViewexample;
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
        textViewmean = (TextView) findViewById(R.id.txv_mean);
        textViewexample = (TextView) findViewById(R.id.txv_example);
        buttonNextWOrd = (Button) findViewById(R.id.btn_nextword);
        buttonShowMore = (Button) findViewById(R.id.btn_showmore);
        buttonShowMore.setOnClickListener(this);
        buttonNextWOrd.setOnClickListener(this);

        initData();
    }
    private void initData(){
        i = getWordMark();
        amount = getAmount();
        listIntent = getIntent().getStringArrayListExtra("data");
        for(String s:listIntent){
            String[] word = s.split("\\*",3);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("word",word[0]);
            hashMap.put("mean",word[1]);
            hashMap.put("example",word[2]);
            wordArraylist.add(hashMap);
        }
        setDataTextView();

    }

    private void setDataTextView(){
        textViewWord.setText(wordArraylist.get(i).get("word"));
        textViewmean.setText(wordArraylist.get(i).get("mean"));
        textViewexample.setText(wordArraylist.get(i).get("example"));
        textViewmean.setVisibility(View.INVISIBLE);
        textViewexample.setVisibility(View.INVISIBLE);
    }

    private int getWordMark(){
        int i = 0;

        return i;
    }

    private int getAmount(){
        int amount = 20;
        return amount;
    }
    private void showMore(){
        if (textViewexample.getVisibility() == View.VISIBLE){
            nextWord();
            return;
        }
        if (textViewmean.getVisibility() == View.VISIBLE){
            textViewexample.setVisibility(View.VISIBLE);
            return;
        }
        if (textViewWord.getVisibility() == View.VISIBLE){
            textViewmean.setVisibility(View.VISIBLE);
            return;
        }
    }
    private void nextWord(){
        if(i<amount){
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
}
