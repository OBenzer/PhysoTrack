package edu.sce.tom.physotrack.Algorithm;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

import static edu.sce.tom.physotrack.Utils.Colors;

public class ChartDataExp {

    private String Expression;

    private LineDataSet eyeToBrowDisstanceSet;
    private LineDataSet eyeAreaSet;
    private LineDataSet mouthDisstanceSet;
    private LineDataSet innerMouthAreaSet;
    private LineDataSet outerMouthAreaSet;
    private LineDataSet mouthAngleSet;

    public ChartDataExp(ArrayList<ImageResultViewer> IR) {
        ArrayList<Entry> eyeToBrowDisstanceEntries = new ArrayList<>();
        ArrayList<Entry> eyeAreaEntries = new ArrayList<>();
        ArrayList<Entry> mouthAngleEntries = new ArrayList<>();
        ArrayList<Entry> mouthDisstanceEntries = new ArrayList<>();
        ArrayList<Entry> innerMouthAreaEntries = new ArrayList<>();
        ArrayList<Entry> outerMouthAreaEntries = new ArrayList<>();

        for(int i=0; i<IR.size(); i++) {
            ImageResultViewer iterObj = IR.get(i);
            eyeAreaEntries.add(new Entry(i,iterObj.getEyeArea()));
            eyeToBrowDisstanceEntries.add(new Entry(i, iterObj.getEyeToBrowDisstance()));
            innerMouthAreaEntries.add(new Entry(i, iterObj.getinnerMouthArea()));
            mouthAngleEntries.add(new Entry(i, iterObj.getMouthAngle()));
            mouthDisstanceEntries.add(new Entry(i, iterObj.getMouthDisstance()));
            outerMouthAreaEntries.add(new Entry(i, iterObj.getOuterMouthArea()));
        }

        eyeToBrowDisstanceSet = new LineDataSet(eyeToBrowDisstanceEntries, "EyeToBrow Disstance");
        eyeToBrowDisstanceSet.setColor(Colors[0]);
        eyeAreaSet = new LineDataSet(eyeAreaEntries, "Eye Area");
        eyeAreaSet.setColor(Colors[1]);
        mouthAngleSet = new LineDataSet(mouthAngleEntries, "Mouth Angle");
        mouthAngleSet.setColor(Colors[2]);
        mouthDisstanceSet = new LineDataSet(mouthDisstanceEntries, "Mouth Edges Disstance");
        mouthDisstanceSet.setColor(Colors[3]);
        innerMouthAreaSet = new LineDataSet(innerMouthAreaEntries, "Inner Mouth Area");
        innerMouthAreaSet.setColor(Colors[4]);
        outerMouthAreaSet = new LineDataSet(outerMouthAreaEntries, "Outher Mouth Area");
        outerMouthAreaSet.setColor(Colors[5]);
    }

    public LineDataSet getEyeToBrowDisstanceSet() { return eyeToBrowDisstanceSet; }

    public LineDataSet getEyeAreaSet() { return eyeAreaSet; }

    public LineDataSet getMouthDisstanceSet() { return mouthDisstanceSet; }

    public LineDataSet getInnerMouthAreaSet() { return innerMouthAreaSet; }

    public LineDataSet getOuterMouthAreaSet() { return outerMouthAreaSet; }

    public LineDataSet getMouthAngleSet() { return mouthAngleSet; }
}