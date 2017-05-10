package edu.sce.tom.physotrack.Algorithm;

//This class contains a single image results (difference between the healthy and damaged side)//
public class ImageResult {
    private LandmarksAnalyzer metrics;

    private float eyeToBrowDisstance;
    private float eyeArea;
    private float mouthAngle;
    private float mouthDisstance;
    private float innerMouthAreat;
    private float outerMouthArea;

    public ImageResult(LandmarksAnalyzer analyzer){
        this.metrics = analyzer;
    }


    public void calcResult(){
        eyeToBrowDisstance = metrics.getLeftEyeToBrowDistance()-metrics.getRightEyeToBrowDistance();
        eyeArea = metrics.getLeftEyeArea()-metrics.getRightEyeArea();
        mouthAngle = metrics.getRightMouthEdgeAngle();
        mouthDisstance = metrics.getLeftMouthDistance()-metrics.getRightMouthDistance();
        innerMouthAreat = metrics.getLeftInnerMouthArea()-metrics.getRightInnerMouthArea();
        outerMouthArea = metrics.getLeftOuterMouthArea()-metrics.getRightOuterMouthArea();
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
}
