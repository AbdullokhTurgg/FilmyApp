package com.example.movieappazi.ui.adapters.movie.listener


interface RvClickListener<T> {
    fun onItemClick(item: T)
    fun onLongClick(item: T)
}