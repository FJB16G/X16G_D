package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.LinkedHashMap;
import java.util.List;

public class TitleFragment extends Fragment implements View.OnClickListener {
    private View topimageButton;
    private RecyclerView.Adapter adapter;
    private List<String> dataset;
    private List<String> datakey;
    private int dataid;
    private ImageButton mbutton;
    private Activity mActivity = null;
    private View mView;
    private Idea1.RecyclerFragmentListener mFragmentListener = null;
    
    // RecyclerViewとAdapter
    private RecyclerView recyclerView = null;

    LinkedHashMap<String,String> lhm = new LinkedHashMap<>();
    EditText idea;
    String pos = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_title, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        topimageButton  = view.findViewById(R.id.topimageButton);
        topimageButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        ((HomeActivity)getActivity()).changeFragment(PlanFragment.class);
    }
}
