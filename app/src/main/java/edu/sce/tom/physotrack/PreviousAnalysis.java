package edu.sce.tom.physotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static edu.sce.tom.physotrack.MainActivity.THERAPIST_MAIL;

public class PreviousAnalysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_analysis);
    }

    public void btn_send_therapist_on_click(View v) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.USER_DETAILS_SP_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String email=pref.getString(THERAPIST_MAIL,"");         // getting String

         Toast.makeText(this, email , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }
}
