package edu.sce.tom.physotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static edu.sce.tom.physotrack.MainActivity.THERAPIST_MAIL;

public class PreviousAnalysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_analysis);


        // Working With the Graph //
        GraphView graph = (GraphView) findViewById(R.id.PreviousAnalysisGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);

        // Finish Working With Graph //
    }



    public void btn_send_therapist_on_click(View v) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.USER_DETAILS_SP_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String email=pref.getString(THERAPIST_MAIL,"");// getting String
         Toast.makeText(this, email , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }
}
