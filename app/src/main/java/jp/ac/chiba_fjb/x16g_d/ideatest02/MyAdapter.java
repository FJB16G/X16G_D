package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLData;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<String> dataArray = new ArrayList<>();
    private int dataId;
    private boolean flg = false;
    MyAdapter(List<String> dataset) {
        //MainActivityのArrayListを持ってきてる？
        dataArray = dataset;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        EditText mTextView;
        Button mButton;

        //繰り返すxmlの中身
        ViewHolder(View v) {
            super(v);

            mTextView = (EditText) v.findViewById(R.id.idea);

            //編集不可
            mTextView.setEnabled(false);
            //どこのボタンかを[v]で受け取ってるっぽい
            mButton = (Button)v.findViewById(R.id.change);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //繰り返すレイアウト
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mTextView.setText(dataArray.get(position));
        //編集ボタン
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flg==false){
                    //編集可能
                    holder.mButton.setText("完了");
                    holder.mTextView.setEnabled(true);
                    flg = true;
                }else {
                    //編集完了
                    holder.mButton.setText("編集");
                    holder.mTextView.setEnabled(false);
                    String text = String.valueOf(holder.mTextView.getText());
                    dataArray.set(position,text);
                    flg = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }
}

