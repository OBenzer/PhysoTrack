package edu.sce.tom.physotrack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;

public class NewSession extends AppCompatActivity {
    final int SMILE=1;
    final int KISS=2;
    final int EYECLOSE=3;
    final int RABBIT=4;
    final int BLANCLY=5;
    final int BROWLIFT=6;

    private Button kiss_info;
    private PopupWindow popupwindow;
    private LayoutInflater layoutinflater;
    private RelativeLayout relative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);
         kiss_info=(Button)findViewById(R.id.btn_ikiss);
        Button smile_info=(Button)findViewById(R.id.btn_ismile);
        Button eyeclose_info=(Button)findViewById(R.id.btn_ieyeclose);
        Button blancly_info=(Button)findViewById(R.id.btn_iblankly);
        Button rabbit_info=(Button)findViewById(R.id.btn_irabbit);
        Button eyebrowlift_info=(Button)findViewById(R.id.btn_ibrowlift);
        relative =(RelativeLayout)findViewById(R.id.relative2);
        kiss_info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                layoutinflater=(LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container=(ViewGroup)layoutinflater.inflate(R.layout.kissinfo,null);
                popupwindow=new PopupWindow(container,400,400,true);
                popupwindow.showAtLocation(relative, Gravity.NO_GRAVITY,500,500);
                Toast.makeText(NewSession.this, "Clicked On", Toast.LENGTH_SHORT).show();

                container.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent){
                        popupwindow.dismiss();
                        Toast.makeText(NewSession.this, "Dismiss me", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });
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
