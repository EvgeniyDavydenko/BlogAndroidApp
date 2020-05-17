package com.example.blogandroidapp.mvp.ui.articlesdetail;

import com.example.blogandroidapp.data.datamodel.Article;
import com.example.blogandroidapp.mvp.BaseMvpPresenter;
import com.example.blogandroidapp.utils.DataUtils;

public class ArticlesDetailPresenter extends BaseMvpPresenter<ArticlesDetailContract.View> implements ArticlesDetailContract.Presenter  {

    private Article article;

    @Override
    public void onAttach(ArticlesDetailContract.View view) {
        super.onAttach(view);
    }

    @Override
    public void setArticle(Article article) {
        this.article = article;
        article.setDateCreated(DataUtils.ConvertTimeStampToDate(article.getDateCreated()));
        view.showArticleDetail(article);
    }
}
