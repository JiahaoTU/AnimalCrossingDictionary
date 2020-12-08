package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.animalcrossingdictionary.R;

import nooks.animalcrossingdictionary.entities.fossils.Fossils;

public class FossilsDetailActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fossils_detail);

        textView = findViewById(R.id.id);

        Intent intent = getIntent();
        Fossils fossils = (Fossils) intent.getSerializableExtra("fossils");
        Log.d("Retrofit", "Success: "+ fossils.getFileName());
        textView.setText(fossils.getFileName());

    }
}