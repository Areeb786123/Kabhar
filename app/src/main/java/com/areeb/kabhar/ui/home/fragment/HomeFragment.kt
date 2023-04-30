package com.areeb.kabhar.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.areeb.kabhar.data.models.categoryModel.CategoryDto
import com.areeb.kabhar.databinding.FragmentHomeBinding
import com.areeb.kabhar.ui.home.CategoryAdapter
import com.google.gson.Gson

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var categoryAdapter: CategoryAdapter? = null
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
