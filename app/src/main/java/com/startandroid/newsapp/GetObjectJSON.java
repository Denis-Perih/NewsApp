package com.startandroid.newsapp;

import androidx.annotation.NonNull;

import com.startandroid.newsapp.data.entity.News;
import com.startandroid.newsapp.ui.network.NetworkServiceTabOneTwo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetObjectJSON {

    public void objectJSON(){

        String apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF";
        NetworkServiceTabOneTwo.INSTANCE
                .getJSONApi()
                .getNews(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<News>() {

                    @Override
                    public void onSuccess(@NonNull News news) {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
