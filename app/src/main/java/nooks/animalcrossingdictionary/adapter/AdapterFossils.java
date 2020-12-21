package nooks.animalcrossingdictionary.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalcrossingdictionary.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import nooks.animalcrossingdictionary.FossilsDetailActivity;
import nooks.animalcrossingdictionary.entities.fossils.Fossils;

public class AdapterFossils extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder_fossils extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView price;
        public ImageView image;

        public MyViewHolder_fossils(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            //price = view.findViewById(R.id.price);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fossils, parent, false);
        return new AdapterFossils.MyViewHolder_fossils(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder_fossils)holder).name.setText(listData.get(position).getName().getNameEUen());
        ((MyViewHolder_fossils)holder).price.setText(String.valueOf(listData.get(position).getPrice()));;
        Picasso.get().load(listData.get(position).getImage_uri()).into(((MyViewHolder_fossils)holder).image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FossilsDetailActivity.class);
                Fossils choosedFossils = listData.get(position);
                intent.putExtra("fossils", (Serializable)choosedFossils);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
