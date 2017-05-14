package edu.sce.tom.physotrack.Algorithm;

import android.graphics.Point;

import java.util.ArrayList;

public class LandmarksAnalyzer extends LandmarksAnalyzerViewer {
    //face subject//
    private final FaceLandmarks face;

    //constant//
    private static final char LEFT_SIDE='L';
    private static final char RIGHT_SIDE='R';



    public LandmarksAnalyzer(FaceLandmarks f, String exp) {
        expression = exp;
        this.face = f;
    }


    public void analyzeFace() {
        //calc right eye//
        calcEye(face.getRightEye(),face.getRightEyeBrow(),RIGHT_SIDE);

        //calc left eye//
        calcEye(face.getLeftEye(),face.getLeftEyeBrow(),LEFT_SIDE);

        //calc Mouth//
        calcMouth();

        //Every metrics calculated//
    }

    //******************** Metricts Calculation Methods ********************//
    //Method that calc all eye and eyebrow metrics and saves them to the correct attribute//
    private void calcEye(ArrayList<Point> eye,ArrayList<Point> brow,char side) {
        // calculate the center of the eye
        // calculate the area of the eye
        //calculate the center of the eyebrow
        //calculate distance from eyebrow to eye
        Point eyeAvg = calcAvg(eye);
        float eyeArea = calcPolygonArea(eye);
        Point browAvg= calcAvg(brow);
        float eyeToBrowDis=calcDistance(browAvg,eyeAvg);
        switch (side) {
            case RIGHT_SIDE:
                rightEyeCenter = eyeAvg;
                rightEyeArea = eyeArea;
                rightBrowCenter=browAvg;
                rightEyeToBrowDistance=eyeToBrowDis;
            case LEFT_SIDE:
                leftEyeCenter = eyeAvg;
                leftEyeArea = eyeArea;
                leftBrowCenter=browAvg;
                leftEyeToBrowDistance=eyeToBrowDis;
        }
    }

    //Method that calc all the mouth metrics and saves them to the correct attribute//
    private void calcMouth(){
        //Points indexes relative to the araylist return by the facelandmark object//
        final int[] innerLeftIndex = {2,3,4,5,6};
        final int[] innerRightIndex = {0,1,2,6,7};
        final int[] outerLeftIndex = {3,4,5,6,7,8,9};
        final int[] outerRightIndex = {0,1,2,3,9,10,11};

        // Calculating the area of halfs of the mouth (inner mouth)//
        ArrayList<Point> innerM = face.getInnerMouth();
        ArrayList<Point> innerLeftPoints = new ArrayList<>();
        ArrayList<Point> innerRightPoints = new ArrayList<>();

        for(int i=0; i<innerM.size(); i++) {    //Splits the points into left and right side
            if(contains(innerLeftIndex,i))
                innerLeftPoints.add(innerM.get(i));
            if(contains(innerRightIndex,i))
                innerRightPoints.add(innerM.get(i));
        }

        //Calcs the area of the inner mouth Areas polygons//
        leftInnerMouthArea = calcPolygonArea(innerLeftPoints);
        rightInnerMouthArea = calcPolygonArea(innerRightPoints);

        // Calculating the area of halfs of the mouth (outer mouth)//
        ArrayList<Point> outerM = face.getOuterMouth();
        ArrayList<Point> outerLeftPoints = new ArrayList<>();
        ArrayList<Point> outerRightPoints = new ArrayList<>();

        for(int i=0; i<outerM.size(); i++) {    //Splits the points into left and right side
            if(contains(outerLeftIndex,i))
                outerLeftPoints.add(outerM.get(i));
            if(contains(outerRightIndex,i))
                outerRightPoints.add(outerM.get(i));
        }

        //Calcs the area of the outer mouth Areas polygons//
        leftOuterMouthArea = calcPolygonArea(outerLeftPoints);
        rightOuterMouthArea = calcPolygonArea(outerRightPoints);

        //Calculating needed angles//
        ArrayList<Point> noseLine = face.getCenterNose();
        float noseSlope = calcSlope(noseLine.get(0), noseLine.get(noseLine.size()-1));
        float mouthSlope = calcSlope(outerM.get(0),outerM.get(6));

        rightMouthEdgeAngle = calcAngleBySlopes(noseSlope, mouthSlope);  //works?
        leftMouthEdgeAngle=180-rightMouthEdgeAngle;

        //Calculating the mouth edges distance with the nose line//
        Point averageNose = calcAvg(noseLine);
        Point leftMouthEdge = outerM.get(6);
        Point rightMouthEdge = outerM.get(0);

        rightMouthDistance = rightMouthEdge.x-averageNose.x;
        leftMouthDistance = averageNose.x-leftMouthEdge.x;
    }

    //********************Help Methods For Calculations********************//
    private Point calcAvg(ArrayList<Point> land){
        int sumx=0;
        int sumy=0;
        for (Point point:land) {
            sumx+=point.x;
            sumy+=point.y;
        }
        int size=land.size();
        return new Point(sumx/size,sumy/size);
    }

    private float calcPolygonArea(ArrayList<Point> points){
        int numOfPoints = points.size();
        float area=0;
        for(int i=0; i<numOfPoints; i++)
            area+=calcTwoPointsPolygon(points.get(i), points.get((i+1)%numOfPoints));

        return Math.abs(area/2);
    }

    private float calcTwoPointsPolygon(Point p1, Point p2){
        return p1.x*p2.y-p1.y*p2.x;
    }

    private float calcDistance(Point left, Point right){
        return (float) Math.sqrt((Math.pow((right.x-left.x),2)-(Math.pow((right.y-left.y),2))));
    }

    private float calcSlope(Point p1, Point p2){
        return (p1.y-p2.y)/(p1.x-p2.x);
    }

    private float calcAngleBySlopes(final float m1, final float m2){
        return (float)(Math.atan(m1)-Math.atan(m2));

    }

    private Boolean contains(int[] arr, int var){
        for (int anArr : arr) {
            if (anArr == var)
                return true;
        }

        return false;
    }

    //******************** Getters ********************//
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
    public float getLeftMouthDistance() { return leftMouthDistance; }
    public float getRightMouthDistance() { return rightMouthDistance; }
}
