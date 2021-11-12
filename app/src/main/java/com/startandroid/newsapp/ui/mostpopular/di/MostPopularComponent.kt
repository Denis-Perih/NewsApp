package com.startandroid.newsapp.ui.mostpopular.di

import com.startandroid.newsapp.data.api.network.di.NetworkServiceModule
import com.startandroid.newsapp.ui.mostpopular.view.MostPopularFragment
import com.startandroid.newsapp.utils.scope.NewsFragmentScope
import dagger.Component

@NewsFragmentScope
@Component(modules = [NetworkServiceModule::class])
interface MostPopularComponent {

    fun injectMostPopularFragment(mostPopularFragment: MostPopularFragment)
}