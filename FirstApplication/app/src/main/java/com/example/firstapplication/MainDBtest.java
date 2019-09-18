package com.example.firstapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainDBtest extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    private EditText nameTxt;
    private Button saveBtn,imgPickerBtn;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dbtest);
        nameTxt = findViewById(R.id.nameTT);
        saveBtn = findViewById(R.id.cBtn);

        sqLiteHelper = new SQLiteHelper(this);
        final SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTxt.getText().toString();

                sqLiteHelper.insertData(name,imageViewToByte(imageView));
            }
        });

        Cursor cursor= sqLiteHelper.getData("select * from food_info");
        if(cursor.moveToNext()){
            Toast.makeText(this, "Has : "+cursor.getString(1), Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Not", Toast.LENGTH_SHORT).show();
        imgPickerBtn = findViewById(R.id.pickImage);
        imageView = findViewById(R.id.imageView);

        imgPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
    }

    private byte[] imageViewToByte(ImageView img){
        Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==1 && resultCode == RESULT_OK){
            final List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData =data.getClipData();

            if(clipData != null){
                for(int i=0;i<clipData.getItemCount();i++){
                    Uri imageUri = clipData.getItemAt(i).getUri();


                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Toast.makeText(this, ""+is, Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        bitmaps.add(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                Uri imageUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    bitmaps.add(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (final Bitmap b: bitmaps){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(b);
                                //Toast.makeText(Image_Picker.this, ""+b, Toast.LENGTH_SHORT).show();
                            }
                        });
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}

