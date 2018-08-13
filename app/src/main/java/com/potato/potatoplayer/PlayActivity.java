package com.potato.potatoplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayActivity extends AppCompatActivity {


    @BindView(R.id.play_name_song)
    TextView playNameSong;
    @BindView(R.id.play_name_group)
    TextView playNameGroup;
    @BindView(R.id.play_image_album)
    ImageView playImageAlbum;
    @BindView(R.id.play_seek_bar)
    SeekBar playSeekBar;
    @BindView(R.id.play_previous_button)
    Button playPreviousButton;
    @BindView(R.id.play_play_button)
    Button playPlayButton;
    @BindView(R.id.play_next_button)
    Button playNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


    }

    @OnClick({R.id.play_previous_button, R.id.play_play_button, R.id.play_next_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_previous_button:
                break;
            case R.id.play_play_button:
                break;
            case R.id.play_next_button:
                break;
        }
    }
}
