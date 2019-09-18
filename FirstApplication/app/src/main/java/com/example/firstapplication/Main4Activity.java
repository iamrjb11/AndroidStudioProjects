package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Main4Activity extends AppCompatActivity {

    private ListView listView;
    private String[] names;
    private int[] imgs = {R.drawable.capture,R.drawable.capture,R.drawable.capture,R.drawable.capture,R.drawable.capture,R.drawable.capture,
            R.drawable.capture,R.drawable.capture,R.drawable.capture,R.drawable.capture,R.drawable.capture,R.drawable.capture,R.drawable.capture,
            R.drawable.capture};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        names = getResources().getStringArray(R.array.list_items);

        listView = findViewById(R.id.listID2);
        //CustomAdapter adapter = new CustomAdapter(this,names,imgs);
        //listView.setAdapter(adapter);

    }
}
