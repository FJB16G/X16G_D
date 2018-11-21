package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private CharSequence[] tabTitle = {"分類作成", "分類分け"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        findViewById(R.id.toIdea).setOnClickListener(this);
        findViewById(R.id.toCategorySelect).setOnClickListener(this);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                switch (position)
                {
                    case 0:
                        return new Category1();
                    case 1:
                        return new Category2();
                    //case 2:
                    //    return new Main1Fragment();
                    default:
                        return null;
                }
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
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(tabTitle.length);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.toIdea){
            finish();
        }else if (view.getId()==R.id.toCategorySelect){

        }
    }

}

