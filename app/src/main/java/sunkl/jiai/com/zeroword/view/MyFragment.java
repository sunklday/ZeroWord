package sunkl.jiai.com.zeroword.view;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;

import sunkl.jiai.com.zeroword.R;
import sunkl.jiai.com.zeroword.adapter.MyAdapter;
import sunkl.jiai.com.zeroword.db.DBManager;
import sunkl.jiai.com.zeroword.view.dummy.DummyContent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * @link MyFragment.OnFragmentInteractionListener interface
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private ListView listViewDateWord;

    // TODO: Rename and change types of parameters
    private int mParam1;


   // private OnFragmentInteractionListener mListener;

    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     *
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(int param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        listViewDateWord = (ListView) view.findViewById(R.id.lsv_dateword);

        ArrayList<String> arrayListDate = new ArrayList<>();
        DBManager dbManager = new DBManager(getContext());
        Cursor cursor = dbManager.dateSelect();
        while (cursor.moveToNext()){
            arrayListDate.add(cursor.getString(cursor.getColumnIndex("date")));
        }
        MyAdapter myAdapter = new MyAdapter(getContext(),0,arrayListDate);
        listViewDateWord.setAdapter(myAdapter);
       // listViewDateWord.setAdapter();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
  /*  public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
