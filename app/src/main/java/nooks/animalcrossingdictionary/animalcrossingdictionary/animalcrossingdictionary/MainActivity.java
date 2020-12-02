package nooks.animalcrossingdictionary.animalcrossingdictionary.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.animalcrossingdictionary.R;

import nooks.animalcrossingdictionary.animalcrossingdictionary.animalcrossingdictionary.reception.Reception;

public class MainActivity extends AppCompatActivity {

    public static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);

        Reception.getUrl();
    }
}