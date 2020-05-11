package com.example.blogandroidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.blogandroidapp.R;
import com.example.blogandroidapp.data.datamodel.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Article> articleList = new ArrayList<>();
    private Context context;


    public ArticlesListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleViewHolder) {
            Glide.with(context)
                    .load(articleList.get(position).getPicture())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .dontAnimate()
                    .into(((ArticleViewHolder) holder).articlePicture);
            ((ArticleViewHolder) holder).articleTitle.setText(articleList.get(position).getTitle());
        }
    }

    public void setArticlesData(List<Article> articleList) {
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    private class ArticleViewHolder extends RecyclerView.ViewHolder {

        private ImageView articlePicture;
        private TextView articleTitle;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            articlePicture = itemView.findViewById(R.id.articlePicture);
            articleTitle = itemView.findViewById(R.id.articleTitle);
        }
    }

}
