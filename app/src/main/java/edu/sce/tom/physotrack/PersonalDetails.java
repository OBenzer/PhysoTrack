package edu.sce.tom.physotrack;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class PersonalDetails extends AppCompatActivity {

    //name of the shared preferences file and keys
    private static final String USER_DETAILS_SP_FILE="userDetails";
    public static final String USER_NAME = "userName";
    public static final String SIDE = "defSide";
    public static final String THERAPIST_MAIL = "therapistMail";


    SharedPreferences userD;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        userD = getSharedPreferences(USER_DETAILS_SP_FILE, Context.MODE_PRIVATE);
        editor=userD.edit();
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


        // Enter All Information to DB
        saveData(user_name.getText().toString(),SideSelcted,therapist_mail.getText().toString());

    }

    private void saveData(String userName,String side,String therapistMail) {
        editor.putString(USER_NAME,userName);
        editor.putString(SIDE,side);
        editor.putString(THERAPIST_MAIL,therapistMail);
        editor.commit();
        Toast.makeText(this, "Details entered successfully!", Toast.LENGTH_SHORT).show();
        finish();

    }


}