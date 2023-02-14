package com.example.movieappazi.ui.adapters.movie.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappazi.app.models.movie.MovieUi


class MovieDiffCallBack(
    private val oldList: List<MovieUi>,
    private val newList: List<MovieUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}