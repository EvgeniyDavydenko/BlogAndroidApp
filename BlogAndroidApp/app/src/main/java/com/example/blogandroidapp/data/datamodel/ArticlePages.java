package com.example.blogandroidapp.data.datamodel;

import java.io.Serializable;
import java.util.List;

public class ArticlePages implements Serializable {
    private List<Article> articles;
    private Pages pages;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }
}
