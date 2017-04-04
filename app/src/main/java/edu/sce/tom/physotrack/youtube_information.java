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

        VideoView informationVideoView = (VideoView)findViewById(R.id.informationVideoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(informationVideoView);
        Uri uri = Uri.parse("rtsp://r4---sn-4g5edn7y.googlevideo.com/Cj0LENy73wIaNAndvmELfSg5aBMYDSANFC2TeONYMOCoAUIASARgsvjCyOazvctYigELbElydkV6UG4tX0UM/D4069C3A3CB28C57B2BD0D8EAEDF0AEFA8F90DFA.4747A9145BA5C4C7320CDBE8B90432AD57E3777B/yt6/1/video.3gp");
        informationVideoView.setMediaController(mediaController);
        informationVideoView.setVideoURI(uri);
        informationVideoView.requestFocus();
        informationVideoView.start();

    }
}
