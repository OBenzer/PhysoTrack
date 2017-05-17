package edu.sce.tom.physotrack.Algorithm;

//This class contains a single image results (difference between the healthy and damaged side)//
public class ImageResult extends ImageResultViewer{
    private static final int WANTED_ANGLE = 90;

    private LandmarksAnalyzer metrics;

    ImageResult(LandmarksAnalyzer analyzer, String exp){
        expression = exp;
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
        mouthAngle = WANTED_ANGLE-metrics.getRightMouthEdgeAngle();

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

    private float sumUpMetric(float left, float right) {
        return (left-right)/((left+right)/2);    //Calc the calc the difference and substitute the average
    }
}
