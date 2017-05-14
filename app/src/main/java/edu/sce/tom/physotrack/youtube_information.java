package edu.sce.tom.physotrack;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;
public class youtube_information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_information);


        String uriString = "rtsp://r12---sn-5hnedne7.googlevideo.com/Cj0LENy73wIaNAl03DV76iuaLxMYDSANFC3hFhhZMOCoAUIASARg8-fo87Gq3IpZigELanpTMTdHbEYtaTAM/CE7D44329A56E59E0BB4CE558E8EB095EF5888F6.4C6ABFE312A1BE0E5F6F0657976B3602419FF8F3/yt6/1/video.3gp" ;
        VideoView videoView = (VideoView)findViewById(R.id.videoViewInfo);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse(uriString);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }
}






