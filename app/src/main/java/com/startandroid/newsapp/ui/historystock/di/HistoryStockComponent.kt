package com.startandroid.newsapp.ui.historystock.di

import com.startandroid.newsapp.data.api.network.di.NetworkServiceModule
import com.startandroid.newsapp.ui.historystock.view.HistoryStockFragment
import com.startandroid.newsapp.utils.scope.HistoryStockFragmentScope
import dagger.Component

@HistoryStockFragmentScope
@Component(modules = [NetworkServiceModule::class])
interface HistoryStockComponent {

    fun injectHistoryStockFragment(historyStockFragment: HistoryStockFragment)
}