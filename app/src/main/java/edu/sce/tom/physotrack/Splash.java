package edu.sce.tom.physotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.sce.tom.physotrack.Algorithm.SesRunSingletone;

import static edu.sce.tom.physotrack.Utils.USER_DETAILS_SP_FILE;
import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences(USER_DETAILS_SP_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean firstUse = settings.getBoolean("firstUse", true);
        Intent intent;

        if (firstUse) {
            intent = new Intent(this, PersonalDetails.class);
            editor.putBoolean("firstUse", false);
            editor.apply();
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        super.onCreate(savedInstanceState);
        try {
            //Creates the instance on the singletone//
            SesRunSingletone.getInstance(getApplicationContext());
            sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finish();
    }
}
