package com.startandroid.newsapp.ui.historystock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.startandroid.newsapp.R

class TabThreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val oneView: View = inflater.inflate(R.layout.fr_tab3, container, false)



        return oneView
    }
}