package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreatedActivity extends AppCompatActivity implements View.OnClickListener {
private EditText grou_idea_name;
private String name;
private String grou_id;
private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created);
        intent = new Intent();
        grou_id = intent.getStringExtra("id");
        findViewById(R.id.toCategory).setOnClickListener(this);
        findViewById(R.id.commit).setOnClickListener(this);
        grou_idea_name = (EditText)findViewById(R.id.grou_idea_name);
        name = grou_idea_name.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toCategory){
            intent = new Intent(this,CreatedActivity.class).putExtra("id",grou_id);
            startActivity(intent);
            finish();
        }else if (v.getId() == R.id.commit){
            intent = new Intent(getApplicationContext(),HomeActivity.class);
            //今までのactivityを消す
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
