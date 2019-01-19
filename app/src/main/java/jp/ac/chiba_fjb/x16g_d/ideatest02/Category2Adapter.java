package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
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
    private List<String> dataArray4 = new ArrayList<>();
    private List<String> dataArray5 = new ArrayList<>();
    private List<Integer> count = new ArrayList<>();
    private ArrayAdapter adapter;

    Category2Adapter(List<String> dataset, List<String> dataset2,List<String>datakey2,List<String>Category_Id) {
        //MainActivityのArrayListを持ってきてる？
        dataArray = dataset;//アイデア名
        dataArray2 = dataset2;//カテゴリ名
        dataArray3 = datakey2;//カテゴリID
        dataArray4 = Category_Id;//アイデアがどこのカテゴリなのかのやつ
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        Button mButtom;
        Spinner mSpinner;

        //繰り返すxmlの中身
        ViewHolder(View v) {
            super(v);

            mTextView = v.findViewById(R.id.text_view);
            mButtom = v.findViewById(R.id.button);
            mSpinner = v.findViewById(R.id.spinner);

            //Spinnerの設定
            adapter = new ArrayAdapter<Pair<String,String>>(v.getContext(), android.R.layout.simple_spinner_item);
            //adapterに中身をセット
            for (String ca:dataArray4){
                int i = 0;
                while (!ca.equals(dataArray3.get(i))){
                    i++;
                }
                count.add(i);
            }

            for(String targetStr : dataArray2) {
              adapter.add(targetStr);
            }
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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
        viewHolder.mSpinner.setSelection(count.get(position));
        viewHolder.mButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("CategoryAdapter","position:"+position);
                Log.w("CategoryAdapter","Idea_Name:"+viewHolder.mTextView.getText());
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



