package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer extends AppCompatActivity {

    Uri uri;
    Thread updateseekBar;
    static MediaPlayer myMediaPlayer;
    private ListView myListViewForSongs;
    Button button;
    private Button btn_next,btn_previous,btn_pause;
    private TextView songName;
    ArrayList<File> mySongs;
    SeekBar songSeekBar;
    String songN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        myListViewForSongs = findViewById(R.id.mySongListView);
        songName = findViewById(R.id.song_name);


        updateseekBar = new Thread(){

            @Override
            public void run(){
                super.run();
                int totalDuration = myMediaPlayer.getDuration();

                int currentPosition = 0;
                while (currentPosition<totalDuration){
                    try{
                        sleep(500);
                        currentPosition = myMediaPlayer.getCurrentPosition();
                        songSeekBar.setProgress(currentPosition);


                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        };

        if(myMediaPlayer!=null){
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }
        Intent i = getIntent();
        Bundle bundle =i.getExtras();
        mySongs =(ArrayList) bundle.getParcelableArrayList("songs");


        String getSong_name = i.getStringExtra("songname");



        songName.setText(getSong_name);
        songName.setSelected(true);
        int pos = bundle.getInt("position",0);

        //Uri u = Uri.parse(R.raw.r);
        myMediaPlayer = MediaPlayer.create(AudioPlayer.this,R.raw.r);

        myMediaPlayer.start();
        songSeekBar.setMax(myMediaPlayer.getDuration());
        updateseekBar.start();
        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myMediaPlayer.seekTo(seekBar.getProgress());

            }
        });


    }
}
