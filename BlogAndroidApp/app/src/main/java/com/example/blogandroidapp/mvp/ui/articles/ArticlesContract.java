package com.example.blogandroidapp.mvp.ui.articles;

import com.example.blogandroidapp.data.datamodel.ArticlePages;
import com.example.blogandroidapp.mvp.IPresenter;
import com.example.blogandroidapp.mvp.IView;

public interface ArticlesContract {
    interface Presenter extends IPresenter<View>{
        void loadArticles(int nextPageNumber);
    }

    interface View extends IView{
        void showArticles(ArticlePages articlePages);
    }
}
