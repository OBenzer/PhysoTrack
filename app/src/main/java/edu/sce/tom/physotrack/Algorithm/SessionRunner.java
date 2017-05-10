package edu.sce.tom.physotrack.Algorithm;

import edu.sce.tom.physotrack.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.tzutalin.dlib.Constants;
import com.tzutalin.dlib.FaceDet;
import com.tzutalin.dlib.VisionDetRet;

public class SessionRunner {
    //attributes//
    //___P - position image path
    //___L - position FaceLandmarks object
    //___AR - position analysis results
    //___R - single image results sumed up
    private String naturalP=null;
    private FaceLandmarks naturalL=null;
    private LandmarksAnalyzer naturalAR=null;
    private ImageResult naturalR=null;

    private String eyebrowRaisedP=null;
    private FaceLandmarks eyebrowRaisedL=null;
    private LandmarksAnalyzer eyebrowRaisedAR=null;
    private ImageResult eyebrowRaisedR=null;

    private String eyesClosedP=null;
    private FaceLandmarks eyesClosedL=null;
    private LandmarksAnalyzer eyesClosedAR=null;
    private ImageResult eyesClosedR=null;

    private String upperlipRasiedP=null;
    private FaceLandmarks upperlipRasiedL=null;
    private LandmarksAnalyzer upperlipRasiedAR=null;
    private ImageResult upperlipRasiedR=null;

    private String smileP=null;
    private FaceLandmarks smileL=null;
    private LandmarksAnalyzer smileAR=null;
    private ImageResult smileR=null;

    private String kissP=null;
    private FaceLandmarks kissL=null;
    private LandmarksAnalyzer kissAR=null;
    private ImageResult kissR=null;

    private FaceDet mFaceDet=null;

    //ctor//
    //looks for the 68.dat file in not found recreates it from the application
    //and initialize the facedet object for detection
    public SessionRunner(Context c) {
        final String targetPath = Constants.getFaceShapeModelPath();
        if (!new File(targetPath).exists()) {

            FileUtils.copyFileFromRawToOthers(c.getApplicationContext(), R.raw.shape_predictor_68_face_landmarks, targetPath);
        }

        if (mFaceDet == null) {
            mFaceDet = new FaceDet(Constants.getFaceShapeModelPath());
        }
    }

    //run is after (potentially) all 5 photos were taken successfully (all setters returned true)
    //all further results will be saved on the database.
    public void run(){
        //Face Analysis//
        //Calculate the positions metrics only if the path attribute is not null
        if(naturalP!=null){
            naturalAR = new LandmarksAnalyzer(naturalL);
            naturalAR.analyzeFace();
            naturalR = new ImageResult(naturalAR);
            naturalR.calcResult();
        }

        if(eyebrowRaisedP!=null){
            eyebrowRaisedAR = new LandmarksAnalyzer(eyebrowRaisedL);
            eyebrowRaisedAR.analyzeFace();
            eyebrowRaisedR = new ImageResult(eyebrowRaisedAR);
            eyebrowRaisedR.calcResult();
        }

        if(eyesClosedP!=null){
            eyesClosedAR = new LandmarksAnalyzer(eyesClosedL);
            eyesClosedAR.analyzeFace();
            eyesClosedR = new ImageResult(eyesClosedAR);
            eyesClosedR.calcResult();
        }

        if(upperlipRasiedP!=null){
            upperlipRasiedAR = new LandmarksAnalyzer(upperlipRasiedL);
            upperlipRasiedAR.analyzeFace();
            upperlipRasiedR = new ImageResult(upperlipRasiedAR);
            upperlipRasiedR.calcResult();
        }

        if(smileP!=null){
            smileAR = new LandmarksAnalyzer(smileL);
            smileAR.analyzeFace();
            smileR = new ImageResult(smileAR);
            smileR.calcResult();
        }

        if(kissP!=null){
            kissAR = new LandmarksAnalyzer(kissL);
            kissAR.analyzeFace();
            kissR = new ImageResult(kissAR);
            kissR.calcResult();
        }

        //Saving results to DB//

        //Finish session//
    }

    //setters//
    //returns true if one face was detected and updates all facial landmarks for specific position, updates path for position
    //return false in any case of error (if no face was found or no facial landmark detected or more than one face was detected...etc)
    //if false was returned no path will be updated and the picture will be ignored and a new picture will have to be taken
    public boolean setNaturalP(String Path) {
        List<VisionDetRet> faceList = mFaceDet.detect(Path);
        if (faceList != null && faceList.size() == 1) { // Only one face detected by dlib
            naturalL = new FaceLandmarks(faceList.get(0).getFaceLandmarks());   // Get all landmarks group of the face
            this.naturalP = Path;
            return true;
        }
        this.naturalP = null;
        this.naturalL = null;
        return false;
    }

    public boolean setEyebrowRaisedP(String Path) {
        List<VisionDetRet> faceList = mFaceDet.detect(Path);
        if (faceList != null && faceList.size() == 1) { // Only one face detected by dlib
            eyebrowRaisedL = new FaceLandmarks(faceList.get(0).getFaceLandmarks());   // Get all landmarks group of the face
            this.eyebrowRaisedP = Path;
            return true;
        }
        this.eyebrowRaisedP = null;
        this.eyebrowRaisedL = null;
        return false;
    }

    public boolean setEyesClosedP(String Path) {
        List<VisionDetRet> faceList = mFaceDet.detect(Path);
        if (faceList != null && faceList.size() == 1) { // Only one face detected by dlib
            eyesClosedL = new FaceLandmarks(faceList.get(0).getFaceLandmarks());   // Get all landmarks group of the face
            this.eyesClosedP = Path;
            return true;
        }
        this.eyesClosedP=null;
        this.eyesClosedL=null;
        return false;
    }

    public boolean setUpperlipRasiedP(String Path) {
        List<VisionDetRet> faceList = mFaceDet.detect(Path);
        if (faceList != null && faceList.size() == 1) { // Only one face detected by dlib
            upperlipRasiedL = new FaceLandmarks(faceList.get(0).getFaceLandmarks());   // Get all landmarks group of the face
            this.upperlipRasiedP = Path;
            return true;
        }
        this.upperlipRasiedP =null;
        this.upperlipRasiedL =null;
        return false;
    }

    public boolean setSmileP(String Path) {
        List<VisionDetRet> faceList = mFaceDet.detect(Path);
        if (faceList != null && faceList.size() == 1) { // Only one face detected by dlib
            smileL = new FaceLandmarks(faceList.get(0).getFaceLandmarks());   // Get all landmarks group of the face
            this.smileP = Path;
            return true;
        }
        this.smileL=null;
        this.smileP=null;
        return false;
    }

    public boolean setKissP(String Path) {
        List<VisionDetRet> faceList = mFaceDet.detect(Path);
        if (faceList != null && faceList.size() == 1) { // Only one face detected by dlib
            kissL = new FaceLandmarks(faceList.get(0).getFaceLandmarks());   // Get all landmarks group of the face
            this.kissP = Path;
            return true;
        }
        this.kissP = null;
        this.kissL = null;
        return false;
    }
}