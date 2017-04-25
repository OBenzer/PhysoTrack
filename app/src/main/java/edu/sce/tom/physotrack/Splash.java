package edu.sce.tom.physotrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onCreate(savedInstanceState);
       try {

            sleep(4000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finish();
    }
}
