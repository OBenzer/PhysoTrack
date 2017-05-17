package edu.sce.tom.physotrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.sce.tom.physotrack.Algorithm.SesRunSingletone;
import edu.sce.tom.physotrack.Algorithm.SessionRunner;

public class SessionResult extends AppCompatActivity {

    private SessionRunner sessionRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_result);
        sessionRunner= SesRunSingletone.getInstance();
    }

    public void btn_finish_onclick(View v){
        sessionRunner.initialRunner();  //Initial the path, landmarks and all the object data.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
