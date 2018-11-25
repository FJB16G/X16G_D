package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.cancel){
            finish();
        }if (v.getId()==R.id.email_sign_in_button){
            Intent intent = new Intent(getApplication(), IdeaActivity.class);
            startActivity(intent);
        }
    }
}
