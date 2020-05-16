package com.example.blogandroidapp.apis;

import com.example.blogandroidapp.data.datamodel.ArticlePages;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("list/")
    Observable<ArticlePages> getArticles(@Query("category") int categoryID, @Query("page") int pageNumber, @Query("per_page") int pageSize, @Query("order") String order);
}
