package nooks.animalcrossingdictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalcrossingdictionary.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder_fish extends RecyclerView.ViewHolder {

        public TextView shadow;

        public MyViewHolder_fish(View view) {
            super(view);
            //shadow = view.findViewById(R.id.item);
        }
    }


    private List<String> listData;
    public Adapter(List<String> data) {
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
        ((MyViewHolder_fish)holder).shadow.setText(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
