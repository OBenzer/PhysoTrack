package edu.sce.tom.physotrack;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;


public class PhotoGuide extends AppCompatActivity {

    ImageView kissInfo, eyeBrow, eyesClosed, rabbit, smile, blankly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_guide);
        init();
        setImg();
    }

    private void init() {
        kissInfo = (ImageView) findViewById(R.id.img_kiss_info);
        eyeBrow = (ImageView) findViewById(R.id.img_eye_brow_info);
        eyesClosed = (ImageView) findViewById(R.id.img_eyes_closed_info);
        rabbit = (ImageView) findViewById(R.id.img_rabit_info);
        smile = (ImageView) findViewById(R.id.img_smile_info);
        blankly = (ImageView) findViewById(R.id.img_blankly_info);
    }

    private void setImg() {
        kissInfo.setImageDrawable(resizeImage(R.drawable.kiss_info));
        eyeBrow.setImageDrawable(resizeImage(R.drawable.browlift_info));
        eyesClosed.setImageDrawable(resizeImage(R.drawable.eyeclose_info));
        rabbit.setImageDrawable(resizeImage(R.drawable.rabbit_info));
        smile.setImageDrawable(resizeImage(R.drawable.smile_info));
        blankly.setImageDrawable(resizeImage(R.drawable.blankly_info));
    }

    //try to reduce image size using these functions

    public Drawable resizeImage(int imageID) {
        // Get device dimensions
        Display display = getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();

        BitmapDrawable bd = (BitmapDrawable) this.getResources().getDrawable(
                imageID);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageID);

        return new BitmapDrawable(this.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));
    }

    /************************ Resize Bitmap *********************************/
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap

        return Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);
    }
}