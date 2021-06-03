package com.example.marvel.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * constructor
 * @param margin desirable margin size in px between the views in the recyclerView
 */
class MarginItemDecoration(private var margin: Int = 0) : RecyclerView.ItemDecoration() {

    /**
     * Set different margins for the items inside the recyclerView: no top margin for the first row
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildLayoutPosition(view)
        if (position == RecyclerView.NO_POSITION) {
            return
        }

        // we only add top margin to the first row
        if (position == 0) {
            outRect.top = margin
        }
        // set bottom margin to all
        outRect.bottom = margin
    }
}
