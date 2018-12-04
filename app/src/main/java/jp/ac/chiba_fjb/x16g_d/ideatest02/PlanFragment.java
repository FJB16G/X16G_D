package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class PlanFragment extends Fragment implements View.OnClickListener {
    private View npbackbutton;
    private View createbutton;

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


        //((MainActivity)getActivity()).changeFragment(Main2Fragment.class);
    }




    @Override
    public void onClick(View view) {
        ((HomeActivity)getActivity()).changeFragment(TitleFragment.class);

    }



}
