package com.example.firstapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_food";
    private static final String TABLE_NAME = "food_info";
    private static final int VERSION= 5;
    private Context context;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    public void  queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);

    }

    public void insertData(String name,byte[] img){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO food_info VALUES (NULL,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,name);
        statement.bindBlob(2,img);
        statement.executeInsert();
    }
    public void insertFood(String name){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String sql = "INSERT INTO food_info VALUES (NULL,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,name);
        statement.executeInsert();
    }



    public Cursor getData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(100),image BLOB );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
