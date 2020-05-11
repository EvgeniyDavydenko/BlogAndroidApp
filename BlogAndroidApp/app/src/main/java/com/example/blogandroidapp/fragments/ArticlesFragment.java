package com.example.blogandroidapp.fragments;

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
import com.example.blogandroidapp.adapters.ArticlesListAdapter;
import com.example.blogandroidapp.apis.APIManager;
import com.example.blogandroidapp.data.datamodel.Article;
import com.example.blogandroidapp.data.datamodel.ArticleList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticlesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticlesFragment extends Fragment {

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
        loadArticles();
    }

    private void loadArticles() {
        List<Article> articleList = new ArrayList<>();
        APIManager apiManager = APIManager.getInstance();
        apiManager.getSchrangTV()
                .map(ArticleList::getArticles)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            articleList.addAll(result);
                            articlesListAdapter.setArticlesData(result);
                            Log.d("Loading Complete", "Articles list size " + articleList.size());

                        },
                        error -> Log.d("Loading Articles Error", "Error Message" + error.getMessage()));
    }
}
