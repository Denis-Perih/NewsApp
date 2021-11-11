package com.startandroid.newsapp.ui.historystock.di

import com.startandroid.newsapp.data.api.network.ApiRequest
import com.startandroid.newsapp.data.repository.repositoryhistorystock.HistoryStockRepository
import com.startandroid.newsapp.ui.historystock.view.HistoryStockFragment
import com.startandroid.newsapp.utils.scope.HistoryStockFragmentScope
import dagger.Component
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

@HistoryStockFragmentScope
@Component(modules = [HistoryStockModule::class])
interface HistoryStockComponent {

    fun injectHistoryStockFragment(historyStockFragment: HistoryStockFragment)
}