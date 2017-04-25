package edu.sce.tom.physotrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewPhoto extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);
    }

    public void btn_photo_guide_On_click(View v){
        Intent i = new Intent(NewPhoto.this, PhotoGuide.class);
        startActivity(i);
    }

    public void btn_new_session_On_click(View v){
        Intent i = new Intent(NewPhoto.this, NewSession.class);
        startActivity(i);
    }

}
