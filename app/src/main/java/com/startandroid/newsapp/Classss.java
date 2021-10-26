package com.startandroid.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.startandroid.newsapp.ui.main.MainContract;
import com.startandroid.newsapp.ui.more.MoreItemFragment;

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

        View v = inflater.inflate(R.layout.fr_most_popular, container, false);

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

        FragmentManager fm = requireActivity().getSupportFragmentManager();
        fm.saveFragmentInstanceState(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    void onPars(Parcelable parcelable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("post", parcelable);

        Fragment newFragment = new MoreItemFragment();
        newFragment.setArguments(bundle);

    }
}
