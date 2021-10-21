package com.startandroid.newsapp.tab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.startandroid.newsapp.R

class TabTwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val oneView: View = inflater.inflate(R.layout.fr_tab2, container, false)

        return oneView
    }
}