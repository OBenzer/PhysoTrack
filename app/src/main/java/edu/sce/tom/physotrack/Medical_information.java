package edu.sce.tom.physotrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Medical_information extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_information);
    }

    public void btn_youtube_exercises_On_click(View v){
        Intent i = new Intent(Medical_information.this, youtube_information.class);
        startActivity(i);
    }
}
