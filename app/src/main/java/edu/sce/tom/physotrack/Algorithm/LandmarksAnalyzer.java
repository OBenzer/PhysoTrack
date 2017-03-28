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


}
