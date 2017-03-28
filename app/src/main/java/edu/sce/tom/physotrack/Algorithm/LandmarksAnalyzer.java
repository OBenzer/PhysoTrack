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

}
