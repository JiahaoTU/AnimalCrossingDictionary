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

import java.io.Serializable;
import java.util.List;

import nooks.animalcrossingdictionary.SeaCreatureDetailActivity;
import nooks.animalcrossingdictionary.entities.bugs.Bugs;
import nooks.animalcrossingdictionary.entities.seaCreatures.SeaCreatures;

public class AdapterSeaCreatures extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class MyViewHolder_SeaCreatures_list extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView loc, price, rarity;
        public ImageView icon;

        public MyViewHolder_SeaCreatures_list(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            loc = view.findViewById(R.id.speed_info);
            price = view.findViewById(R.id.price_info);
            rarity = view.findViewById(R.id.shadow_info);
            icon = view.findViewById(R.id.icon_image);
        }
    }

    public static class MyViewHolder_SeaCreatures_grid extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView icon;

        public MyViewHolder_SeaCreatures_grid(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            icon = view.findViewById(R.id.icon_image);
        }
    }


    private List<SeaCreatures> listData;
    private String viewType;

    public AdapterSeaCreatures(List<SeaCreatures> data, String type) {
        this.listData = data;
        this.viewType = type;
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seacreatures_list, parent, false);
            return new AdapterSeaCreatures.MyViewHolder_SeaCreatures_list(v);
        } else if(viewType == 2) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seacreatures_grid, parent, false);
            return new AdapterSeaCreatures.MyViewHolder_SeaCreatures_grid(v);
        } else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ((AdapterSeaCreatures.MyViewHolder_SeaCreatures_list) holder).name.setText(listData.get(position).getName().getNameEUen());
            ((AdapterSeaCreatures.MyViewHolder_SeaCreatures_list) holder).loc.setText(listData.get(position).getSpeed());
            ((AdapterSeaCreatures.MyViewHolder_SeaCreatures_list) holder).price.setText(listData.get(position).getPrice() + "");
            ((AdapterSeaCreatures.MyViewHolder_SeaCreatures_list) holder).rarity.setText(listData.get(position).getShadow());
            Glide.with(holder.itemView.getContext()).load(listData.get(position).getIcon_uri()).into(((AdapterSeaCreatures.MyViewHolder_SeaCreatures_list) holder).icon);
        } else if (getItemViewType(position) == 2) {
            ((AdapterSeaCreatures.MyViewHolder_SeaCreatures_grid) holder).name.setText(listData.get(position).getName().getNameEUen());
            Glide.with(holder.itemView.getContext()).load(listData.get(position).getIcon_uri()).into(((AdapterSeaCreatures.MyViewHolder_SeaCreatures_grid) holder).icon);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SeaCreatureDetailActivity.class);
                SeaCreatures selectedSeaCreatures = listData.get(position);
                intent.putExtra("seacreatures", (Serializable)selectedSeaCreatures);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
