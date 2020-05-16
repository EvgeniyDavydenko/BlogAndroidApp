package com.example.blogandroidapp.mvp.ui.articles;

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
import com.example.blogandroidapp.data.datamodel.ArticleList;
import com.example.blogandroidapp.data.datamodel.Header;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private LayoutInflater layoutInflater;
    private List<Article> articleList = new ArrayList<>();
    private Header header;
    private Context context;


    public ArticlesListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return articleList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = layoutInflater.inflate(R.layout.tap_bar_item, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.article_item, parent, false);
            return new ArticleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
//            ((HeaderViewHolder) holder).headerTabs.getTabAt(0).select();
        }
        if (holder instanceof ArticleViewHolder) {
            Article currentItem = articleList.get(position - 1);

            Glide.with(context)
                    .load(currentItem.getArticlePicture())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .dontAnimate()
                    .into(((ArticleViewHolder) holder).articlePicture);

            ((ArticleViewHolder) holder).articleTitle.setText(currentItem.getTitle());
            ((ArticleViewHolder) holder).dateArticle.setText(convertTimeStampToDate(currentItem.getDateCreated()));
        }
    }

    public void setArticlesData(ArticleList articlesData) {
        this.articleList.addAll(articlesData.getArticles());
        header = new Header(articleList.get(0).getCategory().getId(), articleList.get(0).getCategory().getTitle());
        notifyDataSetChanged();
    }

    private String convertTimeStampToDate(String dateTimeStamp){
        long timeStamp = Long.parseLong(dateTimeStamp);
        Date date = new Date(timeStamp * 1000);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(date);
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TabLayout headerTabs;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerTabs = itemView.findViewById(R.id.headerTabs);
        }
    }

    private class ArticleViewHolder extends RecyclerView.ViewHolder {

        private ImageView articlePicture;
        private TextView articleTitle;
        private TextView dateArticle;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            articlePicture = itemView.findViewById(R.id.articlePicture);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            dateArticle = itemView.findViewById(R.id.dateArticle);
        }
    }

}
