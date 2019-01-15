package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Category2 extends Fragment implements View.OnClickListener, Category2Adapter.OnItemClickListener {
    private RecyclerView.Adapter Adapter;
    private List<String> dataset;
    private List<String> datakey;
    private List<String> dataset2;
    private List<String> datakey2;
    private List<String> a;
    private Activity Activity = null;
    private View mView;
    private View sView;
    private String grou_id;

    // RecyclerViewとAdapter
    private RecyclerView recyclerView = null;

    LinkedHashMap<String,String> lhm = new LinkedHashMap<>();
    LinkedHashMap<String,String> lhm2 = new LinkedHashMap<>();
    LinkedHashMap<String,String> lhm3 = new LinkedHashMap<>();

    RecyclerView.ViewHolder viewHolder;

    @Override
    public void onItemClick(String value,int value2) {
        Log.w("dbg2",value+"！！！！");
        Log.w("dbg22",value2+"！！！！");
        LinkedHashMap<String,String> lhm3 = new LinkedHashMap<>();

        TestDB db = new TestDB(getActivity());
        Cursor res3 = db.query("select * from category where category_name = '"+value+"' and substr(category_id,1,10) = '" + grou_id + "'or category_id = 'g000000000c0000000';");

        while(res3.moveToNext())
        {
            //0列目を取り出し
            lhm3.put(res3.getString(0),res3.getString(1));
        }
        a = new ArrayList<>(lhm3.keySet());

        String b = a.get(0);
        String c = "";
        if (a.size()>=2){
             c = a.get(1);
        }
        Log.w("dbg2222",b + "/" + c);
        db.exec("update idea_log set category_id = '" + b + "' where idea_id = '" + datakey.get(value2) + "';");

    }

    public interface RecyclerFragmentListener {
        void onRecyclerEvent();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_category2, container, false);
        sView = inflater.inflate(R.layout.my_text_view2, container, false);
        // RecyclerViewの参照を取得
        recyclerView = mView.findViewById(R.id.my_recycler_view2);
        // レイアウトマネージャを設定(ここで縦方向の標準リストであることを指定)
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity));
        return mView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TestDB db = new TestDB(getActivity());
        grou_id = getArguments().getString("id");

        //クエリーの発行
        Cursor res = db.query("select idea_log.idea_id,idea.idea_name from idea_log left outer join idea on idea_log.idea_id = idea.idea_id where idea_log.grou_id = '" + grou_id + "';");

        //Cursor res = db.query("select * from idea");

        //データがなくなるまで次の行へ
        while(res.moveToNext())
        {
            //0列目を取り出し
            lhm.put(res.getString(0),res.getString(1));
        }
        dataset = new ArrayList<>(lhm.values());
        datakey = new ArrayList<>(lhm.keySet());

        dataset2 = new ArrayList<>();
        datakey2 = new ArrayList<>();

        //クエリーの発行
        Cursor res2 = db.query("select category_id,category_name,substr(category_id,1,10) from category where substr(category_id,1,10) = '" + grou_id + "'or category_id = 'g000000000c0000000';");
        //Cursor res2 = db.query("select * from category");

        //データがなくなるまで次の行へ
        while(res2.moveToNext())
        {
            //0列目を取り出し
            lhm2.put(res2.getString(0),res2.getString(1));

           datakey2.add(res2.getString(0));
           dataset2.add(res2.getString(1));
        }
        dataset2 = new ArrayList<>(lhm2.values());
        datakey2 = new ArrayList<>(lhm2.keySet());

        // この辺りはListViewと同じ
        // 今回は特に何もしないけど、一応クリック判定を取れる様にする
        Adapter = new Category2Adapter(dataset,dataset2,datakey2);
        ((Category2Adapter) Adapter).setOnItemClickListener(this);
        recyclerView.setAdapter(Adapter);
    }
    @Override
    public void onClick(View view ) {
    }

    public Category2() {
    }
}
