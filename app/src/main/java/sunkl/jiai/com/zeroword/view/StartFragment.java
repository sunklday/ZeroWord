package sunkl.jiai.com.zeroword.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.activity.ShowWordActivity;
import sunkl.jiai.com.zeroword.db.DBManager;

/**
 * Created by admin on 16/2/18.
 */
public class StartFragment extends Fragment {
    private Button button;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public StartFragment() {
    }

    public static StartFragment newInstance(int sectionNumber) {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        button = (Button) rootView.findViewById(R.id.btn_star);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ShowWordActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
    private void setData(){
        DBManager dbManager = new DBManager(getContext());
        Cursor userCursor = dbManager.userSelect();
        int i = 0;
        while (userCursor.moveToNext()) {
            i = Integer.parseInt(userCursor.getString(userCursor.getColumnIndex("amount")));
        }
        while (i>0) {
            System.out.print("输出单词："+i+"--");
            double d  = Math.random();
            int wordnumber = (int)(d*400);
            System.out.print(wordnumber+"--");
            Cursor cursor = dbManager.selectword(wordnumber);

            while (cursor.moveToNext()) {
                System.out.println(cursor.getString(cursor.getColumnIndex("word")));
                if (!cursor.getString(cursor.getColumnIndex("degree")).equals("0")){
                    continue;
                }
            }

            i--;
        }
    }
}
