package com.startandroid.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.startandroid.newsapp.home.HomeContract;
import com.startandroid.newsapp.home.HomePresenter;
import com.startandroid.newsapp.main.MainContract;

public class Classss extends Fragment {

    public static final String PREFERENCES_KEY = "name";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainContract) requireActivity()).openHomeFragment();

        Intent data = new Intent();
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
