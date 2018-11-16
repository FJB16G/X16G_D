package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView.Adapter adapter;
    private List<String> dataset;
    private List<String> datakey;
    private int dataid;
    private ImageButton mbutton;

    LinkedHashMap<String,String>lhm = new LinkedHashMap<>();
    EditText idea;
    String pos = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final TestDB db = new TestDB(this);
        dataset = new ArrayList<>();
        //クエリーの発行
        Cursor res = db.query("select * from test;");
        //データがなくなるまで次の行へ
        while(res.moveToNext())
        {
            //0列目を取り出し
            lhm.put(res.getString(0),res.getString(1));
        }
        dataset = new ArrayList<>(lhm.values());
        datakey = new ArrayList<>(lhm.keySet());
        adapter = new MyAdapter(dataset);
        recyclerView.setAdapter(adapter);
        //ドラッグ＆ドロップのやつ
        ItemTouchHelper mIth  = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){

                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          ViewHolder viewHolder, ViewHolder target) {
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull ViewHolder viewHolder, int i) {
                        int fromPos = viewHolder.getAdapterPosition();
                        db.exec(String.format("delete from test where id = '%s';",SQLite.STR(datakey.get(fromPos))));
                        datakey.remove(fromPos);
                        dataset.remove(fromPos);
                        adapter.notifyItemRemoved(fromPos);

                    }
                });
        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        mIth .attachToRecyclerView(recyclerView);
        //カーソルを閉じる
        res.close();
        //DBを閉じる
    }

    @Override
    public void onClick(View view) {
        TestDB db = new TestDB(this);
        if (view.getId()==R.id.add) {
            idea = (EditText)findViewById(R.id.ideaText);
            String tuika = idea.getText().toString();
            if (!tuika.equals("")) {
                //IDの生成
                int b = dataset.size()-1;
                String c = "";
                if(b<9){
                    c="000000"+b;
                }else if(b<99){
                    c="00000"+b;
                }else if(b<999){
                    c="0000"+b;
                }else if(b<9999){
                    c="000"+b;
                }else if(b<99999){
                    c="00"+b;
                }else if(b<999999){
                    c="0"+b;
                }
                dataset.add(tuika);
                datakey.add(c);
                adapter.notifyDataSetChanged();
                db.exec(String.format("insert into test values('" + c + "','%s');",SQLite.STR(tuika)));
            }
        }else if(view.getId()==R.id.insert){
                for(int i = 0; i<datakey.size();i++){
                    db.exec("update test set name = '" + dataset.get(i) + "' where id = '" + datakey.get(i) + "';");
                }
            Intent intent = new Intent(getApplication(), SubActivity.class);
            startActivity(intent);
        }
    }
}

