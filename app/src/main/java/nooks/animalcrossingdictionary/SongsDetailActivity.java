package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animalcrossingdictionary.R;

import java.io.IOException;

import nooks.animalcrossingdictionary.entities.songs.Songs;

public class SongsDetailActivity extends AppCompatActivity {

    private ImageView image;

    private TextView nameEN, price;

    private String songPath;

    public MediaPlayer mediaPlayer;

    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_detail);

        image = findViewById(R.id.image);

        nameEN = findViewById(R.id.nameEN);
        price = findViewById(R.id.price_info);

        Intent intent = getIntent();
        Songs songs = (Songs) intent.getSerializableExtra("songs");

        Glide.with(this).load(songs.getImage_uri()).centerCrop().into(image);

        nameEN.setText(songs.getName().getNameEUen());
        price.setText("" + songs.getBuyPrice());

        songPath = songs.getMusic_uri();
        Log.d("Song URL:", songPath);

        mediaPlayer = new MediaPlayer();
        btnPlay = findViewById(R.id.btn_play);


        try {
            mediaPlayer.setDataSource(songPath);
            mediaPlayer.prepare();
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        btnPlay.setText("Resume");
                    } else {
                        mediaPlayer.start();
                        btnPlay.setText("Pause");
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

}