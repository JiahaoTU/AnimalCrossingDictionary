package com.example.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.animalcrossingdictionary.reception.Reception;

public class MainActivity extends AppCompatActivity {

    public static String API_URL = "https://acnhapi.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reception.getUrl("fish");
    }
}