package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.animalcrossingdictionary.R;

import nooks.animalcrossingdictionary.retrofit.Reception;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void fishButton(View view){
        Intent intent = new Intent(this, FishActivity.class);
        startActivity(intent);
    }
}