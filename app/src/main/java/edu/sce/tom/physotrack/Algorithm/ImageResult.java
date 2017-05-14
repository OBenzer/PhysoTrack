package edu.sce.tom.physotrack.Algorithm;

//This class contains a single image results (difference between the healthy and damaged side)//
public class ImageResult extends ImageResultViewer{
    private static final int WANTED_ANGLE = 90;

    private LandmarksAnalyzer metrics;

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
}
