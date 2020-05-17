package com.example.blogandroidapp.mvp.ui.articles;

import android.util.Log;

import com.example.blogandroidapp.apis.APIManager;
import com.example.blogandroidapp.mvp.BaseMvpPresenter;
import com.example.blogandroidapp.mvp.ui.articles.ArticlesContract.*;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ArticlesPresenter extends BaseMvpPresenter<View> implements Presenter {

    private final int dataPerPage = 10;
    private final int firstDataPageNumber = 0;
    private final String dataSortOder = "desc";

    @Override
    public void onAttach(View view) {
        super.onAttach(view);
        loadArticles(firstDataPageNumber);
    }

    @Override
    public void loadArticles(int nextPageNumber) {
        APIManager apiManager = APIManager.getInstance();
        apiManager.getSchrangTV(nextPageNumber, dataPerPage, dataSortOder)
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
