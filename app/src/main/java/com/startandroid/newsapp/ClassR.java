package com.startandroid.newsapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ClassR extends AppCompatActivity {

    SwipeRefreshLayout srlNoNetConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        srlNoNetConnection = findViewById(R.id.srlNoNetConnection);
        srlNoNetConnection.setOnRefreshListener(() -> {

            srlNoNetConnection.setRefreshing(false);
        });

        srlNoNetConnection.setVisibility(View.VISIBLE);



    }

    void isNetFun(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.mainFragmentContainer, fragment)
                .addToBackStack(fragment.getClass().getSimpleName());

        transaction.commit();
    }
}
