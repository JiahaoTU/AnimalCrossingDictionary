package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animalcrossingdictionary.R;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import nooks.animalcrossingdictionary.entities.fossils.Fossils;

public class FossilsDetailActivity extends AppCompatActivity {

    private ImageView image;

    private TextView nameEN, price;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fossils_detail);

        image = findViewById(R.id.image);


        nameEN = findViewById(R.id.nameEN);
        price = findViewById(R.id.price_info);

        Intent intent = getIntent();
        Fossils fossils = (Fossils) intent.getSerializableExtra("fossils");

        Glide.with(this).load(fossils.getImage_uri()).centerCrop().into(image);

        nameEN.setText(fossils.getName().getNameEUen());

    }



}