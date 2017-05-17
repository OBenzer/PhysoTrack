package edu.sce.tom.physotrack;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;

import edu.sce.tom.physotrack.Algorithm.ImageResult;
import edu.sce.tom.physotrack.Algorithm.SesRunSingletone;
import edu.sce.tom.physotrack.Algorithm.SessionRunner;

public class SessionResult extends AppCompatActivity implements View.OnClickListener {

    private static final int Colors[]={Color.rgb(40,220,117),Color.rgb(255,211,5),Color.rgb(251,75,56),Color.rgb(47,161,237),Color.rgb(231,54,218),Color.rgb(250,111,35)};
    private SessionRunner sessionRunner;
    private BarChart chart;

    private BarData smileData=null;
    private BarData kissData=null;
    private BarData eyeClosedData=null;
    private BarData blanklyData=null;
    private BarData raisedBrowData=null;
    private BarData rabbitData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_result);
        sessionRunner= SesRunSingletone.getInstance();

        findViewById(R.id.radio_blankly).setOnClickListener(this);
        findViewById(R.id.radio_browlift).setOnClickListener(this);
        findViewById(R.id.radio_eyeclosed).setOnClickListener(this);
        findViewById(R.id.radio_kiss).setOnClickListener(this);
        findViewById(R.id.radio_rabbit).setOnClickListener(this);
        findViewById(R.id.radio_smile).setOnClickListener(this);

        chart = (BarChart)findViewById(R.id.chart);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setScaleEnabled(false);
        chart.setDescription(null);

        ExtractData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_blankly:
                if(blanklyData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                chart.setData(blanklyData);
                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
                break;
            case R.id.radio_browlift:
                if(raisedBrowData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                chart.setData(raisedBrowData);
                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
                break;
            case R.id.radio_eyeclosed:
                if(eyeClosedData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                chart.setData(eyeClosedData);
                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
                break;
            case R.id.radio_kiss:
                if(kissData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                chart.setData(kissData);
                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
                break;
            case R.id.radio_rabbit:
                if(rabbitData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                chart.setData(rabbitData);
                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
                break;
            case R.id.radio_smile:
                if(smileData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                chart.setData(smileData);
                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
                break;
            default:
                Toast.makeText(this, "Unknown onClickListener", Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_finish_onclick(View v){
        sessionRunner.initialRunner();  //Initial the path, landmarks and all the object data.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void ExtractData(){
        ImageResult ar;
        ArrayList<BarEntry> entries = new ArrayList<>();
        BarDataSet Temp;

        ar=sessionRunner.getEyebrowRaisedR();
        if(ar!=null) {
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            entries.add(new BarEntry(2f, ar.getInnerMouthAreat()));
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), null);
            Temp.setColors(Colors);
            raisedBrowData = new BarData(Temp);
            raisedBrowData.setBarWidth(0.9f);

            entries.clear();
        }

        ar=sessionRunner.getEyesClosedR();
        if(ar!=null) {
            entries.add(new BarEntry(0f,ar.getEyeArea()));
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            entries.add(new BarEntry(2f, ar.getInnerMouthAreat()));
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), null);
            Temp.setColors(Colors);
            eyeClosedData = new BarData(Temp);
            eyeClosedData.setBarWidth(0.9f);

            entries.clear();
        }

        ar=sessionRunner.getKissR();
        if(ar!=null) {
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            entries.add(new BarEntry(2f, ar.getInnerMouthAreat()));
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), null);
            Temp.setColors(Colors);
            kissData = new BarData(Temp);
            kissData.setBarWidth(0.9f);

            entries.clear();
        }

        ar=sessionRunner.getSmileR();
        if(ar!=null) {
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            entries.add(new BarEntry(2f, ar.getInnerMouthAreat()));
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), null);
            Temp.setColors(Colors);
            smileData = new BarData(Temp);
            smileData.setBarWidth(0.9f);

            entries.clear();
        }

        ar=sessionRunner.getNaturalR();
        if(ar!=null) {
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            entries.add(new BarEntry(2f, ar.getInnerMouthAreat()));
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), null);
            Temp.setColors(Colors);
            blanklyData = new BarData(Temp);
            blanklyData.setBarWidth(0.9f);

            entries.clear();
        }

        ar=sessionRunner.getUpperlipRasiedR();
        if(ar!=null) {
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            entries.add(new BarEntry(2f, ar.getInnerMouthAreat()));
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), null);
            Temp.setColors(Colors);
            rabbitData = new BarData(Temp);
            rabbitData.setBarWidth(0.9f);

            entries.clear();
        }
    }
}
