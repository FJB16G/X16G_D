package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity
{
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FrameLayout fl = findViewById(R.id.fragment);
        getLayoutInflater().inflate(R.layout.fragment_title,fl);
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment,new TitleFragment()).commit();
    }
    public void changeFragment(Class c){
        changeFragment(c,null);
    }
    public void changeFragment(Class c,Bundle budle){
        try {
            Fragment f = (Fragment) c.newInstance();
            if(budle != null)
                f.setArguments(budle);
            else
                f.setArguments(new Bundle());
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment,f);
            ft.addToBackStack(null);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
