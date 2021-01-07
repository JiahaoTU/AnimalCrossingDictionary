package nooks.animalcrossingdictionary.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animalcrossingdictionary.R;

import java.util.List;

import nooks.animalcrossingdictionary.FishDetailActivity;
import nooks.animalcrossingdictionary.entities.fish.Fish;

public class AdapterFish extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder_fish_list extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView size, loc, price, rarity;
        public ImageView icon;

        public MyViewHolder_fish_list(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            size = view.findViewById(R.id.size_info);
            loc = view.findViewById(R.id.location_info);
            price = view.findViewById(R.id.price_info);
            rarity = view.findViewById(R.id.rarity_info);
            icon = view.findViewById(R.id.icon_image);
        }
    }
    public static class MyViewHolder_fish_grid extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView icon;

        public MyViewHolder_fish_grid(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            icon = view.findViewById(R.id.icon_image);
        }
    }


    private List<Fish> listData;
    private String viewType;
    public AdapterFish(List<Fish> data, String viewType) {
        this.listData = data;
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        if (viewType.equals("")) {
            return 1;
        } else if(viewType.equals("list")){
            return 1;
        } else if(viewType.equals("grid")) {
            return 2;
        } else {
            return 0;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fish_list, parent, false);
            return new AdapterFish.MyViewHolder_fish_list(v);
        } else if(viewType == 2) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fish_grid, parent, false);
            return new AdapterFish.MyViewHolder_fish_grid(v);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ((MyViewHolder_fish_list) holder).name.setText(listData.get(position).getName().getNameEUen());
            ((MyViewHolder_fish_list) holder).size.setText(listData.get(position).getShadow().split(" ")[0]);
            ((MyViewHolder_fish_list) holder).loc.setText(listData.get(position).getAvailability().getLocation());
            ((MyViewHolder_fish_list) holder).price.setText(listData.get(position).getPrice() + "");
            ((MyViewHolder_fish_list) holder).rarity.setText(listData.get(position).getAvailability().getRarity());
            Glide.with(holder.itemView.getContext())
                    .load(listData.get(position).getIcon_uri())
                    .into(((MyViewHolder_fish_list) holder).icon);
        } else if (getItemViewType(position) == 2) {
            ((MyViewHolder_fish_grid) holder).name.setText(listData.get(position).getName().getNameEUen());
            Glide.with(holder.itemView.getContext())
                    .load(listData.get(position).getIcon_uri())
                    .into(((MyViewHolder_fish_grid) holder).icon);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FishDetailActivity.class);
                Fish chosenFish = listData.get(position);
                intent.putExtra("fish", chosenFish);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
