package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {
    private CharSequence[] tabTitle = {"タブ1", "タブ2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        findViewById(R.id.imageButton).setOnClickListener(this);
        findViewById(R.id.imageButton2).setOnClickListener(this);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                switch (position)
                {
                    case 0:
                        return new Main1Fragment();
                    case 1:
                        return new Main2Fragment();
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
        if (view.getId()==R.id.imageButton){
            finish();
        }else if (view.getId()==R.id.imageButton2){

        }
    }
}

