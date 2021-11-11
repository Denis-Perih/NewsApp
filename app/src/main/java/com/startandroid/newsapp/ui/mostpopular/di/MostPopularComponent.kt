package com.startandroid.newsapp.ui.mostpopular.di

import com.startandroid.newsapp.ui.mostpopular.view.MostPopularFragment
import com.startandroid.newsapp.utils.scope.NewsFragmentScope
import dagger.Component
import javax.inject.Singleton

@NewsFragmentScope
@Component(modules = [MostPopularModule::class])
interface MostPopularComponent {

    fun injectMostPopularFragment(mostPopularFragment: MostPopularFragment)
}