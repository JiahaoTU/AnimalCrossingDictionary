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

import nooks.animalcrossingdictionary.VillagerDetailActivity;
import nooks.animalcrossingdictionary.entities.villagers.Villagers;

public class AdapterVillager extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder_villager_list extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView species, gender, birth;
        public ImageView icon;

        public MyViewHolder_villager_list(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            species = view.findViewById(R.id.species_info);
            gender = view.findViewById(R.id.gender_info);
            birth = view.findViewById(R.id.birth_info);
            icon = view.findViewById(R.id.icon_image);
        }
    }

    public class MyViewHolder_villager_grid extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView icon;

        public MyViewHolder_villager_grid(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            icon = view.findViewById(R.id.icon_image);
        }
    }

    private List<Villagers> listData;
    private String viewType;

    public AdapterVillager(List<Villagers> listData, String viewType) {
        this.listData = listData;
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.villagers_list, parent, false);
            return new AdapterVillager.MyViewHolder_villager_list(v);
        } else if(viewType == 2) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.villagers_grid, parent, false);
            return new AdapterVillager.MyViewHolder_villager_grid(v);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ((MyViewHolder_villager_list) holder).name.setText(listData.get(position).getName().getNameEUen());
            ((MyViewHolder_villager_list) holder).species.setText(listData.get(position).getSpecies());
            ((MyViewHolder_villager_list) holder).gender.setText(listData.get(position).getGender());
            ((MyViewHolder_villager_list) holder).birth.setText(listData.get(position).getBirthdayString());
            Glide.with(holder.itemView.getContext())
                    .load(listData.get(position).getIcon_uri())
                    .into(((MyViewHolder_villager_list) holder).icon);
        } else if (getItemViewType(position) == 2) {
            ((MyViewHolder_villager_grid) holder).name.setText(listData.get(position).getName().getNameEUen());
            Glide.with(holder.itemView.getContext())
                    .load(listData.get(position).getIcon_uri())
                    .into(((MyViewHolder_villager_grid) holder).icon);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VillagerDetailActivity.class);
                Villagers chosenVillager = listData.get(position);
                intent.putExtra("villager", chosenVillager);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
