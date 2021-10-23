package com.startandroid.newsapp.ui.tab2

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.startandroid.newsapp.data.NewsRepository
import com.startandroid.newsapp.data.NewsRepositoryImpl
import com.startandroid.newsapp.data.entity.Stories
import com.startandroid.newsapp.data.entity.StoriesItem
import com.startandroid.newsapp.ui.tab2.adapter.RecyclerViewAdapterTab2
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TabTwoPresenter(private val view: TabTwoContract.View): TabTwoContract.Presenter, AnswerForMoreDetailsTab2 {

    lateinit var rvAdapterTab2: RecyclerViewAdapterTab2

    private val repository: NewsRepository = NewsRepositoryImpl()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun startFragment(context: Context) {
        if (isNetConnected(context)) {
            // has net
            val mostPopular: Single<Stories> = repository.getTopStories()
            mostPopular.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<Stories>() {
                    override fun onSuccess(t: Stories) {
                        view.buildListNews()
                        rvAdapterTab2 = RecyclerViewAdapterTab2(t.results, this@TabTwoPresenter)
                        view.setAdapter(rvAdapterTab2)
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

    override fun onSuccessMoreDetails(storiesItem: StoriesItem) {
        view.openTab2FragmentMoreDetails(storiesItem)
    }
}