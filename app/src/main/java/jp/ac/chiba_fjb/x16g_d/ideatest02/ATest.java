package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ATest extends Fragment implements View.OnClickListener {
    private List<String> dataset;
    private List<String> datakey;
    private List<String> CreatedDay;
    private Activity mActivity = null;
    private View mView;
    private String grou_id;

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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final TestDB db = new TestDB(getActivity());
        datakey = new ArrayList<>();
        dataset = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        grou_id = intent.getStringExtra("id");
        Cursor res = db.query("select grou_i,grou_name from grou");

        mView.findViewById(R.id.toIdea).setOnClickListener(this);
        mView.findViewById(R.id.toCategory).setOnClickListener(this);
        mView.findViewById(R.id.toCreated).setOnClickListener(this);

        res.close();

    }
    @Override
    public void onClick(View view) {
        TestDB db = new TestDB(getActivity());

        if(view.getId()==R.id.toIdea){
            for(int i = 0; i<datakey.size();i++){
                db.exec("update idea set idea_name = '" + dataset.get(i) + "' where idea_id = '" + datakey.get(i) + "';");
            }
            Intent intent = new Intent(getActivity(), IdeaActivity.class).putExtra("id",grou_id);
            startActivity(intent);
        }if(view.getId()==R.id.toCategory){
            for(int i = 0; i<datakey.size();i++){
                db.exec("update idea set idea_name = '" + dataset.get(i) + "' where idea_id = '" + datakey.get(i) + "';");
            }
            Intent intent = new Intent(getActivity(), CategoryActivity.class).putExtra("id",grou_id);
            startActivity(intent);
        }if(view.getId()==R.id.toCreated){
            for(int i = 0; i<datakey.size();i++){
                db.exec("update idea set idea_name = '" + dataset.get(i) + "' where idea_id = '" + datakey.get(i) + "';");
            }
            Intent intent = new Intent(getActivity(), CreatedActivity.class).putExtra("id",grou_id);
            startActivity(intent);
        }
    }
}
