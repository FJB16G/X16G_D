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
        db.execSQL("create table idea(idea_id text primary key,idea_name text);");
        db.execSQL("create table user(user_id text primary key,user_name text,pass text);");
        db.execSQL("create table category(category_id text primary key,category_name text);");
        db.execSQL("create table grou(grou_id text primary key,grou_name text,date text,user_id text);");
        db.execSQL("create table idea_log(grou_id text,user_id text,category_id,idea_id text);");
        db.execSQL("insert into category values('c0000000','未分類');");
        db.execSQL("insert into grou values('g0000000','テストグループ','2018年12月5日','');");
        db.execSQL("insert into grou values('g0000001','テストグループ2','2018年12月5日','');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //バージョン番号を変えた場合に呼び出される
    }

}
