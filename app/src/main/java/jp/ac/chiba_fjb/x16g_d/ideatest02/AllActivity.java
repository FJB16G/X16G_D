package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AllActivity extends AppCompatActivity implements View.OnClickListener {
    private String grou_id;
    private Intent intent;
    private TestDB db;
    private List<String> dataset;
    private List<String> datakey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        db = new TestDB(this);
        intent = getIntent();
        grou_id = intent.getStringExtra("id");

        findViewById(R.id.toIdea).setOnClickListener(this);
        findViewById(R.id.toCategory).setOnClickListener(this);
        findViewById(R.id.toCreated).setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.toIdea){

            Intent intent = new Intent(AllActivity.this,IdeaActivity.class).putExtra("id",grou_id);
            startActivity(intent);

        }else if(view.getId()==R.id.toCategory){

            Intent intent = new Intent(AllActivity.this,CategoryActivity.class).putExtra("id",grou_id);
            startActivity(intent);
        }else if(view.getId()==R.id.toCreated){
            Intent intent = new Intent(AllActivity.this,CreatedActivity.class).putExtra("id",grou_id);
            startActivity(intent);
        }

    }
    public AllActivity() {
    }
}
