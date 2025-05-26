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

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> ArticleList;
    private Context context;

    public ArticleAdapter(Context context, List<Article> ArticleList) {
        this.context = context;
        this.ArticleList = ArticleList;
    }
    public void filterBySearch(List<Article> ArticleList){
        this.ArticleList = ArticleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        Article article = ArticleList.get(position);


        holder.nameTextView.setText(article.getName());
        holder.descriptionTextView.setText(article.getContent());

        String imageUrl = Base_Url + article.getImage();

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticleAboutActivity.class);
                intent.putExtra("image",article.getImage());
                intent.putExtra("title",article.getName());
                intent.putExtra("content",article.getContent());
                intent.putExtra("subject",article.getSubject());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ArticleList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        ImageView imageView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.txtContent);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
