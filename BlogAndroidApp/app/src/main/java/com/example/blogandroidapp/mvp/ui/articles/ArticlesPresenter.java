package com.example.blogandroidapp.mvp.ui.articles;

import android.util.Log;

import com.example.blogandroidapp.apis.APIManager;
import com.example.blogandroidapp.data.datamodel.ArticleList;
import com.example.blogandroidapp.mvp.BaseMvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ArticlesPresenter extends BaseMvpPresenter<ArticlesContract.View> implements ArticlesContract.Presenter {


    @Override
    public void onAttach(ArticlesContract.View view) {
        super.onAttach(view);
        loadArticles();
    }

    @Override
    public void loadArticles() {
        APIManager apiManager = APIManager.getInstance();
        apiManager.getSchrangTV()
//                .map(ArticleList::getArticles)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            view.showArticles(result);
//                            articlesListAdapter.setArticlesData(result);
                            Log.d("Loading Complete", "Articles list size " + result.getArticles().size());

                        },
                        error -> Log.d("Loading Articles Error", "Error Message" + error.getMessage()));
    }
}
