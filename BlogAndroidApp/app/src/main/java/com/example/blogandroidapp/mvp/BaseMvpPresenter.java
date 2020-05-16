package com.example.blogandroidapp.mvp;

import androidx.annotation.Nullable;

public abstract class BaseMvpPresenter<V extends IView> implements IPresenter<V> {

    private static final String TAG = "BaseMvpPresenter";

    @Nullable
    protected V view = null;

    @Override
    public void init() {

    }

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
