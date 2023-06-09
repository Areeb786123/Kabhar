package com.areeb.kabhar.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.kabhar.data.models.categoryModel.Category
import com.areeb.kabhar.ui.common.ItemClicklistener
import com.areeb.kabhar.ui.home.ViewHolder.CategoryViewHolder

class CategoryAdapter(
    private val categoryList: List<Category>,
    private val selectedValue: Int,
    private val selectedItemListener: ItemClicklistener<Pair<Category, Int>>,
) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent, this)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(
            category = categoryList[position],
            selectedItemListener,
            position,
            selectedValue
        )
//        toogle(position, holder)
    }

    private fun toogle(position: Int, holder: CategoryViewHolder) {
        if (position == selectedValue) holder.isSelected(true) else holder.isSelected(false)
    }
}
