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

import nooks.animalcrossingdictionary.SongsDetailActivity;
import nooks.animalcrossingdictionary.entities.songs.Songs;

public class AdapterSongs extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder_songs_grid extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView icon;

        public MyViewHolder_songs_grid(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            icon = view.findViewById(R.id.icon_image);
        }
    }

    private List<Songs> listData;

    public AdapterSongs(List<Songs> data) {
        this.listData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_grid, parent, false);
        return new AdapterSongs.MyViewHolder_songs_grid(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AdapterSongs.MyViewHolder_songs_grid) holder).name.setText(listData.get(position).getName().getNameEUen());
        Glide.with(holder.itemView.getContext()).load(listData.get(position).getImage_uri()).into(((AdapterSongs.MyViewHolder_songs_grid) holder).icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongsDetailActivity.class);
                Songs selectedSongs = listData.get(position);
                intent.putExtra("songs", (Serializable)selectedSongs);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }





}
