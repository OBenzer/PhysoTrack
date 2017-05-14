package edu.sce.tom.physotrack.Algorithm;

//This class contains a single image results (difference between the healthy and damaged side)//
public class ImageResult {
    private static final int WANTED_ANGLE = 90;

    private LandmarksAnalyzer metrics;

    private float eyeToBrowDisstance;
    private float eyeArea;
    private float mouthAngle;
    private float mouthDisstance;
    private float innerMouthAreat;
    private float outerMouthArea;

    private String expression;

    public ImageResult(LandmarksAnalyzer analyzer, String exp){
        expression = exp;
        this.metrics = analyzer;
    }


    public void calcResult(){
        eyeToBrowDisstance = metrics.getLeftEyeToBrowDistance()-metrics.getRightEyeToBrowDistance();
        eyeArea = metrics.getLeftEyeArea()-metrics.getRightEyeArea();
        mouthAngle = WANTED_ANGLE-metrics.getRightMouthEdgeAngle();
        mouthDisstance = metrics.getLeftMouthDistance()-metrics.getRightMouthDistance();
        innerMouthAreat = metrics.getLeftInnerMouthArea()-metrics.getRightInnerMouthArea();
        outerMouthArea = metrics.getLeftOuterMouthArea()-metrics.getRightOuterMouthArea();
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

    @Override
    public String toString() {
        return "ImageResult{" +
                "eyeToBrowDisstance=" + eyeToBrowDisstance +
                ", eyeArea=" + eyeArea +
                ", mouthAngle=" + mouthAngle +
                ", mouthDisstance=" + mouthDisstance +
                ", innerMouthAreat=" + innerMouthAreat +
                ", outerMouthArea=" + outerMouthArea +
                ", expression='" + expression + '\'' +
                '}';
    }
}
