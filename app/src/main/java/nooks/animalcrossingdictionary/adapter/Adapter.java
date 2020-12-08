package nooks.animalcrossingdictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalcrossingdictionary.R;

import java.util.List;

import nooks.animalcrossingdictionary.entities.fish.Fish;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder_fish extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView size, loc, price, month, rarity, time;
        public ImageView icon;

        public MyViewHolder_fish(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            size = view.findViewById(R.id.size_info);
            loc = view.findViewById(R.id.location_info);
            price = view.findViewById(R.id.price_info);
            month = view.findViewById(R.id.month_info);
            rarity = view.findViewById(R.id.rarity_info);
            time = view.findViewById(R.id.time_info);
            icon = view.findViewById(R.id.imageView);
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
        ((MyViewHolder_fish)holder).name.setText(listData.get(position).getName().getNameEUen());
        ((MyViewHolder_fish)holder).size.setText(listData.get(position).getShadow().split(" ")[0]);
        ((MyViewHolder_fish)holder).loc.setText(listData.get(position).getAvailability().getLocation());
        ((MyViewHolder_fish)holder).price.setText(listData.get(position).getPrice()+"");
        ((MyViewHolder_fish)holder).rarity.setText(listData.get(position).getAvailability().getRarity());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
