package edu.sce.tom.physotrack;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.sce.tom.physotrack.Algorithm.SesRunSingletone;
import edu.sce.tom.physotrack.Algorithm.SessionRunner;

public class NewSession extends AppCompatActivity {
    private static final int REQUEST_TAKE_PHOTO_INITIAL = 1000;
    private static final int REQUEST_PICK_IMAGE_INITIAL = 2000;
    private static final int SMILE = 1;
    private static final int KISS = 2;
    private static final int EYECLOSE = 3;
    private static final int RABBIT = 4;
    private static final int BLANKLY = 5;
    private static final int BROWLIFT = 6;
    private static final int REQUEST_TAKE_PHOTO_SMILE = REQUEST_TAKE_PHOTO_INITIAL+SMILE;
    private static final int REQUEST_TAKE_PHOTO_KISS = REQUEST_TAKE_PHOTO_INITIAL+KISS;
    private static final int REQUEST_TAKE_PHOTO_EYECLOSE = REQUEST_TAKE_PHOTO_INITIAL+EYECLOSE;
    private static final int REQUEST_TAKE_PHOTO_RABBIT = REQUEST_TAKE_PHOTO_INITIAL+RABBIT;
    private static final int REQUEST_TAKE_PHOTO_BLANKLY = REQUEST_TAKE_PHOTO_INITIAL+BLANKLY;
    private static final int REQUEST_TAKE_PHOTO_BROWLIFT = REQUEST_TAKE_PHOTO_INITIAL+BROWLIFT;
    private static final int REQUEST_PICK_IMAGE_SMILE = REQUEST_PICK_IMAGE_INITIAL+SMILE;
    private static final int REQUEST_PICK_IMAGE_KISS = REQUEST_PICK_IMAGE_INITIAL+KISS;
    private static final int REQUEST_PICK_IMAGE_EYECLOSE = REQUEST_PICK_IMAGE_INITIAL+EYECLOSE;
    private static final int REQUEST_PICK_IMAGE_RABBIT = REQUEST_PICK_IMAGE_INITIAL+RABBIT;
    private static final int REQUEST_PICK_IMAGE_BLANKLY = REQUEST_PICK_IMAGE_INITIAL+BLANKLY;
    private static final int REQUEST_PICK_IMAGE_BROWLIFT = REQUEST_PICK_IMAGE_INITIAL+BROWLIFT;
    private static final int MINIMUMIMAGECOUNT = 1;

    private Uri file;
    private SessionRunner sessionRunner;
    private int imageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);
        //request permission from the user android 6+
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        imageCount = 0;
        sessionRunner = SesRunSingletone.getInstance();
     }

    // Capture image buttons //
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

    // Pick image from gallery buttons //
    public void btn_kiss_gallery_On_click(View v) { pickImage(REQUEST_PICK_IMAGE_KISS); }
    public void btn_smile_gallery_On_click(View v) { pickImage(REQUEST_PICK_IMAGE_SMILE); }
    public void btn_eyeClose_gallery_On_click(View v) { pickImage(REQUEST_PICK_IMAGE_EYECLOSE); }
    public void btn_blankly_gallery_On_click(View v) { pickImage(REQUEST_PICK_IMAGE_BLANKLY); }
    public void btn_rabbit_gallery_On_click(View v) { pickImage(REQUEST_PICK_IMAGE_RABBIT); }
    public void btn_browLifts_gallery_On_click(View v) { pickImage(REQUEST_PICK_IMAGE_BROWLIFT); }

    public void btn_submit_On_click(View v){
        if(imageCount>=MINIMUMIMAGECOUNT) {
            Toast.makeText(this, "Start to analize your session", Toast.LENGTH_SHORT).show();
            sessionRunner.run();
        }
        else
            Toast.makeText(this, "More images needed for this session!", Toast.LENGTH_SHORT).show();
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

    // Method to handle the event when the user want to capture new image for the session //
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

    // Method to handle the event when the user want to pic image from gallery to the session //
    public void pickImage(int requestCode) {
        Toast.makeText(this, "Pick an image", Toast.LENGTH_SHORT).show();
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, requestCode);
    }

    //saves in storage/emulated/0/pictures/PhysoAblum
    //here we can devide into folders by date
    private File getOutputMediaFile(String s) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "PhysoAlbum" + File.separator + timeStamp);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Expression photo has been taken by the user//
        if(requestCode==REQUEST_TAKE_PHOTO_SMILE||requestCode==REQUEST_TAKE_PHOTO_KISS||requestCode==REQUEST_TAKE_PHOTO_EYECLOSE
            ||requestCode==REQUEST_TAKE_PHOTO_RABBIT||requestCode==REQUEST_TAKE_PHOTO_BLANKLY||requestCode==REQUEST_TAKE_PHOTO_BROWLIFT){
            handleNewImage(requestCode-REQUEST_TAKE_PHOTO_INITIAL, resultCode);
        }

        else if(requestCode==REQUEST_PICK_IMAGE_SMILE||requestCode==REQUEST_PICK_IMAGE_KISS||requestCode==REQUEST_PICK_IMAGE_EYECLOSE
                ||requestCode==REQUEST_PICK_IMAGE_RABBIT||requestCode==REQUEST_PICK_IMAGE_BLANKLY||requestCode==REQUEST_PICK_IMAGE_BROWLIFT) {
            if(resultCode!=RESULT_OK)
                Toast.makeText(this, "Error picking image", Toast.LENGTH_SHORT).show();
            else {
                resultCode = copyPickedImage(requestCode - REQUEST_PICK_IMAGE_INITIAL, data.getData());
                handleNewImage(requestCode - REQUEST_PICK_IMAGE_INITIAL, resultCode);
            }
        }

        //Yotam's testing - to display image
        //checks if the an image was taken and if so displays it
        else if (requestCode == 100) {
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

    private void handleNewImage(int requestCode, int resultCode){
        switch(requestCode){
            case SMILE:
                if(resultCode!=RESULT_OK)
                    Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show();
                else {
                    if(!sessionRunner.setSmileP(file.getPath())){   //Failed to set the path of the image (no face found) must take new photo! (it initialized to nuul)
                        imageCount--;
                        ((TextView)findViewById(R.id.smile_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape)); //Reset the button to the original Button look.
                        Toast.makeText(this, "You should retake smile photo, please use the guide", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        imageCount++;
                        ((TextView)findViewById(R.id.smile_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape_ok)); //Reset the button to the original Button look.
                    }
                }
                break;
            case KISS:
                if(resultCode!=RESULT_OK)
                    Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show();
                else {
                    if(!sessionRunner.setKissP(file.getPath())){   //Failed to set the path of the image (no face found) must take new photo! (it initialized to nuul)
                        imageCount--;
                        ((TextView)findViewById(R.id.kiss_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape)); //Reset the button to the original Button look.
                        Toast.makeText(this, "You should retake kiss photo, please use the guide", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        imageCount++;
                        ((TextView) findViewById(R.id.kiss_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape_ok)); //Reset the button to the original Button look.
                    }
                }
                break;
            case EYECLOSE:
                if(resultCode!=RESULT_OK)
                    Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show();
                else {
                    if(!sessionRunner.setEyesClosedP(file.getPath())){   //Failed to set the path of the image (no face found) must take new photo! (it initialized to nuul)
                        imageCount--;
                        ((TextView)findViewById(R.id.eyeClose_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape)); //Reset the button to the original Button look.
                        Toast.makeText(this, "You should retake Eyes Closed photo, please use the guide", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        imageCount++;
                        ((TextView) findViewById(R.id.eyeClose_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape_ok)); //Reset the button to the original Button look.
                    }
                }
                break;
            case RABBIT:
                if(resultCode!=RESULT_OK)
                    Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show();
                else {
                    if(!sessionRunner.setUpperlipRasiedP(file.getPath())){   //Failed to set the path of the image (no face found) must take new photo! (it initialized to nuul)
                        imageCount--;
                        ((TextView)findViewById(R.id.rabbit_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape)); //Reset the button to the original Button look.
                        Toast.makeText(this, "You should retake rabbit photo, please use the guide", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        imageCount++;
                        ((TextView) findViewById(R.id.rabbit_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape_ok)); //Reset the button to the original Button look.
                    }
                }
                break;
            case BLANKLY:
                if(resultCode!=RESULT_OK)
                    Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show();
                else {
                    if(!sessionRunner.setNaturalP(file.getPath())){   //Failed to set the path of the image (no face found) must take new photo! (it initialized to nuul)
                        imageCount--;
                        ((TextView)findViewById(R.id.blankly_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape)); //Reset the button to the original Button look.
                        Toast.makeText(this, "You should retake blankly photo, please use the guide", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        imageCount++;
                        ((TextView) findViewById(R.id.blankly_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape_ok)); //Reset the button to the original Button look.
                    }
                }
                break;
            case BROWLIFT:
                if(resultCode!=RESULT_OK)
                    Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show();
                else {
                    if(!sessionRunner.setEyebrowRaisedP(file.getPath())){   //Failed to set the path of the image (no face found) must take new photo! (it initialized to nuul)
                        imageCount--;
                        ((TextView)findViewById(R.id.browLift_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape)); //Reset the button to the original Button look.
                        Toast.makeText(this, "You should retake browLift photo, please use the guide", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        imageCount++;
                        ((TextView) findViewById(R.id.browLift_tv)).setBackground(getResources().getDrawable(R.drawable.buttonshape_ok)); //Reset the button to the original Button look.
                    }
                }
                break;
        }
    }

    private int copyPickedImage(int position, Uri source) {
        String posString;
        switch(position){
            case SMILE:
                posString = "Smile";
                break;
            case KISS:
                posString = "Kiss";
                break;
            case EYECLOSE:
                posString = "EyesClosed";
                break;
            case RABBIT:
                posString = "Rabbit";
                break;
            case BLANKLY:
                posString = "Blankly";
                break;
            case BROWLIFT:
                posString = "BrowLift";
                break;
            default:
                posString = "Unknown";
        }

        file = Uri.fromFile(getOutputMediaFile(posString));
        try {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(source, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String sourcePath = cursor.getString(columnIndex);
            cursor.close();

            InputStream in = new FileInputStream(sourcePath);
            OutputStream out = new FileOutputStream(file.getPath());

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            return RESULT_OK;
        } catch (IOException e) {
            Toast.makeText(this, "Failed copy image to application directory", Toast.LENGTH_SHORT).show();
            return RESULT_CANCELED;
        } catch (NullPointerException e) {
            Toast.makeText(this, "Failed copy image to application directory", Toast.LENGTH_SHORT).show();
            return RESULT_CANCELED;
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

    // handling i buttons
    public void KissInfo_Button_Clicked(View v)
    {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.kiss_info);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setTitle("Kiss Expression").
                        setIcon(R.drawable.logo).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();
    }

    public void BlanklyInfo_Button_Clicked(View v)
    {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.blankly_info);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setTitle("Blankly Expression").
                        setIcon(R.drawable.logo).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();
    }

    public void BrowLiftInfo_Button_Clicked(View v)
    {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.browlift_info);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setTitle("Brow Lift Expression").
                        setIcon(R.drawable.logo).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();
    }

    public void EyeCloseInfo_Button_Clicked(View v)
    {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.eyeclose_info);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setTitle("Eye Closed Expression").
                        setIcon(R.drawable.logo).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();
    }

    public void RabbitInfo_Button_Clicked(View v)
    {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.rabbit_info);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setTitle("Rabbit Expression").
                        setIcon(R.drawable.logo).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();
    }

    public void SmileInfo_Button_Clicked(View v)
    {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.smile_info);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setTitle("Smile Expression").
                        setIcon(R.drawable.logo).
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.setInverseBackgroundForced(true); // api 23 and higher
        builder.create().show();
    }
}