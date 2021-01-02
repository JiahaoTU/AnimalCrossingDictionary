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

import nooks.animalcrossingdictionary.entities.bugs.Bugs;

public class BugsDetailActivity extends AppCompatActivity {

    private ImageView image;

    private TextView nameEN,location, rarity, price, time;

    private ImageView blathers;
    private TextView museumPhrase;

    private Button north, south;

    private PopupWindow popupWindow;

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
        time.setText(bugs.getAvailability().getTime());

        blathers.setImageResource(R.drawable.blathers);
        museumPhrase.setText(bugs.getMuseumPhrase());

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] months = bugs.getAvailability().getMonthArrayNorthern();
                setPopupWindow(v, popupWindow, north, months);
            }
        });

        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] months = bugs.getAvailability().getMonthArraySouthern();
                setPopupWindow(v, popupWindow, south, months);
            }
        });

    }

    private void setPopupWindow(View view, PopupWindow popup, Button ns, int[] months) {
        View popupView = LayoutInflater.from(BugsDetailActivity.this).inflate(R.layout.month_popup, null);

        popup = new PopupWindow(popupView, 600, 300);
        popup.setOutsideTouchable(true);
        popup.update();
        List<Integer> monthList = Arrays.stream(months).boxed().collect(Collectors.toList());

        if(monthList.contains(1)) (popup.getContentView().findViewById(R.id.jan)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.jan)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(2)) (popup.getContentView().findViewById(R.id.feb)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.feb)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(3)) (popup.getContentView().findViewById(R.id.mar)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.mar)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(4)) (popup.getContentView().findViewById(R.id.apr)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.apr)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(5)) (popup.getContentView().findViewById(R.id.may)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.may)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(6)) (popup.getContentView().findViewById(R.id.jun)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.jun)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(7)) (popup.getContentView().findViewById(R.id.jul)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.jul)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(8)) (popup.getContentView().findViewById(R.id.aug)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.aug)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(9)) (popup.getContentView().findViewById(R.id.sep)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.sep)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(10)) (popup.getContentView().findViewById(R.id.oct)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.oct)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(11)) (popup.getContentView().findViewById(R.id.nov)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.nov)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(12)) (popup.getContentView().findViewById(R.id.dec)).setBackground(getDrawable(R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.dec)).setTextColor(Color.parseColor("#e0e0e0"));


        int[] loc = new int[2];
        view.getLocationOnScreen(loc);
        popup.showAtLocation(north, Gravity.NO_GRAVITY, loc[0], loc[1]-180);
    }

}