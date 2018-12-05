package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class Grou_id{
    String grou_id;

    public void setGrou_id(String grou_id_position) {
        this.grou_id = grou_id_position;
    }

    public String getGrou_id() {
        return grou_id;
    }
}

public class TitleFragment extends Fragment implements View.OnClickListener {
    private View topimageButton;
    private RecyclerView.Adapter adapter;
    private List<String> dataset;
    private List<String> datakey;
    private List<String> CreatedDay;
    private Activity mActivity = null;
    private View mView;
    private TitleFragment mFragmentListener = null;
    
    // RecyclerViewとAdapter
    private RecyclerView recyclerView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_title, container, false);

        // RecyclerViewの参照を取得
        recyclerView = mView.findViewById(R.id.my_recycler_view3);
        // レイアウトマネージャを設定(ここで縦方向の標準リストであることを指定)
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        return mView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        topimageButton  = view.findViewById(R.id.topimageButton);
        topimageButton.setOnClickListener(this);

        final TestDB db = new TestDB(getActivity());
        datakey = new ArrayList<>();
        dataset = new ArrayList<>();
        CreatedDay = new ArrayList<>();
        Cursor res = db.query("select * from grou");
        while (res.moveToNext()){
            datakey.add(res.getString(0));      //id
            dataset.add(res.getString(1));      //name
            CreatedDay.add(res.getString(2));   //date
        }
        res.close();
        adapter = new TitleAdapter(getContext(),dataset,CreatedDay,datakey);
        recyclerView.setAdapter(adapter);


        //ドラッグ＆ドロップのやつ
        ItemTouchHelper mIth  = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return true;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        int fromPos = viewHolder.getAdapterPosition();

                        db.exec(String.format("delete from grou where grou_id = '%s';",SQLite.STR(datakey.get(fromPos))));
                        db.exec(String.format("delete from idea_log where grou_id = '%s';",SQLite.STR(datakey.get(fromPos))));
                        datakey.remove(fromPos);
                        dataset.remove(fromPos);
                        adapter.notifyItemRemoved(fromPos);
                        adapter.notifyDataSetChanged();
                    }
                });
        mIth.attachToRecyclerView(recyclerView);
    }
    @Override
    public void onClick(View view) {
        ((HomeActivity)getActivity()).changeFragment(PlanFragment.class);
    }
}
