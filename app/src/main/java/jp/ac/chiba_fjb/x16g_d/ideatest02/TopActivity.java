package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TopActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button){
            Intent intent = new Intent(getApplication(), SignUpActivity.class);
            startActivity(intent);
        }else if (v.getId()==R.id.button2){
            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);
        }
    }
}
