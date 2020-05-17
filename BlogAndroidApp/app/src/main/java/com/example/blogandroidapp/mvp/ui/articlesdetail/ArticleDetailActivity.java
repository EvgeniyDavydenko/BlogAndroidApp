package com.example.blogandroidapp.mvp.ui.articlesdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.blogandroidapp.R;
import com.example.blogandroidapp.data.datamodel.Article;
import com.example.blogandroidapp.mvp.BaseMvpActivity;

public class ArticleDetailActivity extends BaseMvpActivity<ArticlesDetailContract.Presenter, ArticlesDetailContract.View> implements ArticlesDetailContract.View {

    private ImageView articlePicture;
    private TextView dateArticle;
    private TextView articleTitle;


    public static Intent getLauncherIntent(Context context, Article article) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra("article", article);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        initView();
        presenter.setArticle((Article) getIntent().getSerializableExtra("article"));
    }

    @Override
    public ArticlesDetailContract.Presenter providePresenter() {
        return new ArticlesDetailPresenter();
    }

    @Override
    public ArticlesDetailContract.View provideView() {
        return this;
    }

    private void initView() {
        articlePicture = findViewById(R.id.articlePicture);
        dateArticle = findViewById(R.id.dateArticle);
        articleTitle = findViewById(R.id.articleTitle);
    }


    @Override
    public void showArticleDetail(Article article) {
        articleTitle.setText(article.getTitle());
        dateArticle.setText(article.getDateCreated());
        Glide.with(this)
                .load(article.getArticlePicture())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .dontAnimate()
                .into(articlePicture);
    }

    @Override
    public void showError(String errorMessage) {

    }


}
