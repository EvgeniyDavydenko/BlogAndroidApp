package com.example.blogandroidapp.mvp.ui.articles;

import com.example.blogandroidapp.data.datamodel.ArticleList;
import com.example.blogandroidapp.mvp.IPresenter;
import com.example.blogandroidapp.mvp.IView;

public interface ArticlesContract {
    interface Presenter extends IPresenter<View>{
        void loadArticles();
    }

    interface View extends IView{
        void showArticles(ArticleList articleList);
    }
}
