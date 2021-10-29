package com.startandroid.newsapp.ui.more

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.databinding.FrMoreScreenBinding

class MoreItemFragment : Fragment(R.layout.fr_more_screen) {

    private var bind: FrMoreScreenBinding? = null
    private val binding get() = bind!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrMoreScreenBinding.bind(view)

        binding.btnBackHome.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack(MoreItemFragment::class.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE) }

        setDataToPost()
    }

    private fun setDataToPost() {
        val bundle: Bundle? = arguments
        if (bundle != null) {
            val popularNewsItem: PopularNewsItem? = bundle.getParcelable("popularNewsItem")
            val storiesItem: StoriesNewsItem? = bundle.getParcelable("storiesNewsItem")
            if (popularNewsItem == null) {
                binding.tvTitleMore.text = storiesItem?.title
                binding.tvDateMore.text = storiesItem?.published_date
                binding.tvAbstract.text = storiesItem?.abstract
            } else {
                binding.tvTitleMore.text = popularNewsItem.title
                binding.tvDateMore.text = popularNewsItem.published_date
                binding.tvAbstract.text = popularNewsItem.abstract
            }
        }
    }

//    override fun onStop() {
//        super.onStop()
//        (requireActivity() as MainContract).openHomeFragment()
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d("Back_Stack", "onAttach: MoreItemFragment")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("Back_Stack", "onStart: MoreItemFragment")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("Back_Stack", "onPause: MoreItemFragment")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        Log.d("Back_Stack", "onDestroyView: MoreItemFragment")
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        Log.d("Back_Stack", "onDetach: MoreItemFragment")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("Back_Stack", "onDestroy: MoreItemFragment")
//    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}