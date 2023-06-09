package com.areeb.kabhar.ui.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.areeb.kabhar.data.models.Article
import com.areeb.kabhar.ui.common.ItemClicklistener
import com.areeb.kabhar.ui.home.ViewHolder.HomePagingViewHolder
import com.areeb.kabhar.ui.home.diffUtil.HomeDiffUtil

class HomePagingAdapter(val click: ItemClicklistener<Article>) :
    PagingDataAdapter<Article, HomePagingViewHolder>(HomeDiffUtil()) {
    override fun onBindViewHolder(holder: HomePagingViewHolder, position: Int) {
        getItem(position).let {
            it?.let { it1 -> holder.bind(it1, click) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePagingViewHolder {
        return HomePagingViewHolder.from(parent)
    }
}
