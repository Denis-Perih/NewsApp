package com.startandroid.newsapp.ui.tab1

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.startandroid.newsapp.data.NewsRepository
import com.startandroid.newsapp.data.NewsRepositoryImpl
import com.startandroid.newsapp.data.entity.News
import com.startandroid.newsapp.data.entity.NewsItem
import com.startandroid.newsapp.ui.tab1.adapter.RecyclerViewAdapterTab1
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class TabOnePresenter(private val view: TabOneContract.View): TabOneContract.Presenter, AnswerForMoreDetailsTab1 {

    lateinit var rvAdapterTab1: RecyclerViewAdapterTab1

    private val repository: NewsRepository = NewsRepositoryImpl()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun startFragment(context: Context) {
        if (isNetConnected(context)) {
            // has net
            val mostPopular: Single<News> = repository.getMostPopular()
            mostPopular.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(t: News) {
                        view.buildListNews()
                        rvAdapterTab1 = RecyclerViewAdapterTab1(t.results, this@TabOnePresenter)
                        view.setAdapter(rvAdapterTab1)
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        } else {
            // hasn't net
            view.noNetConnection()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetConnected(context: Context): Boolean {
        val connectManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectManager != null) {
            val capabilities =
                connectManager.getNetworkCapabilities(connectManager.activeNetwork)
            if (capabilities != null) {
                return true
            }
        }
        return false
    }

    override fun onSuccessMoreDetails(newsItem: NewsItem) {
        view.openTab1FragmentMoreDetails(newsItem)
    }
}