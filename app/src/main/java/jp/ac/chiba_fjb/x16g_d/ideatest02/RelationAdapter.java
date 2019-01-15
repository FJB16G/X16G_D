package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RelationAdapter extends RecyclerView.Adapter<RelationAdapter.ViewHolder>{

    private List<String> Idea = new ArrayList<>();
    private List<String> Category = new ArrayList<>();
    RelationAdapter(List<String> idea,List<String> category) {
        //MainActivityのArrayListを持ってきてる？
        Idea = idea;
        Category = category;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mIdea;
        TextView mCategory;

        //繰り返すxmlの中身
        ViewHolder(View v) {
            super(v);
            mIdea = (TextView) v.findViewById(R.id.idea);
            mCategory = (TextView) v.findViewById(R.id.category);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //繰り返すレイアウト
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view4, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mIdea.setText(Idea.get(position));
        holder.mCategory.setText(Category.get(position));

        if(position>=1){
            if(Category.get(position).equals(Category.get(position-1))){
                holder.mCategory.setText("");
                holder.mCategory.setBackgroundColor(0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return Idea.size()|Category.size();
    }
}
