package edu.sce.tom.physotrack;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewSession extends AppCompatActivity {
    private static final int REQUEST_TAKE_PHOTO_SMILE = 1001;
    private static final int REQUEST_TAKE_PHOTO_KISS = 1002;
    private static final int REQUEST_TAKE_PHOTO_EYECLOSE = 1003;
    private static final int REQUEST_TAKE_PHOTO_RABBIT = 1004;
    private static final int REQUEST_TAKE_PHOTO_BLANKLY = 1005;
    private static final int REQUEST_TAKE_PHOTO_BROWLIFT = 1006;
    private final int SMILE = 1;
    private final int KISS = 2;
    private final int EYECLOSE = 3;
    private final int RABBIT = 4;
    private final int BLANKLY = 5;
    private final int BROWLIFT = 6;


    private Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);
        //request permission from the user android 6+
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }


    // When clicked move to camera //
    public void btn_kiss_photo_On_click(View v) {
        takePic(KISS);
    }

    public void btn_smile_photo_On_click(View v) {
        takePic(SMILE);
    }

    public void btn_eyeClose_photo_On_click(View v) {
        takePic(EYECLOSE);
    }

    public void btn_blankly_photo_On_click(View v) {
        takePic(BLANKLY);
    }

    public void btn_rabbit_photo_On_click(View v) {
        takePic(RABBIT);
    }

    public void btn_browLifts_photo_On_click(View v) {
        takePic(BROWLIFT);
    }


    //request permissions android 6+
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i("permission", "permission granted");
            }
        }
    }

    public void takePic(int position) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        switch (position) {
            case SMILE:
                file = Uri.fromFile(getOutputMediaFile("Smile"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO_SMILE);
                break;
            case KISS:
                file = Uri.fromFile(getOutputMediaFile("Kiss"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO_KISS);
                break;
            case BLANKLY:
                file = Uri.fromFile(getOutputMediaFile("Blankly"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO_BLANKLY);
                break;
            case RABBIT:
                file = Uri.fromFile(getOutputMediaFile("Rabbit"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO_RABBIT);
                break;
            case EYECLOSE:
                file = Uri.fromFile(getOutputMediaFile("EyesClosed"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO_EYECLOSE);
                break;
            case BROWLIFT:
                file = Uri.fromFile(getOutputMediaFile("BrowLift"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO_BROWLIFT);
                break;
            default:
                break;
        }

    }

    //saves in storage/emulated/0/pictures/PhysoAblum
    //here we can devide into folders by date
    private File getOutputMediaFile(String s) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "PhysoAlbum" + File.separator + timeStamp);
        Toast.makeText(this, mediaStorageDir.toString(), Toast.LENGTH_SHORT).show();

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Toast.makeText(this, "error creating file", Toast.LENGTH_SHORT).show();
                return null;
            }

        }
        //name of the image
        return new File(mediaStorageDir.getPath() + File.separator +
                s + ".jpg");
    }

    //to display image
    //checks if the an image was taken and if so displays it
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //reduce size of image before displaying it
                // img.setImageBitmap(getResizedBitmap(bitmap,1000));
            }
        }
    }


    // reduces the size of the image for displaying the image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}


