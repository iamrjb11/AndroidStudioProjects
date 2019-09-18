package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private TextView textView;
    private EditText editTextNum;
    private EditText editText[];
    private Button submitBtn;
    private int num=0;
    SQLiteHelper sqLiteHelper;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        linearLayout = findViewById(R.id.layout);
        TextView dynamicTextView = new TextView(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dynamicTextView.setText(" Enter Number ");
        editTextNum = new EditText(this);
        editTextNum.setHint("Enter num");
        editTextNum.setInputType(InputType.TYPE_CLASS_NUMBER);
        submitBtn = new Button(Main5Activity.this);

        Button button = new Button(this);
        button.setText("Click");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<num;i++){

                    linearLayout.removeView(findViewById(i));
                }

                linearLayout.removeView(findViewById(21));

                num = Integer.parseInt(editTextNum.getText().toString());
                editTextNum.getText().clear(); //clear textbox


                 editText = new EditText[num];
                for (int i=0;i<num;i++){
                    editText[i] = new EditText(Main5Activity.this);
                    editText[i].setId(i);
                    editText[i].setHint("Enter");
                    editText[i].setWidth(350);
                    linearLayout.addView(editText[i],layoutParams);

                }

                submitBtn.setText("Submit");
                submitBtn.setId(21);
                linearLayout.addView(submitBtn,layoutParams);
                Toast.makeText(Main5Activity.this, "Clicked : "+num, Toast.LENGTH_SHORT).show();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=0;i<num;i++){
                    stringBuilder.append(editText[i].getText().toString()+"\n");
                }

                textView = new TextView(Main5Activity.this);
                textView.setText(stringBuilder);
                linearLayout.addView(textView,layoutParams);

            }
        });



        linearLayout.addView(dynamicTextView,layoutParams);
        linearLayout.addView(editTextNum,layoutParams);

        linearLayout.addView(button,layoutParams);
    }
}
