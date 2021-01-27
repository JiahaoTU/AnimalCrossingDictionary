package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animalcrossingdictionary.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nooks.animalcrossingdictionary.adapter.PopupWindow_Month;
import nooks.animalcrossingdictionary.entities.bugs.Bugs;

public class BugsDetailActivity extends AppCompatActivity {

    private ImageView image;

    private TextView nameEN,location, rarity, price, time;

    private ImageView blathers;
    private TextView museumPhrase;

    private Button north, south;

    private PopupWindow_Month popupWindow_month = new PopupWindow_Month();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugs_detail);

        image = findViewById(R.id.image);

        nameEN = findViewById(R.id.nameEN);
        location = findViewById(R.id.location_info);
        rarity = findViewById(R.id.rarity_info);
        price = findViewById(R.id.price_info);
        time = findViewById(R.id.time_info);

        blathers = findViewById(R.id.blathers);
        museumPhrase = findViewById(R.id.museumPhrase);

        north = findViewById(R.id.northernMonth);
        south = findViewById(R.id.southernMonth);

        Intent intent = getIntent();
        Bugs bugs = (Bugs) intent.getSerializableExtra("bugs");

        Glide.with(this).load(bugs.getImage_uri()).centerCrop().into(image);

        nameEN.setText(bugs.getName().getNameEUen());
        location.setText(bugs.getAvailability().getLocation());
        rarity.setText(bugs.getAvailability().getRarity());
        price.setText("" + bugs.getPrice());
        if (bugs.getAvailability().getTime().equals(""))
            time.setText("All day");
        else
            time.setText(bugs.getAvailability().getTime());

        blathers.setImageResource(R.drawable.blathers);
        museumPhrase.setText(bugs.getMuseumPhrase());

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] months = bugs.getAvailability().getMonthArrayNorthern();
                popupWindow_month.setPopupWindow(BugsDetailActivity.this, v, popupWindow_month.popupWindow, north, months);
            }
        });

        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] months = bugs.getAvailability().getMonthArraySouthern();
                popupWindow_month.setPopupWindow(BugsDetailActivity.this, v, popupWindow_month.popupWindow, south, months);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow_month.popupWindow != null) {
            popupWindow_month.popupWindow.dismiss();
        }
    }

}