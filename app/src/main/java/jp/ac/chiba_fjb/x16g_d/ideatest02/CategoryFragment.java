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

public class CategoryFragment extends Fragment {

    private CharSequence[] tabTitle = {"カテゴリ入力", "カテゴリわけ"};
    private View mView;
    public CategoryFragment() {
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_category, container, false);
        FloatingActionButton floatingActionButton = (FloatingActionButton) mView.findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        String grou_id = getArguments().getString("id");
        final Bundle bundle = new Bundle();
        bundle.putString("id", grou_id);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(this.getChildFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                Fragment fragment =null;
                switch (position){
                    case 0:
                        fragment= new Category1();
                        fragment.setArguments(bundle);
                        break;
                    case 1:
                        fragment = new Category2();
                        fragment.setArguments(bundle);
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

}
