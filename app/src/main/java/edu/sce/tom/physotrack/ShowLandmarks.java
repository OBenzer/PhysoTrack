package edu.sce.tom.physotrack;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.provider.BigImageCardProvider;
import com.dexafree.materialList.view.MaterialListView;
import java.io.IOException;
import java.util.ArrayList;

import edu.sce.tom.physotrack.Algorithm.FaceLandmarks;
import edu.sce.tom.physotrack.Algorithm.SesRunSingletone;
import edu.sce.tom.physotrack.Algorithm.SessionRunner;

public class ShowLandmarks extends AppCompatActivity {

    private MaterialListView materialListView;
    private SessionRunner sessionRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_landmarks);
        sessionRunner = SesRunSingletone.getInstance();
        materialListView = (MaterialListView) findViewById(R.id.material_listview);

        String path;
        FaceLandmarks landmarks;

        path=sessionRunner.getSmileP();
        landmarks=sessionRunner.getSmileL();
        if(path!=null&&landmarks!=null)
            addCard(path,"Smile",landmarks.getAllLandmarks());

        path=sessionRunner.getKissP();
        landmarks=sessionRunner.getKissL();
        if(path!=null&&landmarks!=null)
            addCard(path,"Kiss",landmarks.getAllLandmarks());

        path=sessionRunner.getNaturalP();
        landmarks=sessionRunner.getNaturalL();
        if(path!=null&&landmarks!=null)
            addCard(path,"Blankly",landmarks.getAllLandmarks());

        path=sessionRunner.getEyebrowRaisedP();
        landmarks=sessionRunner.getEyebrowRaisedL();
        if(path!=null&&landmarks!=null)
            addCard(path,"Eyebrows Raised",landmarks.getAllLandmarks());

        path=sessionRunner.getEyesClosedP();
        landmarks=sessionRunner.getEyesClosedL();
        if(path!=null&&landmarks!=null)
            addCard(path,"Eyes Closed",landmarks.getAllLandmarks());

        path=sessionRunner.getUpperlipRasiedP();
        landmarks=sessionRunner.getUpperlipRasiedL();
        if(path!=null&&landmarks!=null)
            addCard(path,"Rabbit",landmarks.getAllLandmarks());
    }

    private void addCard(String imgPath, String expresion, ArrayList<Point> landmarks) {
        if (landmarks != null) {
            Card card = new Card.Builder(getApplicationContext())
                    .withProvider(BigImageCardProvider.class)
                    .setDrawable(drawLandmarksOnImage(imgPath, landmarks, Color.GREEN))
                    .setTitle(expresion)
                    .endConfig()
                    .build();
            materialListView.add(card);
        }
    }

    private BitmapDrawable drawLandmarksOnImage(String path, ArrayList<Point> landmarks, int color) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        android.graphics.Bitmap.Config bitmapConfig = bm.getConfig();
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }

        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bm = bm.copy(bitmapConfig, true);
        int width = bm.getWidth();
        int height = bm.getHeight();
        // By ratio scale
        float aspectRatio = bm.getWidth() / (float) bm.getHeight();

        final int MAX_SIZE = 512;
        int newWidth = MAX_SIZE;
        int newHeight = MAX_SIZE;
        float resizeRatio = 1;
        newHeight = Math.round(newWidth / aspectRatio);
        if (bm.getWidth() > MAX_SIZE && bm.getHeight() > MAX_SIZE) {
            Log.d("Draw", "Resize Bitmap");
            bm = getResizedBitmap(bm, newWidth, newHeight);
            resizeRatio = (float) bm.getWidth() / (float) width;
            Log.d("Draw", "resizeRatio " + resizeRatio);
        }

        bm = rotateIamgeIfNeeded(path, bm);

        // Create canvas to draw
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        for (Point point : landmarks) {
            int pointX = (int) (point.x * resizeRatio);
            int pointY = (int) (point.y * resizeRatio);
            canvas.drawCircle(pointX, pointY, 2, paint);
        }

        return new BitmapDrawable(getResources(), bm);
    }

    protected Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(bm, newWidth, newHeight, true);
    }

    private Bitmap rotateIamgeIfNeeded(String path, Bitmap bm){
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
            return bm;
        }
        try {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            return rotateBitmap(bm, orientation);
        }catch (NullPointerException e) {
            e.printStackTrace();
            return bm;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    public void btn_back_onclick(View v) {
        onBackPressed();
    }

}
