package com.filippoengidashet.marketproductsapp.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Filippo 19/08/2021
 */
class VerticalListMarginDecorator(
    private val size: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        outRect.top = if (position < 1) size else 0
        outRect.left = size
        outRect.right = size
        outRect.bottom = size
    }
}
