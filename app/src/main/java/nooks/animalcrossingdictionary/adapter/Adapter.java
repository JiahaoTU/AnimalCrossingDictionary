package nooks.animalcrossingdictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalcrossingdictionary.R;

import java.util.List;

import nooks.animalcrossingdictionary.entities.fish.Fish;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder_fish extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView shadow;

        public MyViewHolder_fish(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            shadow = view.findViewById(R.id.shadow);
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
        ((MyViewHolder_fish)holder).shadow.setText(listData.get(position).getShadow());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
