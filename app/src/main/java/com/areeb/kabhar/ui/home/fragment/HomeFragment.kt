package com.areeb.kabhar.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.areeb.kabhar.data.models.categoryModel.CategoryDto
import com.areeb.kabhar.databinding.FragmentHomeBinding
import com.areeb.kabhar.ui.home.adapter.CategoryAdapter
import com.areeb.kabhar.ui.home.adapter.HomePagingAdapter
import com.areeb.kabhar.ui.home.viewModels.HomeViewModels
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var categoryAdapter: CategoryAdapter? = null
    private var homeAdapter: HomePagingAdapter? = null
    private val viewModels: HomeViewModels by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractCategoryJsonData()
        observer()
        initView()
    }

    private fun initView() {
        homeAdapter = HomePagingAdapter()
        binding.homeViewPager.adapter = homeAdapter
    }

    private fun observer() {
        viewModels.topHeadlineResponse.observe(viewLifecycleOwner) {
            Log.e("topHeadlinesInFragment", it.toString())
            homeAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun extractCategoryJsonData() {
        val jsonString = context?.assets?.open("category.json")?.bufferedReader().use {
            it?.readText()
        }
        val collectionWrapper = Gson().fromJson(jsonString, CategoryDto::class.java)
        val category = collectionWrapper.categories
        categoryAdapter = CategoryAdapter(category)
        binding.newsCategoryRecyclerView.adapter = categoryAdapter
    }
}
