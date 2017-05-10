package edu.sce.tom.physotrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //name of the shared preferences file and keys
    public static final String USER_DETAILS_SP_FILE="userDetails";
    public static final String USER_NAME = "userName";
    public static final String SIDE = "defSide";
    public static final String THERAPIST_MAIL = "therapistMail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    // when btn_personal_details clicked move to PersonalDetails activity
    public void btn_personal_details_On_click(View v){
        Intent i = new Intent(MainActivity.this, PersonalDetails.class);
        startActivity(i);
    }

    // when btn_medical_information clicked move to Medical_information activity
    public void btn_medical_information_On_click(View v){
        Intent i = new Intent(MainActivity.this, Medical_information.class);
        startActivity(i);
    }
    // when btn_previous_analysis clicked move to PreviousAnalysis activity
    public void btn_previous_analysis_On_click(View v) {
        Intent i = new Intent(MainActivity.this, PreviousAnalysis.class);
        startActivity(i);
    }
    // when btn_new_photo clicked move to NewPhoto activity
    public void btn_new_photo_On_click(View v){
        Intent i = new Intent(MainActivity.this, NewPhoto.class);
        startActivity(i);
    }

}
