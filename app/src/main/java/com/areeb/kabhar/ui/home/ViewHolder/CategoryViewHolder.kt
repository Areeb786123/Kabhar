package com.areeb.kabhar.ui.home.ViewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.kabhar.R
import com.areeb.kabhar.data.models.categoryModel.Category
import com.areeb.kabhar.databinding.NewsCategoryItemsBinding
import com.areeb.kabhar.ui.common.ItemClicklistener
import com.areeb.kabhar.ui.home.adapter.CategoryAdapter

class CategoryViewHolder(
    private val bindingAdapter: NewsCategoryItemsBinding,
    private val adapter: CategoryAdapter,
) :
    RecyclerView.ViewHolder(bindingAdapter.root), View.OnClickListener {
    init {
        bindingAdapter.title.setOnClickListener(this)
    }

    var title: Category? = null
    var selectedItem: ItemClicklistener<Pair<Category, Int>>? = null
    var selectedPosition: Int = 0
    var defaultPosition: Int = 0

    fun bind(
        category: Category,
        selectedItem: ItemClicklistener<Pair<Category, Int>>,
        position: Int,
        defaultPosition: Int,
    ) {
        this.title = category
        this.selectedItem = selectedItem
        this.selectedPosition = position
        this.defaultPosition = defaultPosition
        bindingAdapter.title.text = category.title?.uppercase()
        isSelected(position == defaultPosition)
    }

    companion object {
        fun from(parent: ViewGroup, adapter: CategoryAdapter): CategoryViewHolder {
            return CategoryViewHolder(
                NewsCategoryItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                adapter,
            )
        }
    }

    override fun onClick(view: View?) {
        if (view?.id == bindingAdapter.title.id) {
            title?.let {
                selectedItem?.onClick(Pair(it, selectedPosition))
            }
        }
    }

    fun isSelected(value: Boolean) {
        if (value) {
            bindingAdapter.title.setBackgroundResource(R.drawable.chip_background)
        } else {
            bindingAdapter.title.setBackgroundResource(R.drawable.chip_background_unselected)
        }
    }
}
