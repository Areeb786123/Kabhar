package com.areeb.kabhar.ui.home.ViewHolder

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.areeb.kabhar.R
import com.areeb.kabhar.data.models.Article
import com.areeb.kabhar.databinding.ItemViewPagerBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class HomePagingViewHolder(private val bindingAdapter: ItemViewPagerBinding) :
    RecyclerView.ViewHolder(bindingAdapter.root) {

    @SuppressLint("SetTextI18n")
    fun bind(article: Article) {
        bindingAdapter.title.text = article.title
        bindingAdapter.newsTextView.text = article.content
        bindingAdapter.publishAtTextView.text = "published at ${article.publishedAt}"
        bindingAdapter.authorNameTextView.text = article.author
        settingUpImage(article)
//        changingItemBackgroundColor(article)
    }

    @SuppressLint("ResourceAsColor")
    private fun changingItemBackgroundColor(article: Article) {
        if (article.title.length % 2 == 0) {
            val color = ContextCompat.getColor(bindingAdapter.root.context, R.color.card1)
            bindingAdapter.mainCardBackground.setCardBackgroundColor(color)
        } else {
            val color = ContextCompat.getColor(bindingAdapter.root.context, R.color.card2)
            bindingAdapter.mainCardBackground.setCardBackgroundColor(color)
        }
    }

    private fun settingUpImage(article: Article) {
        Glide.with(bindingAdapter.root.context).load(article.urlToImage)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    bindingAdapter.newsImageView.visibility = View.INVISIBLE
                    bindingAdapter.progressBar.visibility = View.VISIBLE
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    bindingAdapter.progressBar.visibility = View.GONE
                    bindingAdapter.newsImageView.visibility = View.VISIBLE
                    return false
                }
            }).into(bindingAdapter.newsImageView)
    }

    companion object {
        fun from(view: ViewGroup): HomePagingViewHolder {
            return HomePagingViewHolder(
                ItemViewPagerBinding.inflate(LayoutInflater.from(view.context), view, false),
            )
        }
    }
}
