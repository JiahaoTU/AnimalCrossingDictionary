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

import nooks.animalcrossingdictionary.BugsDetailActivity;
import nooks.animalcrossingdictionary.entities.bugs.Bugs;

public class AdapterBugs extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder_bugs_list extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView size, loc, price, rarity;
        public ImageView icon;

        public MyViewHolder_bugs_list(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            size = view.findViewById(R.id.size_info);
            loc = view.findViewById(R.id.location_info);
            price = view.findViewById(R.id.price_info);
            rarity = view.findViewById(R.id.rarity_info);
            icon = view.findViewById(R.id.icon_image);
        }
    }

    public static class MyViewHolder_bugs_grid extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView icon;

        public MyViewHolder_bugs_grid(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            icon = view.findViewById(R.id.icon_image);
        }
    }


    private List<Bugs> listData;
    private int viewType; // view type 1 = list view; 2 = grid view

    public AdapterBugs(List<Bugs> data, int type) {
        this.listData = data;
        this.viewType = type;
    }

    @Override
    public int getItemViewType(int position) {
        if (viewType == 1){
            return 1;
        } else if(viewType == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == 1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bugs_list, parent, false);
            return new MyViewHolder_bugs_list(v);
        } else if(viewType == 2) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bugs_grid, parent, false);
            return new MyViewHolder_bugs_grid(v);
        } else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ((MyViewHolder_bugs_list) holder).name.setText(listData.get(position).getName().getNameEUen());
            ((MyViewHolder_bugs_list) holder).loc.setText(listData.get(position).getAvailability().getLocation());
            ((MyViewHolder_bugs_list) holder).price.setText(listData.get(position).getPrice() + "");
            ((MyViewHolder_bugs_list) holder).rarity.setText(listData.get(position).getAvailability().getRarity());
            Glide.with(holder.itemView.getContext()).load(listData.get(position).getIcon_uri()).into(((MyViewHolder_bugs_list) holder).icon);
        } else if (getItemViewType(position) == 2) {
            ((MyViewHolder_bugs_grid) holder).name.setText(listData.get(position).getName().getNameEUen());
            Glide.with(holder.itemView.getContext()).load(listData.get(position).getIcon_uri()).into(((MyViewHolder_bugs_grid) holder).icon);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BugsDetailActivity.class);
                Bugs selectedBugs = listData.get(position);
                intent.putExtra("bugs", (Serializable)selectedBugs);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
