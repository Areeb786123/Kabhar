package com.areeb.kabhar.ui.home.ViewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.kabhar.data.models.categoryModel.Category
import com.areeb.kabhar.databinding.NewsCategoryItemsBinding

class CategoryViewHolder(private val bindingAdapter: NewsCategoryItemsBinding) :
    RecyclerView.ViewHolder(bindingAdapter.root) {

    fun bind(category: Category) {
        bindingAdapter.title.text = category.title?.uppercase()
    }

    companion object {
        fun from(parent: ViewGroup): CategoryViewHolder {
            return CategoryViewHolder(
                NewsCategoryItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }
}
