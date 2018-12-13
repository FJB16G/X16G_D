package jp.ac.chiba_fjb.x16g_d.ideatest02;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Category1 extends Fragment implements View.OnClickListener {

    private RecyclerView.Adapter adapter;
    private List<String> dataset;
    private List<String> datakey;
    private Activity mActivity = null;
    private View mView;
    private RecyclerFragmentListener mFragmentListener = null;
    private String grou_id;

    // RecyclerViewとAdapter
    private RecyclerView recyclerView = null;

    LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
    EditText category;
    String pos = "";

    public interface RecyclerFragmentListener {
        void onRecyclerEvent();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_category1, container, false);

        // RecyclerViewの参照を取得
        recyclerView = mView.findViewById(R.id.my_recycler_view);
        // レイアウトマネージャを設定(ここで縦方向の標準リストであることを指定)
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KeyboardUtils.initHidden(getActivity());
        final TestDB db = new TestDB(getActivity());
        grou_id = getArguments().getString("id");
        Cursor res = db.query("select category_id,category_name,substr(category_id,1,10) from category where substr(category_id,1,10) = '" + grou_id + "';");

        dataset = new ArrayList<>();
        datakey = new ArrayList<>();

        while (res.moveToNext()) {
            //0列目を取り出し
            datakey.add(res.getString(0));
            dataset.add(res.getString(1));
        }

        // この辺りはListViewと同じ
        // 今回は特に何もしないけど、一応クリック判定を取れる様にする
        adapter = new IdeaAdapter(dataset);
        recyclerView.setAdapter(adapter);
        //ドラッグ＆ドロップのやつ
        ItemTouchHelper mIth = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        int fromPos = viewHolder.getAdapterPosition();
                        db.exec(String.format("delete from category where category_id = '%s';", SQLite.STR(datakey.get(fromPos))));
                        //カテゴリーが消されると、そこに入っていたアイデアは未分類にカテゴライズする
                        db.exec("update idea_log set category_id = 'g000000000c0000000' where category_id = '" + datakey.get(fromPos) + "'");
                        datakey.remove(fromPos);
                        dataset.remove(fromPos);
                        adapter.notifyItemRemoved(fromPos);
                        adapter.notifyDataSetChanged();
                    }
                });
        getActivity().findViewById(R.id.toIdeaActivity).setOnClickListener(this);
        getActivity().findViewById(R.id.toNextActivity).setOnClickListener(this);
        getActivity().findViewById(R.id.add).setOnClickListener(this);
        mIth.attachToRecyclerView(recyclerView);
        //カーソルを閉じる
        res.close();
    }

    @Override
    public void onClick(View view) {
        TestDB db = new TestDB(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString("id", grou_id);
        if (view.getId()==R.id.add) {
            category = (EditText)mView.findViewById(R.id.categoryText);
            String tuika = category.getText().toString();
            String id;
            if (!tuika.equals("")) {
                //IDの生成
                int size = datakey.size()-1;
                if (size>=0){
                    id = datakey.get(size);
                }else {
                    id = grou_id + "c0000000";
                }
                int b = Integer.parseInt(id.substring(11)) + 1;
                String c = "";
                if(b<=9){
                    c="000000"+b;
                }else if(b<=99){
                    c="00000"+b;
                }else if(b<=999){
                    c="0000"+b;
                }else if(b<=9999){
                    c="000"+b;
                }else if(b<=99999){
                    c="00"+b;
                }else if(b<=999999){
                    c="0"+b;
                }
                c = "c" + c;

                //カテゴリIDの頭にグループIDが付与されることによってグループごとにカテゴリを管理
                c = grou_id + c;
                dataset.add(tuika);
                datakey.add(c);
                adapter.notifyDataSetChanged();
                db.exec(String.format("insert into category values('" + c + "','%s');",SQLite.STR(tuika)));
            }
        }else if(view.getId()==R.id.toNextActivity){
            for(int i = 0; i<datakey.size();i++){
                db.exec("update category set category_name = '" + dataset.get(i) + "' where category_id = '" + datakey.get(i) + "';");
            }
            bundle.putString("id", grou_id);
            ((HomeActivity)getActivity()).changeFragment(CreatedFragment.class,bundle);
        }else if(view.getId()==R.id.toIdeaActivity){
            for(int i = 0; i<datakey.size();i++){
                db.exec("update category set category_name = '" + dataset.get(i) + "' where category_id = '" + datakey.get(i) + "';");
            }
            bundle.putString("id", grou_id);
            ((HomeActivity)getActivity()).changeFragment(AllFragment.class,bundle);
        }
    }

    public Category1() {
        // Required empty public constructor
    }
}

