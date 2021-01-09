package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animalcrossingdictionary.R;
import nooks.animalcrossingdictionary.entities.villagers.Villagers;

public class VillagersDetailActivity extends AppCompatActivity {
    private ImageView image;

    private TextView nameEn, species, gender, birth, personality, catchPhrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villagers_detail);

        image = findViewById(R.id.image);
        nameEn = findViewById(R.id.nameEN);
        species = findViewById(R.id.species_info);
        gender = findViewById(R.id.gender_info);
        birth = findViewById(R.id.birth_info);
        personality = findViewById(R.id.personality_info);
        catchPhrase = findViewById(R.id.catchPhrase_info);

        Intent intent = getIntent();
        Villagers villager = (Villagers) intent.getSerializableExtra("villager");
        Glide.with(this).load(villager.getImage_uri()).centerCrop().into(image);
        nameEn.setText(villager.getName().getNameEUen());
        species.setText(villager.getSpecies());
        gender.setText(villager.getGender());
        birth.setText(villager.getBirthdayString());
        personality.setText(villager.getPersonality());
        catchPhrase.setText(villager.getCatchPhrase());

    }
}