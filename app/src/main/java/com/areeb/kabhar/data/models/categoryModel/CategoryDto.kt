package com.areeb.kabhar.data.models.categoryModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryDto(
    val categories: List<Category>,
): Parcelable
