package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView dateTxt,dateTxt2,dateTxt3,dateTxt4,showAge;
    private Button button,button2,showAgeB;
    private DatePickerDialog datePickerDialog,datePickerDialog2;

    private int curDay,curMonth,curYear,oldDay,oldMonth,oldYear,Day,Month,Year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Date
        dateTxt = findViewById(R.id.dateID);
        dateTxt2 = findViewById(R.id.dateID2);
        dateTxt3 = findViewById(R.id.dateID3);
        dateTxt4 = findViewById(R.id.dateID4);
        showAge = findViewById(R.id.showAgeTxt);
        button = findViewById(R.id.pickerBtn);
        button2 = findViewById(R.id.pickerBtn2);
        showAgeB = findViewById(R.id.showAgeBtn);

        DatePicker datePicker;
        datePicker = new DatePicker(this);
        curDay = datePicker.getDayOfMonth();
        curMonth = datePicker.getMonth();
        curMonth=curMonth+1;
        curYear = datePicker.getYear();
        String month;
        month=getMonth(curMonth);

        dateTxt3.setText(curDay+"-"+curMonth+"-"+curYear);
        dateTxt4.setText(curDay+"-"+month+"-"+curYear);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                dateTxt.setText(i2+"-"+(i1+1)+"-"+i);
                                String month=getMonth(i1+1);
                                dateTxt2.setText(i2+"-"+month+"-"+i);
                                oldDay=i2;
                                oldMonth=(i1+1);
                                oldYear=i;
                            }
                        }
                        , curYear, curMonth, curDay

                );
                datePickerDialog.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog2 = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                dateTxt3.setText(i2+"-"+(i1+1)+"-"+i);
                                String month=getMonth(i1+1);
                                dateTxt4.setText(i2+"-"+month+"-"+i);
                                curDay=i2;
                                curMonth=(i1+1);
                                curYear=i;
                            }
                        }
                        , curYear, curMonth, curDay

                );
                datePickerDialog2.show();
            }
        });
        showAgeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(curDay > oldDay){
                    Day= curDay-oldDay;
                    if(curMonth > oldMonth){
                        Month=curMonth-oldMonth;
                        Year=curYear-oldYear;
                    }
                    else{
                        Month=curMonth+12;
                        //showAge.setText("Mon : "+Month);
                        Month=Month-oldMonth;

                        Year=curYear-1;
                        Year=Year-oldYear;

                    }

                }
                else{
                    Day= curDay+31;
                    Day=Day-oldDay;
                    if(curMonth > oldMonth){
                        Month=curMonth-1;
                        Month=Month-oldMonth;
                        Year=curYear-oldYear;
                    }
                    else{
                        Month=curMonth-1+12;
                        Month=Month-oldMonth;
                        Year=curYear-1;
                        Year=Year-oldYear;
                    }

                }

                int totalMonth=(Year*12)+Month;
                int totalDay=totalMonth*30;
                showAge.setText(Year+" Y - "+Month+" M - "+Day+" D\nor "+totalMonth+" M - "+Day+" D");
            }
        });

    }
    public String getMonth(int curMonth){
        String month;
        if(curMonth==1) month="Jan";
        else if(curMonth==2) month="Feb";
        else if(curMonth==3) month="Mar";
        else if(curMonth==4) month="Apr";
        else if(curMonth==5) month="May";
        else if(curMonth==6) month="Jun";
        else if(curMonth==7) month="Jul";
        else if(curMonth==8) month="Aug";
        else if(curMonth==9) month="Sep";
        else if(curMonth==10) month="Oct";
        else if(curMonth==11) month="Nov";
        else  month="Dec";

        return month;
    }
}
