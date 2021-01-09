package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animalcrossingdictionary.R;

import nooks.animalcrossingdictionary.entities.fossils.Fossils;

public class FossilsDetailActivity extends AppCompatActivity {
    private ImageView image;

    private TextView nameEN, price;

    private TextView museumPhrase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fossils_detail);

        image = findViewById(R.id.image);
        nameEN = findViewById(R.id.nameEN);

        price = findViewById(R.id.price_info);

        museumPhrase = findViewById(R.id.museumPhrase);


        Intent intent = getIntent();
        Fossils fossils = (Fossils) intent.getSerializableExtra("fossils");
        Glide.with(this).load(fossils.getImage_uri()).centerCrop().into(image);
        nameEN.setText(fossils.getName().getNameEUen());

        price.setText("" + fossils.getPrice());

        museumPhrase.setText(fossils.getMuseumPhrase());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        }
    }

