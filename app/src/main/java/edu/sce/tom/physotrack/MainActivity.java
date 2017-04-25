package edu.sce.tom.physotrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btn_personal_details_On_click(View v){
        Intent i = new Intent(MainActivity.this, PersonalDetails.class);
        startActivity(i);
    }

    public void btn_medical_information_On_click(View v){
        Intent i = new Intent(MainActivity.this, Medical_information.class);
        startActivity(i);
    }
    public void btn_previous_analysis_On_click(View v) {
        Intent i = new Intent(MainActivity.this, PreviousAnalysis.class);
        startActivity(i);
    }
    public void btn_new_photo_On_click(View v){
        Intent i = new Intent(MainActivity.this, NewPhoto.class);
        startActivity(i);
    }

}
