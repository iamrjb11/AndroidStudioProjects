package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Food_List extends AppCompatActivity {
    private GridView gridView;

    ArrayList<Food> list;
    FoodListAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__list);


        list = new ArrayList<>();

        TextView textView = findViewById(R.id.nameID12);
        gridView = findViewById(R.id.listFood);
        adapter = new FoodListAdapter(this,R.layout.simple_layout2,list);
        gridView.setAdapter(adapter);


        //SQLite
        Cursor cursor = Insert_Image.sqLiteHelper.getData("select * from FOOD");
        list.clear();
        byte[] img;
        if(cursor.moveToNext()){
            Toast.makeText(this, "Has", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Not", Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()){
            int id = cursor.getInt(1);
            String name = cursor.getString(2);
            img = cursor.getBlob(3);

            list.add(new Food(name,img,id));
            //textView.setText(name);
            Toast.makeText(this, "Name is : "+name, Toast.LENGTH_SHORT).show();


        }


        adapter.notifyDataSetChanged();

    }
}
