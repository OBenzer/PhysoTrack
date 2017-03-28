package edu.sce.tom.physotrack.Algorithm;

import android.graphics.Point;
import java.util.ArrayList;

public class LandmarksAnalyzer {
    private final FaceLandmarks face;

    public LandmarksAnalyzer(FaceLandmarks f) {
        this.face = f;
    }

    public void analyzeFace()
    {
        // Calc all the metrics of the face //
    }

    //Calculate eye metrics, Send the side as the side of the eye needed//
    private void caclEye(ArrayList<Point> points, char side) {

    }

    //Calculate the polygon area created by the list of points//
    private float polygonAreaCalc(ArrayList<Point> points){
        float area=0;
        int numOfPoints = points.size();
        for(int i=0; i<numOfPoints; i++){
            area+=polygonTwoPointsCalc(points.get(i), points.get((i+1)%numOfPoints));
        }
        return Math.abs(area/2);
    }

    //Help method to calc two near points in the polygon points list//
    private float polygonTwoPointsCalc(Point p1, Point p2){
        return p1.x*p2.y-p1.y*p2.x;
    }

    //Calculate the distance between the two points given//
    private float distanceCalc(Point p1, Point p2) {
        return (float)Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2));
    }

}
