package com.areeb.kabhar.ui.home.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.areeb.kabhar.data.models.categoryModel.Category

class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.title == newItem.title
    }
}
