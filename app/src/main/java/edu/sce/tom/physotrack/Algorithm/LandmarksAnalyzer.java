package edu.sce.tom.physotrack.Algorithm;

import android.graphics.Point;
import android.util.Log;
import java.util.ArrayList;

public class LandmarksAnalyzer {
    //face subject//
    private final FaceLandmarks face;

    //constant//
    private static final char LEFT_SIDE='L';
    private static final char RIGHT_SIDE='R';

    //metrics//
    private Point leftEyeCenter;
    private Point rightEyeCenter;
    private float leftEyeArea;
    private float rightEyeArea;
    private Point rightBrowCenter;
    private Point leftBrowCenter;
    private float leftEyeToBrowDistance;
    private float rightEyeToBrowDistance;
    private float leftMouthArea;
    private float rightMouthArea;

    public LandmarksAnalyzer(FaceLandmarks f) {
        this.face = f;
    }

    public void analyzeFace()
    {
        // Calc all the metrics of the face //

    }

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
            default:
                Log.d("debug", "calceye function in the default");
        }
    }

    //Method that calc all the mouth metrics and saves them to the correct attribute//
    private void CaclMouth(){
        final int[] innerLeftIndex = {2,3,4,5,6};
        final int[] innerRightIndex = {0,1,2,6,7};

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
        leftMouthArea = calcPolygonArea(innerLeftPoints);
        rightMouthArea = calcPolygonArea(innerRightPoints);
    }

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

    private Boolean contains(int[] arr, int var){
        for (int anArr : arr) {
            if (anArr == var)
                return true;
        }

        return false;
    }

}
