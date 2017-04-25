package edu.sce.tom.physotrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class PersonalDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
    }

    public void SubmitClicked(View view) {

        String SideSelcted = "";
        RadioButton left = (RadioButton) findViewById(R.id.radio_left);
        EditText user_name = (EditText) findViewById(R.id.txt_username);
        EditText therapist_mail = (EditText) findViewById(R.id.txt_therapist_mail);

        if (left.isChecked()) {
            SideSelcted = "Left";
        } else {
            SideSelcted = "Right";
        }

        // just for checking
        Toast.makeText(this, SideSelcted + " Side Selcted" , Toast.LENGTH_SHORT).show();
        Toast.makeText(this, user_name.getText().toString(), Toast.LENGTH_SHORT).show();

        // Enter All Information to DB


    }
}