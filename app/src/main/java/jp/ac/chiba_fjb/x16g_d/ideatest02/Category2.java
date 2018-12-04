package jp.ac.chiba_fjb.x16g_d.ideatest02;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Category2 extends Fragment {

private List<String> list;
private TextView idea;
private TextView cateogry;
    public Category2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category2, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TestDB db = new TestDB(getActivity());
        Cursor res = db.query("select idea_id,category_id from idea_log;");
        list = new ArrayList<>();
        idea = getActivity().findViewById(R.id.idea_id);
        cateogry = getActivity().findViewById(R.id.category_id);
        idea.append("アイデアID\n");
        cateogry.append("カテゴリID\n");
        while (res.moveToNext()){
            //奇数カテゴリ、偶数アイディア
            idea.append(res.getString(0) + "\n");
            cateogry.append(res.getString(1) + "\n");
        }
        res.close();
    }
}
