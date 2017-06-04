package edu.sce.tom.physotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.sce.tom.physotrack.Algorithm.ChartDataExp;
import edu.sce.tom.physotrack.Algorithm.ImageResultViewer;
import edu.sce.tom.physotrack.Algorithm.LandmarksAnalyzerViewer;
import edu.sce.tom.physotrack.DataBase.DatabaseHelper;

import static edu.sce.tom.physotrack.Utils.CHRONIC;
import static edu.sce.tom.physotrack.Utils.EYEBROWRAISED_EXP;
import static edu.sce.tom.physotrack.Utils.EYECLOSED_EXP;
import static edu.sce.tom.physotrack.Utils.KISS_EXP;
import static edu.sce.tom.physotrack.Utils.NATURAL_EXP;
import static edu.sce.tom.physotrack.Utils.PATIENT_TYPE;
import static edu.sce.tom.physotrack.Utils.SMILE_EXP;
import static edu.sce.tom.physotrack.Utils.THERAPIST_MAIL;
import static edu.sce.tom.physotrack.Utils.UPPERLIPRAISED_EXP;
import static edu.sce.tom.physotrack.Utils.USER_DETAILS_SP_FILE;
import static edu.sce.tom.physotrack.Utils.USER_NAME;


public class PreviousAnalysis extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final int EYEAREAINDEX= 0;
    private static final int EYETOBROWINDEX= 1;
    private static final int MOUTHANGLEINDEX= 2;
    private static final int MOUTHDISTINDEX= 3;
    private static final int OUTERMOUTHINDEX= 4;
    private static final int INNERMOUTHINDEX= 5;
    private static final int NUM_OF_METRICS = 6;

    private Chart chart;
    private ChartDataExp smileData = null;
    private ChartDataExp kissData = null;
    private ChartDataExp blanklyData = null;
    private ChartDataExp eyeBrowRaisedData = null;
    private ChartDataExp eyesClosedData = null;
    private ChartDataExp rabbitData = null;
    private ChartDataExp targetedData = null;   //For Chronic Patient Only
    private ILineDataSet[] dynamicSets;    //For Chronic Patient Only
    private SharedPreferences pref;
    private String patientType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_analysis);

        pref = getApplicationContext().getSharedPreferences(USER_DETAILS_SP_FILE, MODE_PRIVATE);
        chart = (LineChart) findViewById(R.id.chart);

        //Adding Dropdown Values//
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.add(KISS_EXP);
        spinnerAdapter.add(SMILE_EXP);
        spinnerAdapter.add(NATURAL_EXP);
        spinnerAdapter.add(EYEBROWRAISED_EXP);
        spinnerAdapter.add(EYECLOSED_EXP);
        spinnerAdapter.add(UPPERLIPRAISED_EXP);
        spinnerAdapter.notifyDataSetChanged();

        patientType = pref.getString(PATIENT_TYPE, "");

        if (patientType.equalsIgnoreCase(CHRONIC)) {  //Opens More Oprions With The
            dynamicSets = new ILineDataSet[NUM_OF_METRICS];

            CheckBox currentBox = (CheckBox) findViewById(R.id.box_eyeArea);
            currentBox.setVisibility(View.VISIBLE);
            currentBox.setOnCheckedChangeListener(this);

            currentBox = (CheckBox) findViewById(R.id.box_eyeToBrowDisstance);
            currentBox.setVisibility(View.VISIBLE);
            currentBox.setOnCheckedChangeListener(this);

            currentBox = (CheckBox) findViewById(R.id.box_innerMouthArea);
            currentBox.setVisibility(View.VISIBLE);
            currentBox.setOnCheckedChangeListener(this);

            currentBox = (CheckBox) findViewById(R.id.box_mouthAngle);
            currentBox.setVisibility(View.VISIBLE);
            currentBox.setOnCheckedChangeListener(this);

            currentBox = (CheckBox) findViewById(R.id.box_mouthDisstance);
            currentBox.setVisibility(View.VISIBLE);
            currentBox.setOnCheckedChangeListener(this);

            currentBox = (CheckBox) findViewById(R.id.box_outerMouthArea);
            currentBox.setVisibility(View.VISIBLE);
            currentBox.setOnCheckedChangeListener(this);
        }

        //Retrives all the data from the database and insert it to the chart data objects//
        DatabaseHelper db = new DatabaseHelper(this);
        try {
            smileData = new ChartDataExp(db.getImageResultFromDBByExpression(SMILE_EXP));
        } catch (Exception e) {
            smileData = null;
        }
        try {
            kissData = new ChartDataExp(db.getImageResultFromDBByExpression(KISS_EXP));
        } catch (Exception e) {
            kissData = null;
        }
        try {
            blanklyData = new ChartDataExp(db.getImageResultFromDBByExpression(NATURAL_EXP));
        } catch (Exception e) {
            blanklyData = null;
        }
        try {
            eyeBrowRaisedData = new ChartDataExp(db.getImageResultFromDBByExpression(EYEBROWRAISED_EXP));
        } catch (Exception e) {
            eyeBrowRaisedData = null;
        }
        try {
            eyesClosedData = new ChartDataExp(db.getImageResultFromDBByExpression(EYECLOSED_EXP));
        } catch (Exception e) {
            eyesClosedData = null;
        }
        try {
            rabbitData = new ChartDataExp(db.getImageResultFromDBByExpression(UPPERLIPRAISED_EXP));
        } catch (Exception e) {
            rabbitData = null;
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (patientType.equalsIgnoreCase(CHRONIC)) {
                    chronicUserGraph(spinner.getSelectedItem().toString());
                } else
                    acuteUserGraph(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do-Nothing
            }
        });
    }

    //********** Acute Patient Data Handling **********//
    private void acuteUserGraph(String Express) {
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        switch (Express) {
            case KISS_EXP:
                if(kissData!=null)
                    sets.add(kissData.getOuterMouthAreaSet());
                break;
            case SMILE_EXP:
                if(smileData!=null) {
                    sets.add(smileData.getMouthAngleSet());
                    sets.add(smileData.getMouthDisstanceSet());
                }
                break;
            case NATURAL_EXP:
                if(blanklyData!=null) {
                    sets.add(blanklyData.getEyeAreaSet());
                    sets.add(blanklyData.getMouthAngleSet());
                }
                break;
            case UPPERLIPRAISED_EXP:
                if(rabbitData!=null)
                    sets.add(rabbitData.getInnerMouthAreaSet());
                break;
            case EYEBROWRAISED_EXP:
                if(eyeBrowRaisedData!=null)
                    sets.add(eyeBrowRaisedData.getEyeToBrowDisstanceSet());
                break;
            case EYECLOSED_EXP:
                if(eyesClosedData!=null)
                    sets.add(eyesClosedData.getEyeAreaSet());
                break;
            default:
                sets = null;
        }
        updateChart(sets);
    }

    //********** Chronic Patient Data Handling **********//
    private void chronicUserGraph(String Express) {
        //Remove all data from the graph (for previous expression)//
        uncheckBoxes();
        clearDynamicSet();
        updateChart(null);

        switch (Express) {
            case KISS_EXP:
                targetedData = kissData;
                break;
            case SMILE_EXP:
                targetedData = smileData;
                break;
            case NATURAL_EXP:
                targetedData = blanklyData;
                break;
            case UPPERLIPRAISED_EXP:
                targetedData = rabbitData;
                break;
            case EYEBROWRAISED_EXP:
                targetedData = eyeBrowRaisedData;
                break;
            case EYECLOSED_EXP:
                targetedData = eyesClosedData;
                break;
            default:
                targetedData = null;
        }
        Toast.makeText(this, "Choose Which Metrics To Show", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<ILineDataSet> dynamicToArrayList(){
        ArrayList<ILineDataSet> arr = new ArrayList<>();
        int count=0;
        for(int i=0; i<NUM_OF_METRICS; i++) {
            if (dynamicSets[i] != null) {
                count++;
                arr.add(dynamicSets[i]);
            }
        }

        if(count==0)
            return null;
        else
            return arr;
    }

    //Method for handling the checkbox state change//
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(targetedData!=null) {
            switch (buttonView.getId()) {
                case R.id.box_eyeArea:
                    if (isChecked)
                        dynamicSets[EYEAREAINDEX] = targetedData.getEyeAreaSet();
                    else
                        dynamicSets[EYEAREAINDEX] = null;
                    break;
                case R.id.box_eyeToBrowDisstance:
                    if (isChecked)
                        dynamicSets[EYETOBROWINDEX] = targetedData.getEyeToBrowDisstanceSet();
                    else
                        dynamicSets[EYETOBROWINDEX] = null;
                    break;
                case R.id.box_innerMouthArea:
                    if (isChecked)
                        dynamicSets[INNERMOUTHINDEX] = targetedData.getInnerMouthAreaSet();
                    else
                        dynamicSets[INNERMOUTHINDEX] = null;
                    break;
                case R.id.box_mouthAngle:
                    if (isChecked)
                        dynamicSets[MOUTHANGLEINDEX] = targetedData.getMouthAngleSet();
                    else
                        dynamicSets[MOUTHANGLEINDEX] = null;
                    break;
                case R.id.box_mouthDisstance:
                    if (isChecked)
                        dynamicSets[MOUTHDISTINDEX] = targetedData.getMouthDisstanceSet();
                    else
                        dynamicSets[MOUTHDISTINDEX] = null;
                    break;
                case R.id.box_outerMouthArea:
                    if (isChecked)
                        dynamicSets[OUTERMOUTHINDEX] = targetedData.getOuterMouthAreaSet();
                    else
                        dynamicSets[OUTERMOUTHINDEX] = null;
                    break;
                default:
                    Toast.makeText(this, "Unknown onCheckedChanged ID", Toast.LENGTH_SHORT).show();
            }
        }

        //Updates the graph state//
        updateChart(dynamicToArrayList());
    }

    private void clearDynamicSet() {
            for (int i = 0; i < NUM_OF_METRICS; i++)
                dynamicSets[i] = null;
    }

    private void uncheckBoxes() {
        ((CheckBox)findViewById(R.id.box_eyeArea)).setChecked(false);
        ((CheckBox)findViewById(R.id.box_eyeToBrowDisstance)).setChecked(false);
        ((CheckBox)findViewById(R.id.box_innerMouthArea)).setChecked(false);
        ((CheckBox)findViewById(R.id.box_mouthAngle)).setChecked(false);
        ((CheckBox)findViewById(R.id.box_mouthDisstance)).setChecked(false);
        ((CheckBox)findViewById(R.id.box_outerMouthArea)).setChecked(false);
    }

    //********** Chart Data Update Method **********//
    private void updateChart(ArrayList<ILineDataSet> sets) {
        if (sets != null) {
            ArrayList<ILineDataSet> allDataSets = new ArrayList<>();

            for (int i = 0; i < sets.size(); i++) {
                if(sets.get(i)!=null)
                    allDataSets.add(sets.get(i));
            }

            try {
                chart.setData(new LineData(allDataSets));
            } catch (Exception e) {
                chart.setData(null);
            }
            chart.invalidate(); // refresh
        }
        else
            chart.setData(null);
    }

    //********** Other Buttons **********//
    public void btn_send_therapist_on_click(View v) {
        String filename = "analysis.txt";

        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        Uri path = Uri.fromFile(filelocation);
        FileWriter writer;
        try {
            writer = new FileWriter(filelocation);
        } catch (IOException e) {
            Toast.makeText(this, "Error Creating File", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseHelper db = new DatabaseHelper(this);

        ArrayList<ImageResultViewer> arr = db.getAllImageResultFromDB();
        ArrayList<LandmarksAnalyzerViewer> arrLandmarks = db.getAllMetricsFromDB();
        String email = pref.getString(THERAPIST_MAIL, "");// getting therapist email
        String name = pref.getString(USER_NAME, "");//getting name of user
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        //create a txt file and write stuff and send to physiotherapist mail

        intent.setType("plain/text");
        try {
            for (int i = 0; i < arr.size(); i++) {
                writer.append(arr.get(i).toString());
                writer.append(arrLandmarks.get(i).toString());
            }

            writer.close();
        } catch (IOException e) {
            Toast.makeText(this, "Error Writing File", Toast.LENGTH_SHORT).show();
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, "Analysis of " + name + " by PhysioTrack");
        intent.putExtra(Intent.EXTRA_TEXT, "hi ,\nhere is my analysis by image attached");
        intent.putExtra(Intent.EXTRA_STREAM, path);
        startActivity(Intent.createChooser(intent, ""));
    }
}
