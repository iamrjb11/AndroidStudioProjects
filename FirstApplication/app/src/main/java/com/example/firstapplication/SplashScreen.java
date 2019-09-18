package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressID);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                appStart();
            }
        });
        thread.start();


    }

    public void doWork(){
        for (progressV=1;progressV <=100 ;progressV++){
            try {
                Thread.sleep(50);
                progressBar.setProgress(progressV);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void appStart(){
        Intent intent =new Intent(this,Main5Activity.class);
        startActivity(intent);
        finish();
    }
}
