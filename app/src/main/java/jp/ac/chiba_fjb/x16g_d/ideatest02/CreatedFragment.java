package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CreatedFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private EditText grou_idea_name;
    private String name;
    private String grou_id;
    private TestDB db;
    public CreatedFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         mView = inflater.inflate(R.layout.fragment_created, container, false);
         db = new TestDB(getActivity());
         grou_id = getArguments().getString("id");
         mView.findViewById(R.id.toAllFragment).setOnClickListener(this);
         mView.findViewById(R.id.commit).setOnClickListener(this);
         mView.findViewById(R.id.floatingActionButton).setOnClickListener(this);
         mView.findViewById(R.id.floatingActionButton2).setOnClickListener(this);
         Cursor res = db.query("select grou_idea_name from grou where grou_id = '" + grou_id + "'");
         while (res.moveToNext()) {
             //0列目を取り出し
             name = res.getString(0);
         }
         res.close();
         grou_idea_name = (EditText)mView.findViewById(R.id.grou_idea_name);
         grou_idea_name.setText(name);
         return mView;
    }

    @Override
    public void onClick(View v) {
        db = new TestDB(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString("id", grou_id);
        if (v.getId() == R.id.toAllFragment){
            db.exec("update grou set grou_idea_name = '" + name + "' where grou_id = '" + grou_id + "'");
            ((HomeActivity)getActivity()).changeFragment(CategoryFragment.class,bundle);
        }else if (v.getId() == R.id.commit){
            name = grou_idea_name.getText().toString();
            db.exec("update grou set grou_idea_name = '" + name + "' where grou_id = '" + grou_id + "'");
            Intent intent = new Intent(getActivity(),HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        }else if (v.getId() == R.id.floatingActionButton){
            Intent intent = new Intent(getActivity(),HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        }else if (v.getId() == R.id.floatingActionButton2){
            ((HomeActivity)getActivity()).changeFragment(AllFragment.class,bundle);
        }
    }
}
