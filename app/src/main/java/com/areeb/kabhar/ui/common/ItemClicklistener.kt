package com.areeb.kabhar.ui.common

class ItemClicklistener<T>(val clickListener: (t: T) -> Unit) {
    fun onClick(t: T) = clickListener(t)
}
