package sunkl.jiai.com.zeroword.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.adapter.CardsDataAdapter;
import sunkl.jiai.com.zeroword.manager.WordManager;
import sunkl.jiai.com.zeroword.model.Word;
import sunkl.jiai.com.zeroword.wenchao.cardstack.CardStack;

/**
 * 进入学习单词前的显示列表
 * 列表将随机选出单词。
 * 并且记录word数据库添加date
 */
public class ShowWordActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewShowWord;
    private Button btnStartStudy;
    private Integer wordNumber;
    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;

    private    ArrayList<String> listIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_word);
        mCardStack = (CardStack)findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_content);
        mCardStack.setStackMargin(20);

        mCardAdapter = new CardsDataAdapter(getApplicationContext());
        mCardAdapter.add("test1");
        mCardAdapter.add("test2");
        mCardAdapter.add("test3");
        mCardAdapter.add("test4");
        mCardAdapter.add("test5");

        mCardStack.setAdapter(mCardAdapter);

        if(mCardStack.getAdapter() != null) {
            Log.i("MyActivity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }
        listIntent = new ArrayList<>();

        btnStartStudy = (Button) findViewById(R.id.btn_startStudy);
        btnStartStudy.setOnClickListener(this);
        listViewShowWord = (ListView) findViewById(R.id.lsv_showWord);
        initListView();


    }
    private void initListView(){
        SetListView setListView = new SetListView();
        setListView.execute();




    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,StudyActivity.class);
        intent.putStringArrayListExtra("data",listIntent);
        startActivity(intent);
    }
    class SetListView extends AsyncTask<Void, Void, List<HashMap<String, String>>> {

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {
            WordManager wordManager= new WordManager(ShowWordActivity.this);
            ArrayList<Word> arrayList = wordManager.getTodayWordList();
            ArrayList<HashMap<String,String>> list = new ArrayList<>();
            for (Word word:arrayList){
               // System.out.println(word.toString());
                HashMap<String ,String> hashMap = new HashMap<>();
                hashMap.put("word",word.getWord());
                hashMap.put("mean",word.getMean());
                hashMap.put("id", String.valueOf(word.getId()+1));
                list.add(hashMap);
            }
            return list;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            SimpleAdapter simpleAdapter = new SimpleAdapter(ShowWordActivity.this,hashMaps,R.layout.show_word_listview,
                    //动态数组与ListItem对应的子项
                    new String[]{"id","word", "mean"},

                    //ListItem的XML文件里面的两个TextView ID
                    new int[]{R.id.textview_id,R.id.textview_danci, R.id.textview_shiyi});
            listViewShowWord.setAdapter(simpleAdapter);
        }
    }
}
