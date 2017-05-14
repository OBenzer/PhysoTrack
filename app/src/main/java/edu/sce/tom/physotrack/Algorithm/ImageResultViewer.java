package edu.sce.tom.physotrack.Algorithm;

public class ImageResultViewer {
    protected float eyeToBrowDisstance;
    protected float eyeArea;
    protected float mouthAngle;
    protected float mouthDisstance;
    protected float innerMouthAreat;
    protected float outerMouthArea;
    protected String expression;

    public ImageResultViewer(){

    }

    public ImageResultViewer(float eyeToBrowDisstance, float eyeArea, float mouthAngle, float mouthDisstance,
                             float innerMouthAreat, float outerMouthArea, String expression) {
        this.eyeToBrowDisstance = eyeToBrowDisstance;
        this.eyeArea = eyeArea;
        this.mouthAngle = mouthAngle;
        this.mouthDisstance = mouthDisstance;
        this.innerMouthAreat = innerMouthAreat;
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
    public float getInnerMouthAreat() {
        return innerMouthAreat;
    }
    public float getOuterMouthArea() {
        return outerMouthArea;
    }

    @Override
    public String toString() {
        return "ImageResult{" +
                "eyeToBrowDisstance=" + eyeToBrowDisstance +
                ", eyeArea=" + eyeArea +
                ", mouthAngle=" + mouthAngle +
                ", mouthDisstance=" + mouthDisstance +
                ", innerMouthAreat=" + innerMouthAreat +
                ", outerMouthArea=" + outerMouthArea +
                ", expression='" + expression + '\'' +
                '}';
    }
}
