package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_main,menu);
        return true;
    }




    //ここからチュートリアル

    public static final int PREFERENCE_INIT = 0;
    public static final int PREFERENCE_BOOTED = 1;

    //データ保存
    private void setState(int state) {
        // SharedPreferences設定を保存
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putInt("InitState", state).commit();
    }

    //データ読み出し
    private int getState() {
        // 読み込み
        int state;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        state = sp.getInt("InitState", PREFERENCE_INIT);
        return state;
    }


    //ダイアログ表示
    @Override
    public void onResume(){
        super.onResume();

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        // ダイアログの設定
        alertDialog.setTitle("FirstBoot");          //タイトル
        alertDialog.setMessage("初回メッセージ");      //内容
        alertDialog.setIcon(R.drawable.all_blue);   //アイコン設定

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //初回表示完了
                setState(PREFERENCE_BOOTED);
            }
        });

        // ダイアログの作成と表示
        if(PREFERENCE_INIT == getState() ){
            //初回起動時のみ表示する
            alertDialog.create();
            alertDialog.show();
        }
    }




}
