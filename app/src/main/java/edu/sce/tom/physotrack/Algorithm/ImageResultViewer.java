package edu.sce.tom.physotrack.Algorithm;

import edu.sce.tom.physotrack.Utils;

public class ImageResultViewer {

    protected String date;
    protected float eyeToBrowDisstance;
    protected float eyeArea;
    protected float mouthAngle;
    protected float mouthDisstance;
    protected float innerMouthArea;
    protected float outerMouthArea;
    protected String expression;

    ImageResultViewer(String Exp) {
        date = Utils.todaysDateToString();
        expression = Exp;
    }

    public ImageResultViewer(String date, float eyeToBrowDisstance, float eyeArea, float mouthAngle, float mouthDisstance,
                             float innerMouthArea, float outerMouthArea, String expression) {
        this.date = date;
        this.eyeToBrowDisstance = eyeToBrowDisstance;
        this.eyeArea = eyeArea;
        this.mouthAngle = mouthAngle;
        this.mouthDisstance = mouthDisstance;
        this.innerMouthArea = innerMouthArea;
        this.outerMouthArea = outerMouthArea;
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public float getEyeToBrowDisstance() {
        return eyeToBrowDisstance;
    }

    public float getEyeArea() {
        return eyeArea;
    }

    public float getMouthAngle() {
        return mouthAngle;
    }

    public float getMouthDisstance() {
        return mouthDisstance;
    }

    public float getinnerMouthArea() {
        return innerMouthArea;
    }

    public float getOuterMouthArea() {
        return outerMouthArea;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return ""+date+"    "+expression+"   "+eyeToBrowDisstance+"   "+eyeArea+"  "+mouthAngle+"   "+innerMouthArea+"   "+outerMouthArea+" ";
               /* "date of image = " + date +
                ", expression = " + expression +
                ", eye To eyebrow Distance = " + eyeToBrowDisstance +
                ", eye Area = " + eyeArea +
                ", mouth Angle = " + mouthAngle +
                ", mouth Distance = " + mouthDisstance +
                ", inner Mouth Area = " + innerMouthArea +
                ", outer Mouth Area = " + outerMouthArea +
                "\n\n";*/
    }
}
