package com.example.blogandroidapp.mvp.ui.articles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blogandroidapp.R;
import com.example.blogandroidapp.apis.APIManager;
import com.example.blogandroidapp.data.datamodel.Article;
import com.example.blogandroidapp.data.datamodel.ArticleList;
import com.example.blogandroidapp.mvp.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticlesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticlesFragment extends BaseMvpFragment<ArticlesContract.Presenter, ArticlesContract.View> implements ArticlesContract.View {

    private RecyclerView recyclerView;
    private View view;
    private ArticlesListAdapter articlesListAdapter;

    public ArticlesFragment() {
        // Required empty public constructor
    }


    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ArticlesContract.Presenter providePresenter() {
        return new ArticlesPresenter();
    }

    @Override
    public ArticlesContract.View provideView() {
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_articles, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.recyclerView);
        articlesListAdapter = new ArticlesListAdapter(getContext());
        recyclerView.setAdapter(articlesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showArticles(ArticleList articleList) {
        articlesListAdapter.setArticlesData(articleList);
    }
}
