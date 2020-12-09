package nooks.animalcrossingdictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalcrossingdictionary.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import nooks.animalcrossingdictionary.entities.fish.Fish;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder_fish extends RecyclerView.ViewHolder {

        public TextView name, loc, size, price, place, month, rarity, time;
        public ImageView image;

        public MyViewHolder_fish(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            loc = view.findViewById(R.id.location_info);
            size = view.findViewById(R.id.size_info);
            price = view.findViewById(R.id.price_info);
            place = view.findViewById(R.id.place_info);
            month = view.findViewById(R.id.month_info);
            rarity = view.findViewById(R.id.rarity_info);
            time = view.findViewById(R.id.time_info);
            image = view.findViewById(R.id.imageView);
        }
    }


    private List<Fish> listData;
    public Adapter(List<Fish> data) {
        this.listData = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fish, parent, false);
        return new MyViewHolder_fish(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder_fish)holder).name.setText(listData.get(position).getFileName());
        ((MyViewHolder_fish)holder).loc.setText(listData.get(position).getAvailability().getLocation());
        ((MyViewHolder_fish)holder).size.setText(listData.get(position).getShadow());
        ((MyViewHolder_fish)holder).price.setText(String.valueOf(listData.get(position).getPrice()));
        ((MyViewHolder_fish)holder).place.setText(listData.get(position).getFileName());
        ((MyViewHolder_fish)holder).month.setText(listData.get(position).getAvailability().getMouthNorthern());
        ((MyViewHolder_fish)holder).rarity.setText(listData.get(position).getAvailability().getRarity());
        ((MyViewHolder_fish)holder).time.setText(listData.get(position).getAvailability().getTime());
        Picasso.get().load(listData.get(position).getIcon_uri()).into(((MyViewHolder_fish)holder).image);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
