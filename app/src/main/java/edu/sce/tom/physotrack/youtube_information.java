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

        VideoView videoView = (VideoView)findViewById(R.id.videoViewInfo);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("rtsp://r16---sn-5hne6n7r.googlevideo.com/Cj0LENy73wIaNAndvmELfSg5aBMYDSANFC3DQw9ZMOCoAUIASARg1v-h-peYkodZigELdnJmNUFUSGVLQmsM/39293BD4441D54E1AF8A5CB64FD732B05C990CCC.C5EC6F366348639F868F3AF8BF53E8E982202712/yt6/1/video.3gp");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }
}







