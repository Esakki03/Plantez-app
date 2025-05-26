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

public class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder> {

    private List<GrowItem> SpeciesList;
    private Context context;

    public SpeciesAdapter(Context context, List<GrowItem> SpeciesList) {
        this.context = context;
        this.SpeciesList = SpeciesList;
    }
    public void filterBySearch(List<GrowItem> SpeciesList){
        this.SpeciesList = SpeciesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_species_item, parent, false);
        return new SpeciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesAdapter.SpeciesViewHolder holder, int position) {
        GrowItem species = SpeciesList.get(position);


        holder.nameTextView.setText(species.getName());
        holder.descriptionTextView.setText(species.getContent());

        String imageUrl = Base_Url + species.getImage();

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpeciesAboutActivity.class);
                intent.putExtra("image",species.getImage());
                intent.putExtra("title",species.getName());
                intent.putExtra("subje ct",species.getSubject());
                intent.putExtra("content",species.getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return SpeciesList.size();
    }

    public static class SpeciesViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        ImageView imageView;

        public SpeciesViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.txtContent);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
