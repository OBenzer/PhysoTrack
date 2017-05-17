package edu.sce.tom.physotrack;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Medical_information extends AppCompatActivity {

    TextToSpeech toSpeech;
    int result ;
    TextView textView;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_information);

        textView = (TextView)findViewById(R.id.txt_medical_information);
        toSpeech = new TextToSpeech(Medical_information.this,new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status==TextToSpeech.SUCCESS)
                {

                    result=toSpeech.setLanguage(Locale.ENGLISH);
                }
                else{

                    Toast.makeText(Medical_information.this, "Not Supported in Device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // when btn_youtube_exercises clicked move to youtube_information activity
    public void btn_youtube_exercises_On_click(View v){
        Intent i = new Intent(Medical_information.this, youtube_information.class);
        startActivity(i);
    }

    // Text To Speech method , reading the medical text to the user
    public void TTS(View view){

        switch (view.getId()){

            case R.id.btn_medical_info_Reader:
                if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(this, "NOT SUPPORTED IN SWITCH CASE", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    text = textView.getText().toString();
                    toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
        }
    }


    // responsible to end the reading when finish or when changing activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(toSpeech!=null){

            toSpeech.stop();
            toSpeech.shutdown();
        }
    }






}
