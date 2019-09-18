package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder alertdialogbuilder;

    private LinearLayout linearLayout;
    private TextView textView,show_ck,testView;
    private EditText nameTxt;
    private Button button,btn_ck;
    private CheckBox ck1,ck2,ck3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.lala);

        this.setTitle("Application");
        textView =  findViewById(R.id.textViewID);
        show_ck =  findViewById(R.id.show_ck);
        nameTxt =  findViewById(R.id.nameTxt);

        button =  findViewById(R.id.btnID);
        btn_ck =  findViewById(R.id.btn_ck);

        ck1 =  findViewById(R.id.rajib_ck);
        ck2 =  findViewById(R.id.rakib_ck);
        ck3 =  findViewById(R.id.rrk_ck);

        TextView dynamicTextView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dynamicTextView.setText(" Hello World ");

        linearLayout.addView(dynamicTextView,layoutParams);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mac2){
            //Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);

        }
        if(item.getItemId() == R.id.mac3){
//            Toast.makeText(this, "RRK", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Main3Activity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.mac4){
//            Toast.makeText(this, "RRK", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Main4Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage(View v){
//        textView.setText("Button is clicked");
        String name = nameTxt.getText().toString().trim();
        if(name.isEmpty()){
            nameTxt.setError("Please enter your name");
            nameTxt.requestFocus();
            return;
        }
        textView.setText("Your Name is : "+name);
        Toast.makeText(MainActivity.this, "Button is clicked\n and\nYour Name is : "+name, Toast.LENGTH_SHORT).show();
    }
    public void showCK(View v){
        StringBuilder stringBuilder = new StringBuilder();
        if(ck1.isChecked()){
            String value = ck1.getText().toString();
            stringBuilder.append(value+" is ordered\n");
        }if(ck2.isChecked()){
            String value = ck2.getText().toString();
            stringBuilder.append(value+" is ordered\n");
        }if(ck3.isChecked()){
            String value = ck3.getText().toString();
            stringBuilder.append(value+" is ordered\n");
        }
        show_ck.setText(stringBuilder);

    }
    public void nextActivity(View v){
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        alertdialogbuilder = new AlertDialog.Builder(this);
        alertdialogbuilder.setTitle(R.string.alert_name);
        alertdialogbuilder.setMessage(R.string.alert_msg);
        alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alertdialogbuilder.create();
        alertDialog.show();

    }
}
