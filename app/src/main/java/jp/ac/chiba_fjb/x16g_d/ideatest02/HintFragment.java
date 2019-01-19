package jp.ac.chiba_fjb.x16g_d.ideatest02;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HintFragment extends Fragment implements View.OnClickListener {

    private CharSequence[] tabTitle = {"ステップ1", "ステップ2","ステップ3","ステップ4","ステップ5"};
    private View mView;
    public HintFragment() {
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hint, container, false);

        mView.findViewById(R.id.floatingActionButton).setOnClickListener(this);

        final Bundle bundle = new Bundle();

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this.getChildFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                Fragment fragment =null;
                switch (position){
                    case 0:
                        fragment= new Hint1();
                        break;
                    case 1:
                        fragment = new Hint2();
                        break;
                    case 2:
                        fragment = new Hint3();
                        break;
                    case 3:
                        fragment = new Hint4();
                        break;
                    case 4:
                        fragment = new Hint5();
                        break;
                }
                return fragment;
            }
            @Override
            public CharSequence getPageTitle(int position)
            {
                return tabTitle[position];
            }
            @Override
            public int getCount()
            {
                return tabTitle.length;
            }
        };
        ViewPager viewPager = mView.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(tabTitle.length);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = mView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.floatingActionButton){
            ((HomeActivity)getActivity()).changeFragment(TitleFragment.class);
        }
    }
}
