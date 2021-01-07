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

import nooks.animalcrossingdictionary.adapter.MonthPop;
import nooks.animalcrossingdictionary.entities.fish.Fish;

public class FishDetailActivity extends AppCompatActivity {
    private ImageView image;

    private TextView nameEN, size, location, rarity, price, time;

    private ImageView blathers;
    private TextView museumPhrase;

    private Button north, south;
    private MonthPop popupWindow;
    //private PopupWindow_Month popupWindow_month = new PopupWindow_Month();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_detail);

        image = findViewById(R.id.image);
        nameEN = findViewById(R.id.nameEN);
        size = findViewById(R.id.size_info);
        location = findViewById(R.id.location_info);
        rarity = findViewById(R.id.rarity_info);
        price = findViewById(R.id.price_info);
        time = findViewById(R.id.time_info);
        museumPhrase = findViewById(R.id.museumPhrase);
        north = findViewById(R.id.northernMonth);
        south = findViewById(R.id.southernMonth);

        Intent intent = getIntent();
        Fish fish = (Fish) intent.getSerializableExtra("fish");
        Glide.with(this).load(fish.getImage_uri()).centerCrop().into(image);
        nameEN.setText(fish.getName().getNameEUen());
        size.setText(fish.getShadow().split(" ")[0]);
        location.setText(fish.getAvailability().getLocation());
        rarity.setText(fish.getAvailability().getRarity());
        price.setText("" + fish.getPrice());
        if (fish.getAvailability().getTime().equals(""))
            time.setText("All day");
        else
            time.setText(fish.getAvailability().getTime());

        museumPhrase.setText(fish.getMuseumPhrase());

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] months = fish.getAvailability().getMonthArrayNorthern();
                popupWindow = new MonthPop(FishDetailActivity.this);
                popupWindow.setPopupWindow(v, north, months);
            }
        });

        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] months = fish.getAvailability().getMonthArraySouthern();
                popupWindow = new MonthPop(FishDetailActivity.this);
                popupWindow.setPopupWindow(v, south, months);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

}