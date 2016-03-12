package sunkl.jiai.com.zeroword.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.manager.WordManager;
import sunkl.jiai.com.zeroword.model.Word;

public class DateWordActivity extends AppCompatActivity {
    private ListView listViewDateWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_word);
        String date =getIntent().getStringExtra("date");
        listViewDateWord = (ListView) findViewById(R.id.list_dateword);
        WordManager wordManager = new WordManager(this);
        ArrayList<Word> arrayList = wordManager.getDateWordList(date);
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        for (Word word:arrayList){
            System.out.println(word.toString());
            HashMap<String ,String> hashMap = new HashMap<>();
            hashMap.put("word",word.getWord());
            hashMap.put("mean",word.getMean());
            hashMap.put("id", String.valueOf(word.getId()+1));
            list.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.date_specific_word_listview,
                //动态数组与ListItem对应的子项
                new String[]{"id","word", "mean"},

                //ListItem的XML文件里面的两个TextView ID
                new int[]{R.id.txv_id,R.id.txv_danci, R.id.txv_shiyi});
        listViewDateWord.setAdapter(simpleAdapter);

    }
}
