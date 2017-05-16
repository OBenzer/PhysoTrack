package edu.sce.tom.physotrack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.sce.tom.physotrack.Algorithm.ImageResultViewer;
import edu.sce.tom.physotrack.DataBase.DatabaseHelper;

import static edu.sce.tom.physotrack.MainActivity.THERAPIST_MAIL;
import static edu.sce.tom.physotrack.MainActivity.USER_NAME;

public class PreviousAnalysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_analysis);


        // Working With the Graph //
        GraphView graph = (GraphView) findViewById(R.id.PreviousAnalysisGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 80),
                new DataPoint(2, 83),
                new DataPoint(3, 90),
                new DataPoint(4, 70),
                new DataPoint(5, 95)
        });
        graph.addSeries(series);
        // Finish Working With Graph //

    }


    public void btn_send_therapist_on_click(View v) throws IOException, InterruptedException {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.USER_DETAILS_SP_FILE, MODE_PRIVATE);

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
        String filename = "analysis.txt";

        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),filename);
        Uri path = Uri.fromFile(filelocation);
        FileWriter writer = new FileWriter(filelocation);

        DatabaseHelper db=new DatabaseHelper(this);


        ArrayList<ImageResultViewer> arr= new ArrayList<>();
        arr = db.getAllImageResultFromDB();
        

            String email = pref.getString(THERAPIST_MAIL, "");// getting thrapist email
            String name = pref.getString(USER_NAME, "");//getting name of user
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            //create a txt file and write blabla and send to pysiotrapist mail

            intent.setType("plain/text");

            intent.setType("plain/text");
            //  writer.append("fnu");
            for (ImageResultViewer ls : arr) {
                writer.append(ls.toString());

            }
            writer.close();
            intent.putExtra(Intent.EXTRA_SUBJECT, "Analysis of " + name + " by PhysoTrack");
            intent.putExtra(Intent.EXTRA_TEXT, "hi ,\nhere is my analysis by image attached");
            intent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(intent, ""));

    }
}
