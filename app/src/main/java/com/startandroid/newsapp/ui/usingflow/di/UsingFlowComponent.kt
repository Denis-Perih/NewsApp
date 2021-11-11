package com.startandroid.newsapp.ui.usingflow.di

import com.startandroid.newsapp.ui.usingflow.view.UsingFlowFragment
import com.startandroid.newsapp.utils.scope.ApplicationScope
import dagger.Component
import kotlinx.coroutines.InternalCoroutinesApi

@ApplicationScope
@Component(modules = [UsingFlowModule::class])
interface UsingFlowComponent {

    @InternalCoroutinesApi
    fun injectUsingFlowFragment(usingFlowFragment: UsingFlowFragment)
}