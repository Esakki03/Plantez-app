package com.simats.plantz;

import static com.simats.plantz.api.RetroClient.Base_Url;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private List<PlantList> plantList;
    private Context context;

    public PlantAdapter(Context context, List<PlantList> plantList) {
        this.context = context;
        this.plantList = plantList;
    }

    public void filterBySearch(List<PlantList> plantList){
        this.plantList = plantList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_items, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        PlantList plant = plantList.get(position);


        holder.nameTextView.setText(plant.getName());
        holder.descriptionTextView.setText(plant.getDescription());

        String imageUrl = Base_Url + plant.getImage();

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlantDetailsActivity.class);
                intent.putExtra("image",plant.getImage());
                intent.putExtra("title",plant.getName());
                intent.putExtra("content",plant.getDescription());
                intent.putExtra("benifits",plant.getMedicinalUse());
                intent.putExtra("geography",plant.getGeographicDistribution());
                intent.putExtra("seasonal",plant.getSeason());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        ImageView imageView;

        public PlantViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.txtContent);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
