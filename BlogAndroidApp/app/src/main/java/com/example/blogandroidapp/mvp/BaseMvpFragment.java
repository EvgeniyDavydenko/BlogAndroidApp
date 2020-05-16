package com.example.blogandroidapp.mvp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseMvpFragment<T extends IPresenter<V>, V extends IView> extends Fragment implements IView {

    protected T presenter;
    protected V view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();
        view = provideView();
        presenter.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onAttach(view);
    }

    @Override
    public void onPause() {
        presenter.onDetach();
        super.onPause();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    public abstract T providePresenter();
    public abstract V provideView();

}
