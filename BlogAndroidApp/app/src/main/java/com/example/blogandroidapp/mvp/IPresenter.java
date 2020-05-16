package com.example.blogandroidapp.mvp;

public interface IPresenter<V extends IView> {
    void init();
    void onAttach(V view);
    void onDetach();
}
