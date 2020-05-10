package com.example.blogandroidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.blogandroidapp.R;
import com.example.blogandroidapp.apis.APIManager;
import com.example.blogandroidapp.data.datamodel.Article;
import com.example.blogandroidapp.data.datamodel.ArticleList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadArticles();
        Log.d("OnCreate","Finished");
    }

    private void loadArticles() {
        List<Article> articleList = new ArrayList<>();
        APIManager apiManager = APIManager.getInstance();
        apiManager.getSchrangTV()
                .map(ArticleList::getArticles)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            articleList.addAll(result);
                            Log.d("Loading Complete", "Articles list size " + articleList.size());

                        },
                        error -> Log.d("Loading Articles Error", "Error Message" + error.getMessage()));
    }
}
