package edu.sce.tom.physotrack.Algorithm;

//This class contains a single image results (difference between the healthy and damaged side)//
public class ImageResult extends ImageResultViewer{
    private static final int WANTED_ANGLE = 90;

    private LandmarksAnalyzer metrics;

    ImageResult(LandmarksAnalyzer analyzer, String exp){
        super(exp);
        this.metrics = analyzer;
    }

    void calcResult(){
        float left,right;

        //Eye To Brow Distance//
        left = metrics.getLeftEyeToBrowDistance();
        right = metrics.getRightEyeToBrowDistance();
        eyeToBrowDisstance = sumUpMetric(left,right);

        //Eye Erea//
        left = metrics.getLeftEyeArea();
        right = metrics.getRightEyeArea();
        eyeArea = sumUpMetric(left,right);

        //Mouth Angle//
        mouthAngle = sumUpAngle(metrics.getLeftMouthEdgeAngle());

        //Mouth Edges Disstance//
        left = metrics.getLeftMouthDistance();
        right = metrics.getRightMouthDistance();
        mouthDisstance = sumUpMetric(left,right);

        //Inner Mouth Area//
        left = metrics.getLeftInnerMouthArea();
        right = metrics.getRightInnerMouthArea();
        innerMouthArea = sumUpMetric(left,right);

        //Outher Mouth Area//
        left = metrics.getLeftOuterMouthArea();
        right = metrics.getRightOuterMouthArea();
        outerMouthArea = sumUpMetric(left,right);
    }

    //Check Sumup Metrics with udi and yael//
    private float sumUpMetric(float left, float right) {
        return ((left-right)/(right+left))*100;
    }

    //Check with udi&yael for values//
    private float sumUpAngle(float angle) {
        return WANTED_ANGLE-angle;
    }
}