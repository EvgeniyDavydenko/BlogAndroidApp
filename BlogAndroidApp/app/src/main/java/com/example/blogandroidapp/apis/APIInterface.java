package com.example.blogandroidapp.apis;

import com.example.blogandroidapp.data.datamodel.ArticleList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("list/")
    Observable<ArticleList> getArticles(@Query("category") int categoryID, @Query("page") int pageNumber, @Query("per_page") int pageSize, @Query("order") String order);
}
