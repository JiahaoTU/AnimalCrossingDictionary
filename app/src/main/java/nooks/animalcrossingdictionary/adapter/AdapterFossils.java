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

import nooks.animalcrossingdictionary.FossilsDetailActivity;
import nooks.animalcrossingdictionary.SongsDetailActivity;
import nooks.animalcrossingdictionary.VillagersDetailActivity;
import nooks.animalcrossingdictionary.entities.fossils.Fossils;
import nooks.animalcrossingdictionary.entities.songs.Songs;
import nooks.animalcrossingdictionary.entities.villagers.Villagers;

public class AdapterFossils extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static class MyViewHolder_fossils_grid extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;

        public MyViewHolder_fossils_grid(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            image = view.findViewById(R.id.image);
        }
    }

    private List<Fossils> listData;

    public AdapterFossils(List<Fossils> data) {
        this.listData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fossils_grid, parent, false);
        return new AdapterFossils.MyViewHolder_fossils_grid(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AdapterFossils.MyViewHolder_fossils_grid) holder).name.setText(listData.get(position).getName().getNameEUen());
        Glide.with(holder.itemView.getContext()).load(listData.get(position).getImage_uri()).into(((AdapterFossils.MyViewHolder_fossils_grid) holder).image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FossilsDetailActivity.class);
                Fossils selectedFossils = listData.get(position);
                intent.putExtra("fossils", (Serializable)selectedFossils);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
