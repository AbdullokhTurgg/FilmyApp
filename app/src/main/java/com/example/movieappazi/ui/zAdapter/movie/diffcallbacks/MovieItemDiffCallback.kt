package com.example.movieappazi.ui.zAdapter.movie.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappazi.uiModels.movie.MovieUi

class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieUi>() {
    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem == newItem
    }
}