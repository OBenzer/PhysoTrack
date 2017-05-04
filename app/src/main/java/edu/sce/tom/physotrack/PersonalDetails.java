package edu.sce.tom.physotrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class PersonalDetails extends AppCompatActivity {

    SharedPreferences userD;
    SharedPreferences.Editor editor;
    EditText name, therapistMail;
    RadioButton left, right;
    String strUserName, strTherapistMail, strSelectedSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        userD = getSharedPreferences(MainActivity.USER_DETAILS_SP_FILE, Context.MODE_PRIVATE);
        editor = userD.edit();
        name = (EditText) findViewById(R.id.txt_username);
        therapistMail = (EditText) findViewById(R.id.txt_therapist_mail);
        left = (RadioButton) findViewById(R.id.radio_left);
        right = (RadioButton) findViewById(R.id.radio_right);


        init();
    }

    private void init() {
        strUserName = userD.getString(MainActivity.USER_NAME, "");
        name.setText(strUserName);
        strTherapistMail = userD.getString(MainActivity.THERAPIST_MAIL, "");
        therapistMail.setText(strTherapistMail);
        strSelectedSide = userD.getString(MainActivity.SIDE, "");
        if (strSelectedSide.equalsIgnoreCase("Left")) {
            left.setChecked(true);
        } else if (strSelectedSide.equalsIgnoreCase("Right")) {
            right.setChecked(true);
        }


    }

    public void SubmitClicked(View view) {

        String SideSelected;

        EditText user_name = (EditText) findViewById(R.id.txt_username);
        EditText therapist_mail = (EditText) findViewById(R.id.txt_therapist_mail);
        String strUserName = name.getText().toString();

        //check if name was entered
        if (TextUtils.isEmpty(strUserName)) {
            name.setError(getString(R.string.insert_user_name));
            return;
        }
        if (left.isChecked()) {
            SideSelected = "Left";
        } else {
            SideSelected = "Right";
        }


        // Enter All Information to DB
        saveData(user_name.getText().toString(), SideSelected, therapist_mail.getText().toString());

    }

    private void saveData(String userName, String side, String therapistMail) {
        editor.putString(MainActivity.USER_NAME, userName);
        editor.putString(MainActivity.SIDE, side);
        editor.putString(MainActivity.THERAPIST_MAIL, therapistMail);
        editor.commit();
        Toast.makeText(this, "Details entered successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}