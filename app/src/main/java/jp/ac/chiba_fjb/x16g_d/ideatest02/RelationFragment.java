package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RelationFragment extends DialogFragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView = null;
    private View mView;
    private String grou_id;
    private List<String> idea;
    private List<String> category;

    public RelationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dialog, container, false);
        // RecyclerViewの参照を取得
        recyclerView = mView.findViewById(R.id.my_recycler_view);
        // レイアウトマネージャを設定(ここで縦方向の標準リストであることを指定)
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return mView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeyboardUtils.initHidden(getActivity());
        final TestDB db = new TestDB(getActivity());
        grou_id = getArguments().getString("id");
        //Cursor res = db.query("select category.category_name,idea.idea_name from idea_log left outer join idea on idea_log.idea_id = idea.idea_id left outer join category on idea_log.category_id = category.category_id where idea_log.grou_id = '" + grou_id + "';");
        Cursor res = db.query("select category.category_name,idea.idea_name from idea_log left outer join idea on idea_log.idea_id = idea.idea_id left outer join category on idea_log.category_id = category.category_id where grou_id = '" + grou_id + "'");
        idea = new ArrayList<>();
        category = new ArrayList<>();
        while(res.moveToNext())
        {
            //0列目を取り出し
            category.add(res.getString(0));
            idea.add(res.getString(1));
        }
        adapter = new RelationAdapter(idea, category);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        res.close();
        db.close();
    }
}
