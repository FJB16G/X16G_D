package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreatedActivity extends AppCompatActivity implements View.OnClickListener {
private EditText grou_idea_name;
private String name;
private String grou_id;
private Intent intent;
private TestDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created);
        KeyboardUtils.initHidden(this);
        intent = getIntent();
        db = new TestDB(this);
        grou_id = intent.getStringExtra("id");
        findViewById(R.id.toCategory).setOnClickListener(this);
        findViewById(R.id.commit).setOnClickListener(this);
        Cursor res = db.query("select grou_idea_name from grou where grou_id = '" + grou_id + "'");
        while (res.moveToNext()) {
            //0列目を取り出し
            name = res.getString(0);
        }
        res.close();
        grou_idea_name = (EditText)findViewById(R.id.grou_idea_name);
        grou_idea_name.setText(name);
    }
    @Override
    public void onClick(View v) {
        TestDB db = new TestDB(this);
        intent = new Intent();
        if (v.getId() == R.id.toCategory){
            db.exec("update grou set grou_idea_name = '" + name + "' where grou_id = '" + grou_id + "'");
            intent = new Intent(this,AllActivity.class).putExtra("id", grou_id);
            startActivity(intent);
            finish();
        }else if (v.getId() == R.id.commit){
            name = grou_idea_name.getText().toString();
            db.exec("update grou set grou_idea_name = '" + name + "' where grou_id = '" + grou_id + "'");
            intent = new Intent(this,HomeActivity.class);
            //今までのactivityを消す
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
