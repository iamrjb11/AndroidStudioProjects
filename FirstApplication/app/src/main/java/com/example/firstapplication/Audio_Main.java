package com.example.firstapplication;
import android.Manifest;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.NullPointerException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Audio_Main extends AppCompatActivity {

    Context context;

    public static final int RUNTIME_PERMISSION_CODE = 7;

    String[] items;

    private ListView myListViewForSongs;

    List<String> ListElementsArrayList ;

    ArrayAdapter<String> adapter ;
    ArrayList<File> mySongs;

    ContentResolver contentResolver;

    Cursor cursor;
    int songDuration;

    Uri uri;
    Thread updateseekBar;
    static MediaPlayer myMediaPlayer;

    Button button;
    private Button btn_next,btn_previous,btn_pause;
    private TextView songName;
    SeekBar songSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_audio__main);

        myListViewForSongs = findViewById(R.id.mySongListView);
        songName = findViewById(R.id.song_name);

        runtimePermission();




    }
    public ArrayList<File> findSong(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findSong(singleFile));
            }
            else{
                if(singleFile.getName().endsWith(".mp3")){
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }
    void display(){
        mySongs = findSong(Environment.getExternalStorageDirectory());
        items = new String[mySongs.size()];

        for(int i=0;i<mySongs.size();++i){
            items[i]=mySongs.get(i).getName().toString().replace(".mp3","");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        myListViewForSongs.setAdapter(adapter);

        myListViewForSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String getSong_name = myListViewForSongs.getItemAtPosition(i).toString();

                updateseekBar = new Thread(){

                    @Override
                    public void run(){
                        //super.run();
                       int totalDuration = myMediaPlayer.getDuration();
                       songDuration = totalDuration;
                        int currentPosition = 0;
                        while (currentPosition<totalDuration){
                            try{
                                sleep(500);
                                currentPosition = myMediaPlayer.getCurrentPosition();
                                songSeekBar.setProgress(currentPosition);


                            }
                            catch (InterruptedException e){
                                //e.printStackTrace();
                            }
                        }

                    }
                };

                if(myMediaPlayer!=null){
                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                }



                songName.setText(getSong_name);
                songName.setSelected(true);
                int pos = i;
                Uri u = Uri.parse(mySongs.get(i).toString());
                //Toast.makeText(Audio_Main.this, ""+u, Toast.LENGTH_SHORT).show();
                myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                Toast.makeText(Audio_Main.this, ""+myMediaPlayer.getDuration(), Toast.LENGTH_SHORT).show();
                myMediaPlayer.start();
//                songSeekBar.setMax(100);
//                updateseekBar.start();
//
//                songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                        //myMediaPlayer.seekTo(i);
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//                        myMediaPlayer.seekTo(seekBar.getProgress());
//
//                    }
//                });


            }
        });
    }
    // Creating Runtime permission function.
    public void runtimePermission(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        display();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }


    public void GetAllMediaMp3Files(){

        contentResolver = context.getContentResolver();

        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        cursor = contentResolver.query(
                uri, // Uri
                null,
                null,
                null,
                null
        );

        if (cursor == null) {

            Toast.makeText(Audio_Main.this,"Something Went Wrong.", Toast.LENGTH_LONG);

        } else if (!cursor.moveToFirst()) {

            Toast.makeText(Audio_Main.this,"No Music Found on SD Card.", Toast.LENGTH_LONG);

        }
        else {

            int Title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            //Getting Song ID From Cursor.
            //int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);

            do {

                // You can also get the Song ID using cursor.getLong(id).
                //long SongID = cursor.getLong(id);

                String SongTitle = cursor.getString(Title);

                // Adding Media File Names to ListElementsArrayList.
                ListElementsArrayList.add(SongTitle);

            } while (cursor.moveToNext());
        }
    }





}