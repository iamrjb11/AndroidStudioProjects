package com.example.firstapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    private AlertDialog.Builder alertdialogbuilder;

    private RatingBar ratingBar;
    private TextView textView,dateTxt,dateTxt2,timeTxt,showAge;
    private Button button,button2,timeBtn,showAgeB;
    private DatePickerDialog datePickerDialog,datePickerDialog2;
    private TimePickerDialog timePickerDialog;
    private int curDay,curMonth,curYear,curHour,curMinute,oldDay,oldMonth,oldYear,Day,Month,Year;
private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        //Rating
        ratingBar = v.findViewById(R.id.ratingbar);
        textView = v.findViewById(R.id.ratingTxt);
        //Date
        dateTxt = v.findViewById(R.id.dateID);
        dateTxt2 = v.findViewById(R.id.dateID2);
        showAge = v.findViewById(R.id.showAgeTxt);
        button = v.findViewById(R.id.pickerBtn);
        button2 = v.findViewById(R.id.pickerBtn2);
        showAgeB = v.findViewById(R.id.showAgeBtn);
        //Time
        timeTxt = v.findViewById(R.id.timeTxt);
        timeBtn = v.findViewById(R.id.timeBtn);



        //Datapicker
        //DatePicker datePicker = new DatePicker(BottomNavigation.this);
        DatePicker datePicker;
        datePicker = new DatePicker(v.getContext());
        curDay = datePicker.getDayOfMonth();
        curMonth = datePicker.getMonth();
        curMonth=curMonth+1;
        curYear = datePicker.getYear();
        dateTxt2.setText(curDay+"/"+curMonth+"/"+curYear);

        //TimePicker
        TimePicker timePicker = new TimePicker(v.getContext());


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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                dateTxt.setText(i2+"-"+(i1+1)+"-"+i);
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
                datePickerDialog2 = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                dateTxt2.setText(i2+"-"+(i1+1)+"-"+i);
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

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        timePickerDialog = new TimePickerDialog(v.getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                                        timeTxt.setText("Seleted Time : "+i+" : "+i1);
                                    }
                                },curHour,curMinute,false
                        );
                        timePickerDialog.show();
                    }
            });


        return v;
    }

}
