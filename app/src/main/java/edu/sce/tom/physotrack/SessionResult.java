package edu.sce.tom.physotrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import edu.sce.tom.physotrack.Algorithm.ImageResult;
import edu.sce.tom.physotrack.Algorithm.SesRunSingletone;
import edu.sce.tom.physotrack.Algorithm.SessionRunner;
import static edu.sce.tom.physotrack.Utils.Colors;

public class SessionResult extends AppCompatActivity implements View.OnClickListener {

    private SessionRunner sessionRunner;
    private BarChart chart;

    private ArrayList<BarDataSet> smileData=null;
    private ArrayList<BarDataSet> kissData=null;
    private ArrayList<BarDataSet> eyeClosedData=null;
    private ArrayList<BarDataSet> blanklyData=null;
    private ArrayList<BarDataSet> raisedBrowData=null;
    private ArrayList<BarDataSet> rabbitData=null;

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
        chart.setDragEnabled(true);
        chart.setDescription(null);
        chart.setTouchEnabled(false);
        YAxis y = chart.getAxisLeft();
        y.setAxisMaximum(100);
        y.setAxisMinimum(-100);

        ExtractData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_blankly:
                if(blanklyData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                else
                    updateChart(blanklyData);
                break;
            case R.id.radio_browlift:
                if(raisedBrowData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                else
                    updateChart(raisedBrowData);
                break;
            case R.id.radio_eyeclosed:
                if(eyeClosedData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                else
                    updateChart(eyeClosedData);
                break;
            case R.id.radio_kiss:
                if(kissData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                else
                    updateChart(kissData);
                break;
            case R.id.radio_rabbit:
                if(rabbitData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                else
                    updateChart(rabbitData);
                break;
            case R.id.radio_smile:
                if(smileData==null)
                    Toast.makeText(this, "This session doesn't include this image", Toast.LENGTH_SHORT).show();
                else
                    updateChart(smileData);
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
            raisedBrowData = new ArrayList<>();
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye Area");
            Temp.setColor(Colors[0]);
            raisedBrowData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye To Brow Distance");
            Temp.setColor(Colors[1]);
            raisedBrowData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(2f, ar.getinnerMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "InnerMouth Area");
            Temp.setColor(Colors[2]);
            raisedBrowData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Angle");
            Temp.setColor(Colors[3]);
            raisedBrowData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "OuterMouth Area");
            Temp.setColor(Colors[4]);
            raisedBrowData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Edge Distance");
            Temp.setColor(Colors[5]);
            raisedBrowData.add(Temp);
            entries.clear();
        }

        ar=sessionRunner.getEyesClosedR();
        if(ar!=null) {
            eyeClosedData = new ArrayList<>();
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye Area");
            Temp.setColor(Colors[0]);
            eyeClosedData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye To Brow Distance");
            Temp.setColor(Colors[1]);
            eyeClosedData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(2f, ar.getinnerMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "InnerMouth Area");
            Temp.setColor(Colors[2]);
            eyeClosedData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Angle");
            Temp.setColor(Colors[3]);
            eyeClosedData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "OuterMouth Area");
            Temp.setColor(Colors[4]);
            eyeClosedData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Edge Distance");
            Temp.setColor(Colors[5]);
            eyeClosedData.add(Temp);
            entries.clear();
        }

        ar=sessionRunner.getKissR();
        if(ar!=null) {
            kissData = new ArrayList<>();
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye Area");
            Temp.setColor(Colors[0]);
            kissData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye To Brow Distance");
            Temp.setColor(Colors[1]);
            kissData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(2f, ar.getinnerMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "InnerMouth Area");
            Temp.setColor(Colors[2]);
            kissData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Angle");
            Temp.setColor(Colors[3]);
            kissData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "OuterMouth Area");
            Temp.setColor(Colors[4]);
            kissData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Edge Distance");
            Temp.setColor(Colors[5]);
            kissData.add(Temp);
            entries.clear();
        }

        ar=sessionRunner.getSmileR();
        if(ar!=null) {
            smileData = new ArrayList<>();
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye Area");
            Temp.setColor(Colors[0]);
            smileData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye To Brow Distance");
            Temp.setColor(Colors[1]);
            smileData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(2f, ar.getinnerMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "InnerMouth Area");
            Temp.setColor(Colors[2]);
            smileData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Angle");
            Temp.setColor(Colors[3]);
            smileData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "OuterMouth Area");
            Temp.setColor(Colors[4]);
            smileData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Edge Distance");
            Temp.setColor(Colors[5]);
            smileData.add(Temp);
            entries.clear();
        }

        ar=sessionRunner.getNaturalR();
        if(ar!=null) {
            blanklyData = new ArrayList<>();
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye Area");
            Temp.setColor(Colors[0]);
            blanklyData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye To Brow Distance");
            Temp.setColor(Colors[1]);
            blanklyData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(2f, ar.getinnerMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "InnerMouth Area");
            Temp.setColor(Colors[2]);
            blanklyData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Angle");
            Temp.setColor(Colors[3]);
            blanklyData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "OuterMouth Area");
            Temp.setColor(Colors[4]);
            blanklyData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Edge Distance");
            Temp.setColor(Colors[5]);
            blanklyData.add(Temp);
            entries.clear();
        }

        ar=sessionRunner.getUpperlipRasiedR();
        if(ar!=null) {
            rabbitData = new ArrayList<>();
            entries.add(new BarEntry(0f, ar.getEyeArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye Area");
            Temp.setColor(Colors[0]);
            rabbitData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(1f, ar.getEyeToBrowDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Eye To Brow Distance");
            Temp.setColor(Colors[1]);
            rabbitData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(2f, ar.getinnerMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "InnerMouth Area");
            Temp.setColor(Colors[2]);
            rabbitData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(3f, ar.getMouthAngle()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Angle");
            Temp.setColor(Colors[3]);
            rabbitData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(4f, ar.getOuterMouthArea()));
            Temp = new BarDataSet(new ArrayList<>(entries), "OuterMouth Area");
            Temp.setColor(Colors[4]);
            rabbitData.add(Temp);
            entries.clear();
            entries.add(new BarEntry(5f, ar.getMouthDisstance()));
            Temp = new BarDataSet(new ArrayList<>(entries), "Mouth Edge Distance");
            Temp.setColor(Colors[5]);
            rabbitData.add(Temp);
            entries.clear();
        }
    }

    private void updateChart(ArrayList<BarDataSet> set){
        if(set!=null){
            ArrayList<IBarDataSet> allDataSets = new ArrayList<>();
            for(int i=0;i<set.size();i++)
                allDataSets.add(set.get(i));
            try{
                chart.setData(new BarData(allDataSets));
                chart.setFitBars(true);
            } catch (Exception e) {
                chart.setData(null);
            }

            chart.invalidate();
        }
    }
}
