package jp.ac.chiba_fjb.x16g_d.ideatest02;


import android.app.Activity;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Category2 extends Fragment implements View.OnClickListener {
    private RecyclerView.Adapter Adapter;
    private List<String> dataset;
    private List<String> datakey;
    private List<String> SpinnerArray;
    private Activity Activity = null;
    private View mView;
    private View sView;
    Spinner mSpinner;
    private RecyclerFragmentListener mFragmentListener = null;

    // RecyclerViewとAdapter
    private RecyclerView recyclerView = null;

    LinkedHashMap<String,String> lhm = new LinkedHashMap<>();


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

        dataset = new ArrayList<>();


        //クエリーの発行
        Cursor res = db.query("select * from idea;");
        //データがなくなるまで次の行へ
        while(res.moveToNext())
        {
            //0列目を取り出し
            lhm.put(res.getString(0),res.getString(1));
        }
        dataset = new ArrayList<>(lhm.values());
        datakey = new ArrayList<>(lhm.keySet());


        // この辺りはListViewと同じ
        // 今回は特に何もしないけど、一応クリック判定を取れる様にする
        Adapter = new Category2Adapter(dataset);
        recyclerView.setAdapter(Adapter);




        // Spinerにアダプターを設定
        mSpinner = sView.findViewById(R.id.spinner);
        // アダプターを設定します（作成たメソッド呼び出すように修正しています。）
        mSpinner.setAdapter(setAdapterContents());



    }

    private ArrayAdapter<String> setAdapterContents(){
        // とりあえず、adapterの中身はなしでインスタンスを生成
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
//        // 自分でspinnerの中身の配列を定義しています。
//        SpinnerArray = new ArrayList<>();
//        SpinnerArray.add("aaaa");
//        SpinnerArray.add("bbbb");
//        SpinnerArray.add("cccc");
//
//        // adapterに中身をセット
//        for(String targetStr : SpinnerArray) {
//            adapter.add(targetStr);
//        }



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;


    }





    @Override
    public void onClick(View view) {

    }

    public Category2() {
        // Required empty public constructor
    }
}
