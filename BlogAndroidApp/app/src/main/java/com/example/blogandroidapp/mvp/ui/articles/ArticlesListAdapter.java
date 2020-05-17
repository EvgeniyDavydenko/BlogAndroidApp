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
import com.example.blogandroidapp.data.datamodel.ArticlePages;
import com.example.blogandroidapp.data.datamodel.Header;
import com.example.blogandroidapp.interfaces.IListItemSelected;
import com.example.blogandroidapp.interfaces.IPaginationListener;
import com.example.blogandroidapp.utils.DataUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private LayoutInflater layoutInflater;
    private IPaginationListener paginationListener;
    private IListItemSelected<Article> itemClickListener;
    private ArticlePages articlePages;
    private List<Article> articleList = new ArrayList<>();
    private Header header;
    private Context context;
    private int dataPageNumber = 0;


    public ArticlesListAdapter(Context context, IPaginationListener paginationListener, IListItemSelected<Article> itemClickListener) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.paginationListener = paginationListener;
        this.itemClickListener = itemClickListener;
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
            ((ArticleViewHolder) holder).dateArticle.setText(DataUtils.ConvertTimeStampToDate(currentItem.getDateCreated()));
        }
        if (position == getItemCount() - 1
                && articlePages != null
                && dataPageNumber != articlePages.getPages().getLast()) {
            paginationListener.onLoadNext(dataPageNumber++);
        }
    }

    public void setArticlesData(ArticlePages articlesPages) {
        this.articlePages = articlesPages;
        this.articleList.addAll(articlesPages.getArticles());
        header = new Header(articleList.get(0).getCategory().getId(), articleList.get(0).getCategory().getTitle());
        notifyDataSetChanged();
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

        private ArticleViewHolder(View itemView) {
            super(itemView);
            articlePicture = itemView.findViewById(R.id.articlePicture);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            dateArticle = itemView.findViewById(R.id.dateArticle);
            itemView.setOnClickListener(v -> {
                if (articleList.get(getAdapterPosition()) != null) {
                    itemClickListener.onItemSelected(articleList.get(getAdapterPosition() - 1));
                }
            });
        }
    }

}
