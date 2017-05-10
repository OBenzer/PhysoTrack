package edu.sce.tom.physotrack.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.sce.tom.physotrack.Algorithm.ImageResult;
import edu.sce.tom.physotrack.Algorithm.LandmarksAnalyzer;
import edu.sce.tom.physotrack.Utils;


public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 7;

    // Database Name
    private static final String DATABASE_NAME = "BellsData.db";

    // Table Names
    public static final String TABLE_METRICS = "metrics";
    public static final String TABLE_IMAGE_RESULT = "image_result";

    // Common column names
    private static final String SESSION_DATE = "session_date";
    private static final String EXPRESSION = "expression";

    // metrics Table - column names
     private static final String LEFT_EYE_CENTER_X="leftEyeCenterX";
     private static final String LEFT_EYE_CENTER_Y="leftEyeCenterY";
     private static final String RIGHT_EYE_CENTER_X="rightEyeCenterX";
     private static final String RIGHT_EYE_CENTER_Y="rightEyeCenterY";
     private static final String LEFT_EYE_AREA="leftEyeArea";
     private static final String RIGHT_EYE_AREA="rightEyeArea";
     private static final String RIGHT_BROW_CENTER_X ="rightBrowCenterX";
     private static final String RIGHT_BROW_CENTER_Y ="rightBrowCenterY";
     private static final String LEFT_BROW_CENTER_X="leftBrowCenterX";
     private static final String LEFT_BROW_CENTER_Y="leftBrowCenterY";
     private static final String LEFT_EYE_TO_BROW_DISTANCE="leftEyeToBrowDistance";
     private static final String RIGHT_EYE_TO_BROW_DISTANCE="rightEyeToBrowDistance";
     private static final String LEFT_INNER_MOUTH_AREA="leftInnerMouthArea";
     private static final String RIGHT_INNER_MOUTH_AREA="rightInnerMouthArea";
     private static final String LEFT_OUTER_MOUTH_AREA="leftOuterMouthArea";
     private static final String RIGHT_OUTER_MOUTH_AREA="rightOuterMouthArea";
     private static final String RIGHT_MOUTH_EDGE_ANGLE="rightMouthEdgeAngle";
     private static final String LEFT_MOUTH_EDGE_ANGLE="leftMouthEdgeAngle";

    // image result - column names
    private static final String EYE_TO_BROW_DISTANCE="eyeToBrowDistance";
    private static final String EYE_AREA="eyeArea";
    private static final String MOUTH_ANGLE="mouthAngle";
    private static final String MOUTH_DISTANCE="mouthDistance";
    private static final String INNER_MOUTH_AREA="innerMouthArea";
    private static final String OUTER_MOUTH_AREA="outerMouthArea";


    // Table Create Statements
    // Metrics table create statement
    private static final String CREATE_TABLE_METRICS = "CREATE TABLE "
            + TABLE_METRICS + " (" + SESSION_DATE + " TEXT," + EXPRESSION + " TEXT,"
            + LEFT_EYE_CENTER_X + " REAL," + LEFT_EYE_CENTER_Y + " REAL," + RIGHT_EYE_CENTER_X
            + " REAL," + RIGHT_EYE_CENTER_Y + " REAL," + LEFT_EYE_AREA + " REAL,"
            + RIGHT_EYE_AREA + " REAL," + RIGHT_BROW_CENTER_X + " REAL,"
            + RIGHT_BROW_CENTER_Y + " REAL," + LEFT_BROW_CENTER_X + " REAL,"
            + LEFT_BROW_CENTER_Y + " REAL," + LEFT_EYE_TO_BROW_DISTANCE + " REAL," +
            RIGHT_EYE_TO_BROW_DISTANCE + " REAL," + LEFT_INNER_MOUTH_AREA + " REAL," +
            RIGHT_INNER_MOUTH_AREA + " REAL," + LEFT_OUTER_MOUTH_AREA + " REAL," +
            RIGHT_OUTER_MOUTH_AREA + " REAL," + RIGHT_MOUTH_EDGE_ANGLE + " REAL," +
            LEFT_MOUTH_EDGE_ANGLE + " REAL)";

    // image result table create statement
    private static final String CREATE_TABLE_IMAGE_RESULT = "CREATE TABLE "
            + TABLE_IMAGE_RESULT + " (" + SESSION_DATE + " TEXT," + EXPRESSION + " TEXT,"
            + EYE_TO_BROW_DISTANCE + " REAL," + EYE_AREA + " REAL," + MOUTH_ANGLE
            + " REAL," + MOUTH_DISTANCE + " REAL," + INNER_MOUTH_AREA + " REAL,"
            + OUTER_MOUTH_AREA + " REAL)";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE_METRICS);
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGE_RESULT);
        //create other tables the same way
    }

    //maybe backup tables before deleting them
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_METRICS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE_RESULT);
        onCreate(sqLiteDatabase);
        //or update other tables the same way
    }


    //inserting a metrics row to the database
    //return -1 if error happens otherwise return the row id (this is how db.insert function works)
    public long insertMetricsToDB(LandmarksAnalyzer metric) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SESSION_DATE, Utils.todaysDateToString());
        values.put(EXPRESSION, metric.getExpression());
        values.put(LEFT_EYE_CENTER_X, metric.getLeftEyeCenter().x);
        values.put(LEFT_EYE_CENTER_Y, metric.getLeftEyeCenter().y);
        values.put(RIGHT_EYE_CENTER_X, metric.getRightEyeCenter().x);
        values.put(RIGHT_EYE_CENTER_Y, metric.getLeftEyeCenter().y);
        values.put(LEFT_EYE_AREA, metric.getLeftEyeArea());
        values.put(RIGHT_EYE_AREA, metric.getRightEyeArea());
        values.put(RIGHT_BROW_CENTER_X, metric.getRightBrowCenter().x);
        values.put(RIGHT_BROW_CENTER_Y, metric.getRightBrowCenter().y);
        values.put(LEFT_BROW_CENTER_X, metric.getLeftBrowCenter().x);
        values.put(LEFT_BROW_CENTER_Y, metric.getLeftBrowCenter().y);
        values.put(LEFT_EYE_TO_BROW_DISTANCE, metric.getLeftEyeToBrowDistance());
        values.put(RIGHT_EYE_TO_BROW_DISTANCE, metric.getRightEyeToBrowDistance());
        values.put(LEFT_INNER_MOUTH_AREA, metric.getLeftInnerMouthArea());
        values.put(RIGHT_INNER_MOUTH_AREA, metric.getRightInnerMouthArea());
        values.put(LEFT_OUTER_MOUTH_AREA, metric.getLeftOuterMouthArea());
        values.put(RIGHT_OUTER_MOUTH_AREA, metric.getRightOuterMouthArea());
        values.put(RIGHT_MOUTH_EDGE_ANGLE, metric.getRightMouthEdgeAngle());
        values.put(LEFT_MOUTH_EDGE_ANGLE, metric.getLeftMouthEdgeAngle());

        // insert row
        long result= db.insert(TABLE_METRICS, null, values);
        db.close();
        return result;
    }


    //inserting a image result row to the database
    //return -1 if error happens otherwise return the row id (this is how db.insert function works)
    public long insertImageResultToDB(ImageResult imageResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SESSION_DATE, Utils.todaysDateToString());
        values.put(EXPRESSION, imageResult.getExpression());
        values.put(EYE_TO_BROW_DISTANCE, imageResult.getEyeToBrowDisstance());
        values.put(EYE_AREA, imageResult.getEyeArea());
        values.put(MOUTH_ANGLE, imageResult.getMouthAngle());
        values.put(MOUTH_DISTANCE, imageResult.getMouthDisstance());
        values.put(INNER_MOUTH_AREA, imageResult.getInnerMouthAreat());
        values.put(OUTER_MOUTH_AREA, imageResult.getOuterMouthArea());


        // insert row
        long result= db.insert(TABLE_IMAGE_RESULT, null, values);
        db.close();
        return result;
    }

}


