package com.areeb.kabhar.ui.home.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.areeb.kabhar.data.models.Article

class HomeDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }
}
