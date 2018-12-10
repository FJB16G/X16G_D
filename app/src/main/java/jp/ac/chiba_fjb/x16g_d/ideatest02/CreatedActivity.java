package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public class CreatedActivity extends Fragment implements View.OnClickListener {




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
        Cursor res = db.query("select * from grou");

    }
    @Override
    public void onClick(View view) {

    }
}
