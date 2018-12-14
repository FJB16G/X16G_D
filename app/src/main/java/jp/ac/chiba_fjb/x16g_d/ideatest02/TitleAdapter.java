package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder>{

    private List<String> dataArray = new ArrayList<>();
    private List<String> datakey = new ArrayList<>();
    private List<String> day = new ArrayList<>();
    private Activity activity;
    private onItemClickListener listener;
    private String id;
    private String [] color = {"#ff8800","#22aaee","#aa66cc"};

    public TitleAdapter(Activity activity,List<String> dataset,List<String> day,List<String> datakey) {
        //MainActivityのArrayListを持ってきてる？
        this.activity = activity;
        this.day = day;
        this.datakey = datakey;
        this.dataArray = dataset;

    }
    public TitleAdapter(List<String> datakey){
        this.datakey = datakey;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //繰り返すレイアウト
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view3, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mButton.setText(dataArray.get(position));
        holder.mTextView.setText(day.get(position));
        int mod = position%3;
        holder.mTextView.setTextColor(Color.parseColor(color[mod]));
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", datakey.get(position));
                ((HomeActivity)activity).changeFragment(AllFragment.class,bundle);
            }
        });
    }
    public interface onItemClickListener{
        void onClick(String id);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return dataArray.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        Button mButton;
        TextView mTextView;

        ViewHolder(View v) {
            super(v);

            mButton = (Button)v.findViewById(R.id.grou_name);
            mTextView = (TextView)v.findViewById(R.id.day);
        }
    }
}

