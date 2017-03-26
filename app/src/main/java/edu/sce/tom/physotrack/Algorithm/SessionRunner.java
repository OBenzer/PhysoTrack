package edu.sce.tom.physotrack.Algorithm;

import android.content.Context;
import edu.sce.tom.physotrack.R;
import com.tzutalin.dlib.Constants;
import com.tzutalin.dlib.FaceDet;
import com.tzutalin.dlib.VisionDetRet;
import java.io.File;
import java.util.List;

public class SessionRunner {
    //attributes//
    //___P - position image path
    //___L - position FaceLandmarks object
    //___AR - position analysis results
    private String naturalP=null;
    private FaceLandmarks naturalL=null;
    private LandmarksAnalyzer naturalAR=null;

    private String eyebrowRaisedP=null;
    private FaceLandmarks eyebrowRaisedL=null;
    private LandmarksAnalyzer eyebrowRaisedAR=null;

    private String eyesClosedP=null;
    private FaceLandmarks eyesClosedL=null;
    private LandmarksAnalyzer eyesClosedAR=null;

    private String upperlipRasiedP=null;
    private FaceLandmarks upperlipRasiedL=null;
    private LandmarksAnalyzer upperlipRasiedAR=null;

    private String smileP=null;
    private FaceLandmarks smileL=null;
    private LandmarksAnalyzer smileAR=null;

    private String kissP=null;
    private FaceLandmarks kissL=null;
    private LandmarksAnalyzer kissAR=null;

    private FaceDet mFaceDet=null;
    private Context context=null;

    //ctor//
    //looks for the 68.dat file in not found recreates it from the application
    //and initialize the facedet object for detection
    public SessionRunner(Context c) {
        this.context=c;
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

        }

        if(eyebrowRaisedP!=null){
            eyebrowRaisedAR = new LandmarksAnalyzer(eyebrowRaisedL);

        }

        if(eyesClosedP!=null){
            eyesClosedAR = new LandmarksAnalyzer(eyesClosedL);

        }

        if(upperlipRasiedP!=null){
            upperlipRasiedAR = new LandmarksAnalyzer(upperlipRasiedL);

        }

        if(smileP!=null){
            smileAR = new LandmarksAnalyzer(smileL);

        }

        if(kissP!=null){
            kissAR = new LandmarksAnalyzer(kissL);

        }

        //Saving results to DB//

        //Sums-Up results //

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
