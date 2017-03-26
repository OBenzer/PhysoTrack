package edu.sce.tom.physotrack.Algorithm;

import android.graphics.Point;
import java.util.ArrayList;

public class FaceLandmarks {
    // This class contains all facial landmarks divided into seperate interes group //
    // Each group is divided by the dlib points indexing - image in helpIMGs //
    private ArrayList<Point> allLandmarks;  // List of all the landmarks recived at constructing the object
    private ArrayList<Point> leftEye;       // List of 6 points representing the left eye
    private ArrayList<Point> leftEyeBrow;   // List of 5 points representing the left eye brow
    private ArrayList<Point> rightEye;
    private ArrayList<Point> rightEyeBrow;
    private ArrayList<Point> outherMouth;         // List of 12 points representing the othre mouth (lips)
    private ArrayList<Point> innerMouth;          // List of 8 point representing the inner of the mouth
    private ArrayList<Point> centerNose;          // List of 4 points representing the nose center line (between the eyes)
    private ArrayList<Point> noseBottomLine;      // List of 5 point representing the bottom line of the nose (nostrals area)
    private ArrayList<Point> FaceBorder;      // List of 17 point representing the bottom line of the face (ear to ear)

    // Constants of the indexes of points by interess erea, taken from dlib library //
    private static final int[] FACEBORDERINDEX = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    private static final int[] LEFTEYEBREOWINDEX = {17,18,19,20,21};
    private static final int[] RIGHTEYEBROWINDEX = {22,23,24,25,26};
    private static final int[] CENTERNOSEINDEX = {27,28,29,30};
    private static final int[] BOTOMNOSEINDEX = {31,32,33,34,35};
    private static final int[] LEFTEYEINDEX = {36,37,38,39,40,41};
    private static final int[] RIGHTEYEINDEX = {42,43,44,45,46,47};
    private static final int[] OUTMAOUTHINDEX = {48,49,50,51,52,53,54,55,56,57,58,59};
    private static final int[] INNERMOUTHINDEX = {60,61,62,63,64,65,66,67};

    /***
     * Class constructor
     * Devides the all the landmarks points (recived) into seperate intress groups
     * @param rawLandmarks ArrayList<Point> contains all the facial landmarks (Make sure it was created by dlib)
     */
    public FaceLandmarks(ArrayList<Point> rawLandmarks) {
        allLandmarks = new ArrayList<>(rawLandmarks);
        leftEye = new ArrayList<>();
        leftEyeBrow = new ArrayList<>();
        rightEye = new ArrayList<>();
        rightEyeBrow = new ArrayList<>();
        outherMouth = new ArrayList<>();
        innerMouth = new ArrayList<>();
        centerNose = new ArrayList<>();
        noseBottomLine = new ArrayList<>();
        FaceBorder = new ArrayList<>();

        // Decides the landmarks array into seperate groups //
        // Checks if the index i of the point in the Landmarks array between group ends //
        for (int i = 0; i < rawLandmarks.size() ; i++) {
            if (i>=FACEBORDERINDEX[0] && i<=FACEBORDERINDEX[FACEBORDERINDEX.length-1])
                FaceBorder.add(rawLandmarks.get(i));
            if (i>=LEFTEYEBREOWINDEX[0] && i<=LEFTEYEBREOWINDEX[LEFTEYEBREOWINDEX.length-1])
                leftEyeBrow.add(rawLandmarks.get(i));
            if (i>=RIGHTEYEBROWINDEX[0] && i<=RIGHTEYEBROWINDEX[RIGHTEYEBROWINDEX.length-1])
                rightEyeBrow.add(rawLandmarks.get(i));
            if (i>=CENTERNOSEINDEX[0] && i<=CENTERNOSEINDEX[CENTERNOSEINDEX.length-1])
                centerNose.add(rawLandmarks.get(i));
            if (i>=BOTOMNOSEINDEX[0] && i<=BOTOMNOSEINDEX[BOTOMNOSEINDEX.length-1])
                noseBottomLine.add(rawLandmarks.get(i));
            if (i>=LEFTEYEINDEX[0] && i<=LEFTEYEINDEX[LEFTEYEINDEX.length-1])
                leftEye.add(rawLandmarks.get(i));
            if (i>=RIGHTEYEINDEX[0] && i<=RIGHTEYEINDEX[RIGHTEYEINDEX.length-1])
                rightEye.add(rawLandmarks.get(i));
            if (i>=OUTMAOUTHINDEX[0] && i<=OUTMAOUTHINDEX[OUTMAOUTHINDEX.length-1])
                outherMouth.add(rawLandmarks.get(i));
            if (i>=INNERMOUTHINDEX[0] && i<=INNERMOUTHINDEX[INNERMOUTHINDEX.length-1])
                innerMouth.add(rawLandmarks.get(i));
        }
    }

    // Any getter, creates a copy of the arraylist, by-value return //
    public ArrayList<Point> getAllLandmarks() { return new ArrayList<Point>(allLandmarks); }

    public ArrayList<Point> getLeftEye() { return new ArrayList<Point>(leftEye); }

    public ArrayList<Point> getLeftEyeBrow() { return new ArrayList<Point>(leftEyeBrow); }

    public ArrayList<Point> getRightEye() { return new ArrayList<Point>(rightEye); }

    public ArrayList<Point> getRightEyeBrow() { return new ArrayList<Point>(rightEyeBrow); }

    public ArrayList<Point> getOutherMouth() { return new ArrayList<Point>(outherMouth); }

    public ArrayList<Point> getInnerMouth() { return new ArrayList<Point>(innerMouth); }

    public ArrayList<Point> getCenterNose() { return new ArrayList<Point>(centerNose); }

    public ArrayList<Point> getNoseBottomLine() { return new ArrayList<Point>(noseBottomLine); }

    public ArrayList<Point> getFaceBorder() { return new ArrayList<Point>(FaceBorder); }

}
