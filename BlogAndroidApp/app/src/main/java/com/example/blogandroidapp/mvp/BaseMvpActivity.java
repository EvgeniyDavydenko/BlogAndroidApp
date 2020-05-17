package com.example.blogandroidapp.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMvpActivity<T extends IPresenter<V>, V extends IView> extends BaseActivity implements IView {

    protected T presenter;
    protected V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();
        view = provideView();
        presenter.onAttach(view);
    }

    @Override
    protected void onResume() {
        presenter.onAttach(view);
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onDetach();
        super.onPause();
    }

    @Override
    public void showError(String errorMessage) {

    }

    public abstract T providePresenter();
    public abstract V provideView();

}
