package com.example.blogandroidapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.blogandroidapp.R;
import com.example.blogandroidapp.fragments.ArticlesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.d("OnCreate","Finished");
    }

    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ArticlesFragment articlesFragment = ArticlesFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, articlesFragment);
        fragmentTransaction.commit();
    }
}
