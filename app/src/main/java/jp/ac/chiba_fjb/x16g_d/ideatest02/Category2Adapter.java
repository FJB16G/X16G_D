package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private List<String> dataArray = new ArrayList<>();
    private List<String> dataArray2 = new ArrayList<>();

    private boolean flg = false;
    private int cnt=0;

    Category2Adapter(List<String> dataset ) {
        //MainActivityのArrayListを持ってきてる？
        dataArray = dataset;
        cnt=1;

    }

    Category2Adapter(List<String> dataset, List<String> dataset2) {
        //MainActivityのArrayListを持ってきてる？
        dataArray = dataset;
        dataArray2 = dataset2;
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



            ArrayAdapter adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item);
            Log.w("dbg2",dataArray.get(0));

            if (cnt==2){
                Log.w("dbg2",dataArray2.get(0)+"/2個目！！！！");
            }

            List<String> spArray = new ArrayList<>();

                for (String spinnerString : dataArray2) {
                    spArray.add(spinnerString);

                }


            //adapterに中身をセット
            for(String targetStr : spArray) {
              adapter.add(targetStr);

            }

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
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.mTextView.setText(dataArray.get(i));


    }




    @Override
    public int getItemCount() {
        return dataArray.size();
    }
}



