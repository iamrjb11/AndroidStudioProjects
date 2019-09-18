package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private AlertDialog.Builder alertdialogbuilder;

    private RatingBar ratingBar;
    private TextView textView,dateTxt,timeTxt;
    private Button button,timeBtn;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    SQLiteHelper sqLiteHelper;
    private int curDay,curMonth,curYear,curHour,curMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //sqLiteHelper = new SQLiteHelper(this,"db_food.sqlite",null,1);

        Cursor cursor = sqLiteHelper.getData("select * from food");

        if(cursor.moveToNext()){
            Toast.makeText(this, "Has", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Not", Toast.LENGTH_SHORT).show();
        }

        //Rating
        ratingBar = findViewById(R.id.ratingbar);
        textView = findViewById(R.id.ratingTxt);
        //Date
        dateTxt = findViewById(R.id.dateID);
        button = findViewById(R.id.pickerBtn);
        //Time
        timeTxt = findViewById(R.id.timeTxt);
        timeBtn = findViewById(R.id.timeBtn);



        //Datapicker
        DatePicker datePicker = new DatePicker(this);
        curDay = datePicker.getDayOfMonth();
        curMonth = datePicker.getMonth();
        curYear = datePicker.getYear();
        dateTxt.setText("Current Date : "+curDay+"/"+(curMonth+1)+"/"+curYear);

        //TimePicker
        TimePicker timePicker = new TimePicker(this);
        curHour = timePicker.getCurrentHour();
        curMinute = timePicker.getCurrentMinute();

        timeTxt.setText("Current Time : "+curHour+" : "+curMinute);

        //Rating
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                textView.setText("Value : "+v);
            }
        });
    }

    public void DatePicker(View v){


        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateTxt.setText("Selected Date : "+i2+"/"+(i1+1)+"/"+i);
                    }
                }
                , curYear, curMonth, curDay

        );
        datePickerDialog.show();
    }

    public void TimePicker(View v){
        alertdialogbuilder = new AlertDialog.Builder(this);
        alertdialogbuilder.setTitle(R.string.alert_name);
        alertdialogbuilder.setMessage(R.string.alert_msg);
        alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showTime();
            }
        });
        alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        AlertDialog alertDialog = alertdialogbuilder.create();
        alertDialog.show();




    }
    protected void showTime(){
        timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        timeTxt.setText("Seleted Time : "+i+" : "+i1);
                    }
                },curHour,curMinute,false
        );
        timePickerDialog.show();
    }


}
