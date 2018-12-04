package jp.ac.chiba_fjb.x16g_d.ideatest02;

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
import java.util.List;

public class Category2Adapter extends RecyclerView.Adapter<Category2Adapter.ViewHolder> {

    private List<String> dataArray = new ArrayList<>();
    private boolean flg = false;
    Category2Adapter(List<String> dataset) {
        //MainActivityのArrayListを持ってきてる？
        dataArray = dataset;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
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
            String[] spinnerItemArray = {"ゆ1","き1","む1","だよ1"};
            // adapterに中身をセット
            for(String targetStr : spinnerItemArray) {
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



