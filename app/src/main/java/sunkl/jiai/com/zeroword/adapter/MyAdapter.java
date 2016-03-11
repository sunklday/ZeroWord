package sunkl.jiai.com.zeroword.adapter;


import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.activity.DateWordActivity;

/**
 * Created by admin on 2016/3/10.
 */
public class MyAdapter extends  ArrayAdapter<String>{


    public MyAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.date_word_listview,null);
        } else {
            view = convertView;
        }
        TextView textView = (TextView) view.findViewById(R.id.textview_dateword);
        textView.setText(getItem(position));

        return view;
    }
}
