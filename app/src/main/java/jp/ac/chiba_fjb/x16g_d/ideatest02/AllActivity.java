package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AllActivity extends Fragment implements View.OnClickListener {

    private String grou_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_created, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final TestDB db = new TestDB(getActivity());

        //グループIDの受け取り方
        Intent intent = getActivity().getIntent();
        grou_id = intent.getStringExtra("id");

    }
    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.toIdea){
            Intent intent = new Intent(getActivity(), IdeaActivity.class);
            startActivity(intent);
        }if(view.getId()==R.id.toCategory){
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            startActivity(intent);
        }if(view.getId()==R.id.toCreated){
            Intent intent = new Intent(getActivity(), CreatedActivity.class);
            startActivity(intent);
        }

    }
}
