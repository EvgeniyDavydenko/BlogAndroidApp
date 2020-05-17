package com.example.blogandroidapp.mvp.ui.articlesdetail;

import com.example.blogandroidapp.data.datamodel.Article;
import com.example.blogandroidapp.mvp.IPresenter;
import com.example.blogandroidapp.mvp.IView;

public interface ArticlesDetailContract {

    interface Presenter extends IPresenter<View> {
        void setArticle(Article article);
    }

    interface View extends IView {
        void showArticleDetail(Article article);
    }

}
