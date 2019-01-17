package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.ClipData;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.security.AccessController.getContext;

public class Category2Adapter extends RecyclerView.Adapter<Category2Adapter.ViewHolder> {

    interface OnItemClickListener{
        void onItemClick(String value, int value2);
    }
    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    private List<String> dataArray = new ArrayList<>();
    private List<String> dataArray2 = new ArrayList<>();
    private List<String> dataArray3 = new ArrayList<>();

    private boolean flg = false;
    private int cnt=0;



    Category2Adapter(List<String> dataset, List<String> dataset2) {
        //MainActivityのArrayListを持ってきてる？
        dataArray = dataset;
        dataArray2 = dataset2;
        cnt=2;

    }

    Category2Adapter(List<String> dataset, List<String> dataset2,List<String>datakey2) {
        //MainActivityのArrayListを持ってきてる？
        dataArray = dataset;
        dataArray2 = dataset2;
        dataArray3 = datakey2;

        cnt=2;

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        Button mButtom;
        Spinner mSpinner;


        //繰り返すxmlの中身
        ViewHolder(View v) {
            super(v);

            mTextView =  v.findViewById(R.id.text_view);
            mButtom = v.findViewById(R.id.button);
            mSpinner = v.findViewById(R.id.spinner);

            //どこのボタンかを[v]で受け取ってるっぽい
            //(仮)
            //mButtom.setOnClickListener(new View.OnClickListener() {
            //    public void onClick(View v) {
            //        if(mListener != null)

            //            mListener.onItemClick("");
                    // クリック時の処理
            //        Log.w("dbg2z","click！！！！");

            //    }
            //});

            //Spinnerの設定
            ArrayAdapter adapter = new ArrayAdapter<Pair<String,String>>(v.getContext(), android.R.layout.simple_spinner_item);


            if (cnt==2){

            }

            List<String> spArray = new ArrayList<>();
            List<String> spArray2 = new ArrayList<>();

                for (String spinnerString : dataArray2) {
                    spArray.add(spinnerString);

                }

            for (String spinnerString : dataArray3) {
                spArray2.add(spinnerString);

            }



            //adapterに中身をセット
            for(String targetStr : spArray) {
              adapter.add(targetStr);

            }


           // for(int i =0;i<spArray.size();i++){

           //    adapter.add(new Pair<String, String>(spArray.get(i),spArray2.get(i)));

            //}

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(adapter);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //繰り返すレイアウト
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

       viewHolder.mTextView.setText(dataArray.get(position));

        viewHolder.mButtom.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Log.w("dbg2z",v+"click！！！！"+position);
                Log.w("dbg2z",v+"click！！！！"+viewHolder.mTextView.getText());
                Log.w("dbg2z",v+"click！！！！"+viewHolder.mSpinner.getSelectedItem());
                String item = (String)viewHolder.mSpinner.getSelectedItem();
                mListener.onItemClick(item,position);

            }
        });

    }







    @Override
    public int getItemCount() {
        return dataArray.size();

    }

}



