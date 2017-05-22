package edu.sce.tom.physotrack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static edu.sce.tom.physotrack.Utils.ACUTE;
import static edu.sce.tom.physotrack.Utils.CHRONIC;
import static edu.sce.tom.physotrack.Utils.SIDE;
import static edu.sce.tom.physotrack.Utils.THERAPIST_MAIL;
import static edu.sce.tom.physotrack.Utils.USER_DETAILS_SP_FILE;
import static edu.sce.tom.physotrack.Utils.USER_NAME;
import static edu.sce.tom.physotrack.Utils.PATIENT_TYPE;

public class PersonalDetails extends AppCompatActivity {

    SharedPreferences userD;
    SharedPreferences.Editor editor;
    EditText name, therapistMail;
    RadioButton left, right;
    RadioButton chronic, acute;
    String strUserName, strTherapistMail, strSelectedSide, patientType;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        userD = getSharedPreferences(USER_DETAILS_SP_FILE, Context.MODE_PRIVATE);
        editor = userD.edit();
        name = (EditText) findViewById(R.id.txt_user_name);
        therapistMail = (EditText) findViewById(R.id.txt_therapist_mail);
        left = (RadioButton) findViewById(R.id.radio_left);
        right = (RadioButton) findViewById(R.id.radio_right);
        chronic = (RadioButton) findViewById(R.id.radio_Chronic);
        acute = (RadioButton) findViewById(R.id.radio_Acute);

        init();
    }

    private void init() {
        strUserName = userD.getString(USER_NAME, "");
        name.setText(strUserName);
        strTherapistMail = userD.getString(THERAPIST_MAIL, "");
        therapistMail.setText(strTherapistMail);

        strSelectedSide = userD.getString(SIDE, "");
        if (strSelectedSide.equalsIgnoreCase("Left")) {
            left.setChecked(true);
        } else if (strSelectedSide.equalsIgnoreCase("Right")) {
            right.setChecked(true);
        }

        patientType = userD.getString(PATIENT_TYPE,"");
        if(patientType.equalsIgnoreCase(ACUTE)){
            acute.setChecked(true);
        } else if(patientType.equalsIgnoreCase(CHRONIC)) {
            chronic.setChecked(true);
        }
    }

    public void SubmitClicked(View view) {

        String SideSelected;
        String patientType;

        EditText user_name = (EditText) findViewById(R.id.txt_user_name);
        EditText therapist_mail = (EditText) findViewById(R.id.txt_therapist_mail);
        String strUserName = name.getText().toString();

        //check if name was entered
        if (TextUtils.isEmpty(strUserName)) {
            name.setError(getString(R.string.user_name));
            return;
        }

        if(((RadioGroup)findViewById(R.id.radioSide)).getCheckedRadioButtonId()==-1) {   //No One Checked
            Toast.makeText(this, "Choose Treated Side..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (left.isChecked()) {
            SideSelected = "Left";
        } else {
            SideSelected = "Right";
        }

        if(((RadioGroup)findViewById(R.id.radioPatient)).getCheckedRadioButtonId()==-1) {   //No One Checked
            Toast.makeText(this, "Choose Patient Type..", Toast.LENGTH_SHORT).show();
            return;
        }
        if(acute.isChecked()) {
            patientType = ACUTE;
        } else {
            patientType = CHRONIC;
        }

        // Enter All Information to DB
        saveData(user_name.getText().toString(), SideSelected, patientType, therapist_mail.getText().toString());
    }

    private void saveData(String userName, String side, String patientType, String therapistMail) {
        editor.putString(USER_NAME, userName);
        editor.putString(SIDE, side);
        editor.putString(THERAPIST_MAIL, therapistMail);
        editor.putString(PATIENT_TYPE, patientType);
        editor.commit();
        Toast.makeText(this, "Details entered successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}