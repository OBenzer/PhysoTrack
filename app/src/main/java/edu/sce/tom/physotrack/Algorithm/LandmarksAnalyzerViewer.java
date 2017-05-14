package edu.sce.tom.physotrack.Algorithm;

import android.graphics.Point;


public class LandmarksAnalyzerViewer {

    //metrics//
    protected Point leftEyeCenter;
    protected Point rightEyeCenter;
    protected float leftEyeArea;
    protected float rightEyeArea;
    protected Point rightBrowCenter;
    protected Point leftBrowCenter;
    protected float leftEyeToBrowDistance;
    protected float rightEyeToBrowDistance;
    protected float leftInnerMouthArea;
    protected float rightInnerMouthArea;
    protected float leftOuterMouthArea;
    protected float rightOuterMouthArea;
    protected float rightMouthEdgeAngle;
    protected float leftMouthEdgeAngle;
    protected float leftMouthDistance;
    protected float rightMouthDistance;
    protected String expression;

    public LandmarksAnalyzerViewer() {
    }

    public LandmarksAnalyzerViewer(Point leftEyeCenter, Point rightEyeCenter, float leftEyeArea,
                                   float rightEyeArea, Point rightBrowCenter, Point leftBrowCenter,
                                   float leftEyeToBrowDistance, float rightEyeToBrowDistance, float leftInnerMouthArea,
                                   float rightInnerMouthArea, float leftOuterMouthArea, float rightOuterMouthArea,
                                   float rightMouthEdgeAngle, float leftMouthEdgeAngle,float leftMouthDistance,
                                   float rightMouthDistance,String expression) {
        this.leftEyeCenter = leftEyeCenter;
        this.rightEyeCenter = rightEyeCenter;
        this.leftEyeArea = leftEyeArea;
        this.rightEyeArea = rightEyeArea;
        this.rightBrowCenter = rightBrowCenter;
        this.leftBrowCenter = leftBrowCenter;
        this.leftEyeToBrowDistance = leftEyeToBrowDistance;
        this.rightEyeToBrowDistance = rightEyeToBrowDistance;
        this.leftInnerMouthArea = leftInnerMouthArea;
        this.rightInnerMouthArea = rightInnerMouthArea;
        this.leftOuterMouthArea = leftOuterMouthArea;
        this.rightOuterMouthArea = rightOuterMouthArea;
        this.rightMouthEdgeAngle = rightMouthEdgeAngle;
        this.leftMouthEdgeAngle = leftMouthEdgeAngle;
        this.leftMouthDistance = leftMouthDistance;
        this.rightMouthDistance = rightMouthDistance;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "LandmarksAnalyzerViewer{" +
                "leftEyeCenter=" + leftEyeCenter +
                ", rightEyeCenter=" + rightEyeCenter +
                ", leftEyeArea=" + leftEyeArea +
                ", rightEyeArea=" + rightEyeArea +
                ", rightBrowCenter=" + rightBrowCenter +
                ", leftBrowCenter=" + leftBrowCenter +
                ", leftEyeToBrowDistance=" + leftEyeToBrowDistance +
                ", rightEyeToBrowDistance=" + rightEyeToBrowDistance +
                ", leftInnerMouthArea=" + leftInnerMouthArea +
                ", rightInnerMouthArea=" + rightInnerMouthArea +
                ", leftOuterMouthArea=" + leftOuterMouthArea +
                ", rightOuterMouthArea=" + rightOuterMouthArea +
                ", rightMouthEdgeAngle=" + rightMouthEdgeAngle +
                ", leftMouthEdgeAngle=" + leftMouthEdgeAngle +
                ", expression='" + expression + '\'' +
                '}';
    }
}
