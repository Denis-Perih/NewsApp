package com.startandroid.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.startandroid.newsapp.data.NewsRepository;
import com.startandroid.newsapp.main.MainContract;

public class Classss extends Fragment {

    public static final String PREFERENCES_KEY = "name";

    SwipeRefreshLayout swipe_container;

    RecyclerView rvHorizontalBlockNews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainContract) requireActivity()).openHomeFragment();

        View v = inflater.inflate(R.layout.fr_tab1, container, false);

        Intent data = new Intent();
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
        } catch (ApiException e) {
            e.printStackTrace();
        }


        rvHorizontalBlockNews = v.findViewById(R.id.rvListMostPopular);
        LinearLayoutManager llmHorizontalBlockNews = new LinearLayoutManager(
                rvHorizontalBlockNews.getContext(), RecyclerView.HORIZONTAL, false);
        rvHorizontalBlockNews.setLayoutManager(llmHorizontalBlockNews);
        swipe_container.setRefreshing(false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
