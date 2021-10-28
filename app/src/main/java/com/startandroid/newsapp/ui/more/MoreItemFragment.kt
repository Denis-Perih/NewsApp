package com.startandroid.newsapp.ui.more

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.mostpopular.view.MostPopularFragment
import com.startandroid.newsapp.utils.IOnBackPressed

class MoreItemFragment : Fragment() {

    private lateinit var btnBackHome: Button
    private lateinit var tvTitleMore: TextView
    private lateinit var tvDateMore: TextView
    private lateinit var tvAbstract: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moreView: View = inflater.inflate(R.layout.fr_more_screen, container, false)

        btnBackHome = moreView.findViewById(R.id.btnBackHome)
        btnBackHome.setOnClickListener {
            val fm = requireActivity().supportFragmentManager
            fm.popBackStack(MoreItemFragment::class.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//            (requireActivity() as MainContract).openHomeFragment()
        }

        tvTitleMore = moreView.findViewById(R.id.tvTitleMore)
        tvDateMore = moreView.findViewById(R.id.tvDateMore)
        tvAbstract = moreView.findViewById(R.id.tvAbstract)

        setDataToPost()

        return moreView
    }

    private fun setDataToPost() {
        val bundle: Bundle? = arguments
        if (bundle != null) {
            val popularNewsItem: PopularNewsItem? = bundle.getParcelable("popularNewsItem")
            val storiesItem: StoriesNewsItem? = bundle.getParcelable("storiesNewsItem")
            if (popularNewsItem == null) {
                tvTitleMore.text = storiesItem?.title
                tvDateMore.text = storiesItem?.published_date?.substring(0, 10)
                tvAbstract.text = storiesItem?.abstract
            } else {
                tvTitleMore.text = popularNewsItem.title
                tvDateMore.text = popularNewsItem.published_date?.substring(0, 10)
                tvAbstract.text = popularNewsItem.abstract
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
}