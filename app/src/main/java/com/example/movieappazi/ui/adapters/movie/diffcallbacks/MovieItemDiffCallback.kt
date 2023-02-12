package com.example.movieappazi.ui.adapters.movie.diffcallbacks

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.movieappazi.uiModels.movie.MovieUi

class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieUi>() {
    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem == newItem
    }
}