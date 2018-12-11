package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.apache.commons.lang.RandomStringUtils;

public class PlanFragment extends Fragment implements View.OnClickListener {
    private View npbackbutton;
    private View createbutton;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        npbackbutton = view.findViewById(R.id.npbackbutton);
        npbackbutton.setOnClickListener(this);
        createbutton = view.findViewById(R.id.createbutton);
        createbutton.setOnClickListener(this);
        KeyboardUtils.initHidden(getActivity());
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.npbackbutton){
            ((HomeActivity)getActivity()).changeFragment(TitleFragment.class);
        }else if (view.getId()==R.id.createbutton) {
            editText = getActivity().findViewById(R.id.groupname);
            editText.setError(null);
            String name = editText.getText().toString();
            View focusView = null;
            if (TextUtils.isEmpty(name)) {
                editText.setError("入力してください");
                focusView = editText;
            } else {
                //ネット同期ができれば、ユーザIDを付与することにより管理者を入れる。
                String r = RandomStringUtils.randomAlphabetic(10);
                TestDB db = new TestDB(getActivity());
                db.exec("insert into grou(grou_id,grou_name,date,user_id,grou_idea_name) values('" + r + "','" + name + "',datetime('now', 'localtime'),'','')");
                ((HomeActivity) getActivity()).changeFragment(TitleFragment.class);
            }
        }
    }
}
