package com.areeb.kabhar.data.models.categoryModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val title: String? = null,
) : Parcelable
