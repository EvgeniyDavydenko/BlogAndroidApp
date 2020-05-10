package com.example.blogandroidapp.apis;

import com.example.blogandroidapp.data.datamodel.ArticleList;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class APIManager {

    private static APIManager apiManager;

    public static APIManager getInstance(){
        if (apiManager == null){
            apiManager = new APIManager();
        }
        return apiManager;
    }


    public Observable<ArticleList> getSchrangTV() {
        APIInterface apiInterface = NetworkService.getInstance().getAPIInterface();
        return apiInterface.getArticles(1, 0, 1, "desc")
                .subscribeOn(Schedulers.io());
    }


}
