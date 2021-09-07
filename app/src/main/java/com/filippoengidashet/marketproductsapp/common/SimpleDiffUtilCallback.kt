package com.filippoengidashet.marketproductsapp.common

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Filippo 19/08/2021
 */
abstract class SimpleDiffUtilCallback<T>(
    private val oldList: List<T>, private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem != null && newItem != null) {
            isSameItem(oldItem, newItem)
        } else {
            oldItem == null && newItem == null
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem != null && newItem != null) {
            isSameContent(oldItem, newItem)
        } else {
            oldItem == null && newItem == null
        }
    }

    abstract fun isSameItem(oldItem: T, newItem: T): Boolean

    abstract fun isSameContent(oldItem: T, newItem: T): Boolean
}
