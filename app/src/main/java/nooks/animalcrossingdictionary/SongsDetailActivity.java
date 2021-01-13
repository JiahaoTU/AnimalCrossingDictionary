package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animalcrossingdictionary.R;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import nooks.animalcrossingdictionary.entities.songs.Songs;

public class SongsDetailActivity extends AppCompatActivity {

    private ImageView image;

    private LinearLayout layout_price, layout_obtain;
    private TextView nameEN, price;

    private String songPath;

    public MediaPlayer mediaPlayer;

    private SeekBar seekBar;
    private boolean isSeekBarChanging;
    private Timer timer;
    private TimerTask timerTask;

    private TextView musicCurrent, musicLength;
    private ImageButton btnPlay;

    private SimpleDateFormat format = new SimpleDateFormat("mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_detail);

        image = findViewById(R.id.image);

        layout_price = findViewById(R.id.layout_price);
        layout_obtain = findViewById(R.id.layout_obtain);

        nameEN = findViewById(R.id.nameEN);
        price = findViewById(R.id.price_info);

        Intent intent = getIntent();
        Songs songs = (Songs) intent.getSerializableExtra("songs");

        Glide.with(this).load(songs.getImage_uri()).centerCrop().into(image);

        nameEN.setText(songs.getName().getNameEUen());
        if(songs.getOrderable()) {
            price.setText("" + songs.getBuyPrice());
            layout_obtain.setVisibility(View.GONE);
        } else {
            layout_price.setVisibility(View.GONE);
        }

        songPath = songs.getMusic_uri();

        mediaPlayer = new MediaPlayer();
        seekBar = findViewById(R.id.seekBar);

        musicCurrent = findViewById(R.id.music_current);
        musicLength = findViewById(R.id.music_length);

        btnPlay = findViewById(R.id.btn_play);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!isSeekBarChanging && mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            musicCurrent.setText(format.format(mediaPlayer.getCurrentPosition())+"");
                        }
                    });
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isSeekBarChanging = false;
                if(mediaPlayer != null)
                    mediaPlayer.seekTo(seekBar.getProgress());

            }
        });

        initializeMediaPlayer();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnPlay.setImageResource(R.drawable.play_music);
                mediaPlayer.seekTo(0);
            }
        });
    }

    private void initializeMediaPlayer() {
        try {
            mediaPlayer.setDataSource(songPath);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    btnPlay.setImageResource(R.drawable.play_music);
                    musicCurrent.setText("00:00");
                    musicLength.setText(format.format(mediaPlayer.getDuration())+"");
                    btnPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                                btnPlay.setImageResource(R.drawable.play_music);
                            } else {
                                mediaPlayer.start();
                                btnPlay.setImageResource(R.drawable.pause_music);

                            }
                        }
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {

        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (timerTask != null){
            timerTask.cancel();
            timerTask = null;
        }

        if(timer != null) {
            timer.cancel();
            timer = null;
        }

        super.onDestroy();
    }

}