package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder>{

    private List<String> dataArray = new ArrayList<>();
    private List<String> datakey = new ArrayList<>();
    private onItemClickListener listener;

    public TitleAdapter(List<String> dataset,List<String> datakey) {
        //MainActivityのArrayListを持ってきてる？
        this.datakey = datakey;
        this.dataArray = dataset;
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
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, dataArray.get(position));
            }
        });
    }
    public interface onItemClickListener {
        void onClick(View view, String name);
    }
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return dataArray.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        Button mButton;

        //繰り返すxmlの中身
        ViewHolder(View v) {
            super(v);
            //どこのボタンかを[v]で受け取ってるっぽい
            mButton = (Button)v.findViewById(R.id.grou_name);
        }
    }
}

