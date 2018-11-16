package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class TestDB extends SQLite
{

    public TestDB(Context context) {
        //ここでデータベースのファイル名とバージョン番号を指定
        super(context, "test.db",1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //初期テーブルの作成
        db.execSQL("create table test(id text primary key,name text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //バージョン番号を変えた場合に呼び出される
    }

}
