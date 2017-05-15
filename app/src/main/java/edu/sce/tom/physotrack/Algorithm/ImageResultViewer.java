package edu.sce.tom.physotrack.Algorithm;

import java.util.Date;

import edu.sce.tom.physotrack.Utils;

public class ImageResultViewer {

    protected Date date;
    protected float eyeToBrowDisstance;
    protected float eyeArea;
    protected float mouthAngle;
    protected float mouthDisstance;
    protected float innerMouthAreat;
    protected float outerMouthArea;
    protected String expression;

    public ImageResultViewer() {

    }

    public ImageResultViewer(String date, float eyeToBrowDisstance, float eyeArea, float mouthAngle, float mouthDisstance,
                             float innerMouthAreat, float outerMouthArea, String expression) {
        this.date = Utils.stringToDate(date);
        this.eyeToBrowDisstance = eyeToBrowDisstance;
        this.eyeArea = eyeArea;
        this.mouthAngle = mouthAngle;
        this.mouthDisstance = mouthDisstance;
        this.innerMouthAreat = innerMouthAreat;
        this.outerMouthArea = outerMouthArea;
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public float getEyeToBrowDisstance() {
        return eyeToBrowDisstance;
    }

    public float getEyeArea() {
        return eyeArea;
    }

    public float getMouthAngle() {
        return mouthAngle;
    }

    public float getMouthDisstance() {
        return mouthDisstance;
    }

    public float getInnerMouthAreat() {
        return innerMouthAreat;
    }

    public float getOuterMouthArea() {
        return outerMouthArea;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ImageResultViewer{" +
                "date=" + date +
                ", eyeToBrowDisstance=" + eyeToBrowDisstance +
                ", eyeArea=" + eyeArea +
                ", mouthAngle=" + mouthAngle +
                ", mouthDisstance=" + mouthDisstance +
                ", innerMouthAreat=" + innerMouthAreat +
                ", outerMouthArea=" + outerMouthArea +
                ", expression='" + expression + '\'' +
                '}';
    }
}
