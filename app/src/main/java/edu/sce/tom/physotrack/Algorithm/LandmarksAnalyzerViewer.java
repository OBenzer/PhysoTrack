package edu.sce.tom.physotrack.Algorithm;

import android.graphics.Point;

import edu.sce.tom.physotrack.Utils;


public class LandmarksAnalyzerViewer {

    //metrics//
    protected String date;
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

    public LandmarksAnalyzerViewer(String Exp) {
        date = Utils.todaysDateToString();
        expression = Exp;
    }

    public LandmarksAnalyzerViewer(String date, Point leftEyeCenter, Point rightEyeCenter, float leftEyeArea,
                                   float rightEyeArea, Point rightBrowCenter, Point leftBrowCenter,
                                   float leftEyeToBrowDistance, float rightEyeToBrowDistance, float leftInnerMouthArea,
                                   float rightInnerMouthArea, float leftOuterMouthArea, float rightOuterMouthArea,
                                   float rightMouthEdgeAngle, float leftMouthEdgeAngle, float leftMouthDistance,
                                   float rightMouthDistance, String expression) {
        this.date = date;
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


    //******************** Getters ********************//

    public String getDate() {
        return date;
    }

    public String getExpression() {
        return expression;
    }

    public Point getLeftEyeCenter() {
        return new Point(leftEyeCenter);
    }

    public Point getRightEyeCenter() {
        return new Point(rightEyeCenter);
    }

    public float getLeftEyeArea() {
        return leftEyeArea;
    }

    public float getRightEyeArea() {
        return rightEyeArea;
    }

    public Point getRightBrowCenter() {
        return new Point(rightBrowCenter);
    }

    public Point getLeftBrowCenter() {
        return new Point(leftBrowCenter);
    }

    public float getLeftEyeToBrowDistance() {
        return leftEyeToBrowDistance;
    }

    public float getRightEyeToBrowDistance() {
        return rightEyeToBrowDistance;
    }

    public float getLeftInnerMouthArea() {
        return leftInnerMouthArea;
    }

    public float getRightInnerMouthArea() {
        return rightInnerMouthArea;
    }

    public float getLeftOuterMouthArea() {
        return leftOuterMouthArea;
    }

    public float getRightOuterMouthArea() {
        return rightOuterMouthArea;
    }

    public float getRightMouthEdgeAngle() {
        return rightMouthEdgeAngle;
    }

    public float getLeftMouthEdgeAngle() {
        return leftMouthEdgeAngle;
    }

    public float getLeftMouthDistance() {
        return leftMouthDistance;
    }

    public float getRightMouthDistance() {
        return rightMouthDistance;
    }

    @Override
    public String toString() {
        return "Landmarks  :\n" +
                ", leftEyeCenter=" + leftEyeCenter +
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
                ", leftMouthDistance=" + leftMouthDistance +
                ", rightMouthDistance=" + rightMouthDistance +
                ", expression='" + expression + "\n\n"
                ;
    }
}
