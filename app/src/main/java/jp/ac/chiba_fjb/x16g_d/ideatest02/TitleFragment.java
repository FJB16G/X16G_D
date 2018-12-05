package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TitleFragment extends Fragment implements View.OnClickListener {
    private View topimageButton;
    private RecyclerView.Adapter adapter;
    private List<String> dataset;
    private List<String> datakey;
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
        recyclerView = mView.findViewById(R.id.my_recycler_view);
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
        Cursor res = db.query("select * from grou");
        while (res.moveToNext()){
            datakey.add(res.getString(0));
            dataset.add(res.getString(1));
        }
        adapter = new TitleAdapter(dataset, datakey);
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

                        //アイデアログからもアイデアを削除する必要がある
                        db.exec(String.format("delete from grou where grou_id = '%s';",SQLite.STR(datakey.get(fromPos))));
                        db.exec(String.format("delete from idea_log where grou_id = '%s';",SQLite.STR(datakey.get(fromPos))));
                        datakey.remove(fromPos);
                        dataset.remove(fromPos);
                        adapter.notifyItemRemoved(fromPos);
                        adapter.notifyDataSetChanged();
                    }
                });

        setOnItemClickListener(new TitleAdapter.onItemClickListener() {
        @Override
        public void onClick(View view, String name) {
            Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
        }
    });
        mIth.attachToRecyclerView(recyclerView);
        res.close();
    }
    @Override
    public void onClick(View view) {
        ((HomeActivity)getActivity()).changeFragment(PlanFragment.class);
    }
}
