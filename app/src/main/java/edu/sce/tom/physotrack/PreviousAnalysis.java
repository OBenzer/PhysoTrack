package edu.sce.tom.physotrack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.sce.tom.physotrack.Algorithm.ChartDataExp;
import edu.sce.tom.physotrack.Algorithm.ImageResultViewer;
import edu.sce.tom.physotrack.Algorithm.LandmarksAnalyzerViewer;
import edu.sce.tom.physotrack.DataBase.DatabaseHelper;

import static edu.sce.tom.physotrack.Utils.EYEBROWRAISED_EXP;
import static edu.sce.tom.physotrack.Utils.EYECLOSED_EXP;
import static edu.sce.tom.physotrack.Utils.KISS_EXP;
import static edu.sce.tom.physotrack.Utils.NATURAL_EXP;
import static edu.sce.tom.physotrack.Utils.SMILE_EXP;
import static edu.sce.tom.physotrack.Utils.THERAPIST_MAIL;
import static edu.sce.tom.physotrack.Utils.UPPERLIPRAISED_EXP;
import static edu.sce.tom.physotrack.Utils.USER_DETAILS_SP_FILE;
import static edu.sce.tom.physotrack.Utils.USER_NAME;


public class PreviousAnalysis extends AppCompatActivity {

    private Chart chart;
    private ChartDataExp smileData=null;
    private ChartDataExp kissData=null;
    private ChartDataExp blanklyData=null;
    private ChartDataExp eyeBrowRaisedData=null;
    private ChartDataExp eyesClosedData=null;
    private ChartDataExp rabbitData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_analysis);

        chart = (Chart)findViewById(R.id.chart);

        //Adding Dropdown Values//
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.add(KISS_EXP);
        spinnerAdapter.add(SMILE_EXP);
        spinnerAdapter.add(NATURAL_EXP);
        spinnerAdapter.add(EYEBROWRAISED_EXP);
        spinnerAdapter.add(EYECLOSED_EXP);
        spinnerAdapter.add(UPPERLIPRAISED_EXP);
        spinnerAdapter.notifyDataSetChanged();

        //Retrives all the data from the database and insert it to the chart data objects//
        DatabaseHelper db = new DatabaseHelper(this);
        smileData = new ChartDataExp(db.getImageResultFromDBByExpression(SMILE_EXP));
        kissData = new ChartDataExp(db.getImageResultFromDBByExpression(KISS_EXP));
        blanklyData = new ChartDataExp(db.getImageResultFromDBByExpression(NATURAL_EXP));
        eyeBrowRaisedData = new ChartDataExp(db.getImageResultFromDBByExpression(EYEBROWRAISED_EXP));
        eyesClosedData = new ChartDataExp(db.getImageResultFromDBByExpression(EYECLOSED_EXP));
        rabbitData = new ChartDataExp(db.getImageResultFromDBByExpression(UPPERLIPRAISED_EXP));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                basicUserGraph(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do-Nothing
            }
        });
    }

    private void basicUserGraph(String Express) {
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        switch(Express) {
            case KISS_EXP:
                sets.add(kissData.getOuterMouthAreaSet());
                break;
            case SMILE_EXP:
                sets.add(smileData.getMouthAngleSet());
                sets.add(smileData.getMouthDisstanceSet());
                break;
            case NATURAL_EXP:
                sets.add(blanklyData.getEyeAreaSet());
                sets.add(blanklyData.getMouthAngleSet());
                break;
            case UPPERLIPRAISED_EXP:
                sets.add(rabbitData.getInnerMouthAreaSet());
                break;
            case EYEBROWRAISED_EXP:
                sets.add(eyeBrowRaisedData.getEyeToBrowDisstanceSet());
                break;
            case EYECLOSED_EXP:
                sets.add(eyesClosedData.getEyeAreaSet());
                break;
            default:
                sets=null;
        }
        updateChart(sets);
    }

    private void updateChart(ArrayList<ILineDataSet> sets){
        if(sets!=null) {
            ArrayList<ILineDataSet> allDataSets = new ArrayList<ILineDataSet>();

            for(int i=0; i<sets.size(); i++){
                allDataSets.add(sets.get(i));
            }

            chart.setData(new LineData(allDataSets));
            chart.invalidate(); // refresh
        }
    }

    public void btn_send_therapist_on_click(View v) throws IOException, InterruptedException {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(USER_DETAILS_SP_FILE, MODE_PRIVATE);

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
        String filename = "analysis.txt";

        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),filename);
        Uri path = Uri.fromFile(filelocation);
        FileWriter writer = new FileWriter(filelocation);

        DatabaseHelper db=new DatabaseHelper(this);

        ArrayList<ImageResultViewer> arr= new ArrayList<>();
        arr = db.getAllImageResultFromDB();
        ArrayList<LandmarksAnalyzerViewer> arrLandmarks=new ArrayList<>();
        arrLandmarks=db.getAllMetricsFromDB();

            String email = pref.getString(THERAPIST_MAIL, "");// getting therapist email
            String name = pref.getString(USER_NAME, "");//getting name of user
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            //create a txt file and write stuff and send to physiotherapist mail

            intent.setType("plain/text");
            //  writer.append("fnu");

                for(int i=0; i<arr.size();i++)
                {
                    writer.append(arr.get(i).toString());
                    writer.append(arrLandmarks.get(i).toString());
                }

            writer.close();
            intent.putExtra(Intent.EXTRA_SUBJECT, "Analysis of " + name + " by PhysioTrack");
            intent.putExtra(Intent.EXTRA_TEXT, "hi ,\nhere is my analysis by image attached");
            intent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(intent, ""));
    }

}
