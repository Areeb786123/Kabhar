package com.areeb.kabhar.ui.home.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.areeb.kabhar.data.models.categoryModel.CategoryDto
import com.areeb.kabhar.data.network.Resources
import com.areeb.kabhar.databinding.FragmentHomeBinding
import com.areeb.kabhar.ui.common.ItemClicklistener
import com.areeb.kabhar.ui.detailScreen.DetailScreen
import com.areeb.kabhar.ui.detailScreen.activity.DetailActivity
import com.areeb.kabhar.ui.home.adapter.CategoryAdapter
import com.areeb.kabhar.ui.home.adapter.HomePagingAdapter
import com.areeb.kabhar.ui.home.viewModels.HomeViewModels
import com.areeb.kabhar.utils.Constants
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

    @SuppressLint("CommitTransaction", "ResourceType")
    private fun initView() {
        val bundle = Bundle()
        DetailScreen().arguments = bundle
        homeAdapter = HomePagingAdapter(
            ItemClicklistener {
                bundle.putString(Constants.String_CONSTANTS.WEB_VIEW_URL, it.url)
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(Constants.String_CONSTANTS.WEB_VIEW_URL, it.url)
                startActivity(intent)
            },
        )
        binding.homeViewPager.adapter = homeAdapter
    }

    private fun observer() {
        viewModels.resources.observe(viewLifecycleOwner) {
//            settingUpResources(it)
            Log.e("hh", it.toString())
        }
        viewModels.topHeadlineResponse.observe(viewLifecycleOwner) {
            Log.e("topHeadlinesInFragment", it.toString())
            homeAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun settingUpResources(resource: Resources<Any>?) {
        when (resource) {
            is Resources.LOADING -> {
                binding.homeViewPager.visibility = View.GONE
                binding.shimmerEffect.visibility = View.VISIBLE
            }
            is Resources.SUCCESS,
            -> {
                binding.homeViewPager.visibility = View.VISIBLE
                binding.shimmerEffect.visibility = View.GONE
                viewModels.clearResources()
            }

            else -> {
                Log.e("tag", "some error occur")
            }
        }
    }

    private fun extractCategoryJsonData() {
        val jsonString = context?.assets?.open("category.json")?.bufferedReader().use {
            it?.readText()
        }

        val collectionWrapper = Gson().fromJson(jsonString, CategoryDto::class.java)
        val category = collectionWrapper.categories
        categoryAdapter = CategoryAdapter(
            category,
            viewModels.getChipValue(),
            ItemClicklistener { pair ->
                viewModels.setChipSelectedValue(pair.second)
                homeAdapter?.notifyDataSetChanged()
                viewModels.getTopHeadlines(pair.first.title.toString().lowercase())
                Toast.makeText(context, "${pair.first.title}", Toast.LENGTH_SHORT).show()
                Log.e("hh", pair.second.toString())
                Log.e("hh", viewModels.getChipValue().toString())
            },

        )
        categoryAdapter!!.notifyDataSetChanged()
        binding.newsCategoryRecyclerView.adapter = categoryAdapter
    }
}
