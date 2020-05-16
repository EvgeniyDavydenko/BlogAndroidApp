package com.example.blogandroidapp.apis;

import com.example.blogandroidapp.data.datamodel.ArticlePages;

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


    public Observable<ArticlePages> getSchrangTV(int pageNumber, int dataPerPage, String dataSortOrder) {
        APIInterface apiInterface = NetworkService.getInstance().getAPIInterface();
        return apiInterface.getArticles(1, pageNumber, dataPerPage, dataSortOrder)
                .subscribeOn(Schedulers.io());
    }


}
