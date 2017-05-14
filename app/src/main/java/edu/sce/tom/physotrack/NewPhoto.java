package edu.sce.tom.physotrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class NewPhoto extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_photo);
    }

    // when btn_photo_guide clicked move to PhotoGuide activity
    public void btn_photo_guide_On_click(View v) {
        Intent i = new Intent(NewPhoto.this, PhotoGuide.class);
        startActivity(i);
    }

    // when btn_new_session clicked move to NewSession activity
    public void btn_new_session_On_click(View v) {
        if (true)/*!checkSessionValidation()*/ {
            Intent i = new Intent(NewPhoto.this, NewSession.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Session can only be recorded once a day, please try again tomorrow", Toast.LENGTH_SHORT).show();
        }
    }

    //checks if the user already took pictures today
    private boolean checkSessionValidation() {
        SharedPreferences sharedPref = getSharedPreferences(MainActivity.NEW_SESSION_SP_FILE, Context.MODE_PRIVATE);
        String todaysDate = sharedPref.getString(MainActivity.TODAYS_DATE, "");
        String todaysRealDate = Utils.todaysDateToString();
        return todaysDate.equalsIgnoreCase(todaysRealDate);
    }

}
