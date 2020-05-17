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
    private int currentArticlesDataCategoryID = 1;

    @Override
    public void onAttach(View view) {
        super.onAttach(view);
        loadFirstArticlesDataPage(currentArticlesDataCategoryID);
//        loadArticles(firstDataPageNumber);
    }

    @Override
    public void loadFirstArticlesDataPage(int articlesDataCategory) {
        currentArticlesDataCategoryID = articlesDataCategory;
        APIManager apiManager = APIManager.getInstance();
        apiManager.loadArticlesData(currentArticlesDataCategoryID, firstDataPageNumber, dataPerPage, dataSortOder)
//                .map(ArticleList::getArticles)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            view.showFirstArticlesDataPage(result);
//                            articlesListAdapter.setArticlesData(result);
                            Log.d("Loading Complete", "Articles list size " + result.getArticles().size());

                        },
                        error -> Log.d("Loading Articles Error", "Error Message" + error.getMessage()));
    }

    @Override
    public void loadNextArticlesDataPage(int nextPageNumber) {
        APIManager apiManager = APIManager.getInstance();
        apiManager.loadArticlesData(currentArticlesDataCategoryID, nextPageNumber, dataPerPage, dataSortOder)
//                .map(ArticleList::getArticles)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            view.showNextArticlesDataPAge(result);
//                            articlesListAdapter.setArticlesData(result);
                            Log.d("Loading Complete", "Articles list size " + result.getArticles().size());

                        },
                        error -> Log.d("Loading Articles Error", "Error Message" + error.getMessage()));
    }
}
