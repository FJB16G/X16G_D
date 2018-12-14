package jp.ac.chiba_fjb.x16g_d.ideatest02;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment implements View.OnClickListener {

private View mView;
private String grou_id;
    public AllFragment() {
        // Required empty public constructor
    }
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_all, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        //グループの取得をしているところ
        grou_id = getArguments().getString("id");
        mView.findViewById(R.id.toIdea).setOnClickListener(this);
        mView.findViewById(R.id.toCategory).setOnClickListener(this);
        mView.findViewById(R.id.toCreated).setOnClickListener(this);
        mView.findViewById(R.id.toHome).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //フラグメントの値をbundleにセット
        Bundle bundle = new Bundle();
        bundle.putString("id", grou_id);
        if(v.getId()==R.id.toIdea){
            //最後の,bundleという部分が値が入った箱を送っている処理
            ((HomeActivity)getActivity()).changeFragment(IdeaFragment.class,bundle);
        }else if(v.getId()==R.id.toCategory){
            ((HomeActivity)getActivity()).changeFragment(CategoryFragment.class,bundle);
        }else if(v.getId()==R.id.toCreated){
            ((HomeActivity)getActivity()).changeFragment(CreatedFragment.class,bundle);
        }else if(v.getId()==R.id.toHome){
            ((HomeActivity)getActivity()).changeFragment(TitleFragment.class);
        }
    }
}
