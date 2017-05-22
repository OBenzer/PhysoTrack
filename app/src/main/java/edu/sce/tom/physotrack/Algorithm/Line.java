package edu.sce.tom.physotrack.Algorithm;

import android.graphics.Point;

class Line {
    private float m;
    private float b;

    Line(Point p1, Point p2){
        m = ((float)p2.y-p1.y)/((float)p2.x-p1.x);
        b = p1.y-m*p1.x;
    }

    Point intersect(Line l){
        double x = (l.b-b)/(m-l.m);
        double y = m*x+b;
        return new Point((int)x,(int)y);
    }

    float getSlope(){ return m; }
}
