package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity
{
    private FragmentTransaction ft;
    private final String preName = "MAIN_SETTING";
    private final String dataIntPreTag ="dataIPT";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private int dataInt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FrameLayout fl = findViewById(R.id.fragment);
        getLayoutInflater().inflate(R.layout.fragment_title,fl);
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment,new TitleFragment()).commit();
        KeyboardUtils.hide(this);

        sharedPreferences = getSharedPreferences(preName, MODE_PRIVATE);
        dataInt = sharedPreferences.getInt(dataIntPreTag, 0);
        dataInt++;
        edit = sharedPreferences.edit();
        if(dataInt == 1){
            Log.w("HomeActivity","初回起動");
            edit.putInt(dataIntPreTag,dataInt).apply();

            //初回起動だった場合、最初にHintFragmentが呼ばれる
            changeFragment(HintFragment.class);

            //初回起動時のダイアログ
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("アプリをDLしていただきありがとうございます。\nまずはアプリの使い方を確認してください。")
            .setPositiveButton("閉じる", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                // ボタンをクリックしたときの動作
                }
            });
            alertDialog.show();

        }else{
            //2回目以降の起動はこちらが呼び出される
            Log.w("HomeActivity",dataInt+"回目起動");
            edit.putInt(dataIntPreTag,dataInt).apply();
        }
    }
    public void changeFragment(Class c){
        changeFragment(c,null);
    }
    public void changeFragment(Class c,Bundle bundle){
        try {
            Fragment f = (Fragment) c.newInstance();
            if(bundle != null)
                f.setArguments(bundle);
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

    //メニューボタンのドロップダウン部分
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_main,menu);
        return true;
    }
}
