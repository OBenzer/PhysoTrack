package edu.sce.tom.physotrack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class NewSession extends AppCompatActivity {
    final int SMILE=1;
    final int KISS=2;
    final int EYECLOSE=3;
    final int RABBIT=4;
    final int BLANCLY=5;
    final int BROWLIFT=6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        Bundle extras=intent.getExtras();
        Bitmap photo=(Bitmap)extras.get("data");


        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "kazar");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
    }

    public void btn_kiss_photo_On_click(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,11);

    }

    public void btn_smile_photo_On_click(View v) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,1);

    }

    public void btn_eyeClose_photo_On_click(View v) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

    }

    public void btn_blankly_photo_On_click(View v) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }

    public void btn_rabbit_photo_On_click(View v) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }

    public void btn_browLifts_photo_On_click(View v) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }

  /*public void session(View v){
        switch (v.getId()){
            case R.id.btn_kiss_photo:
        }
    }*/
}
