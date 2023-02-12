package com.example.movieappazi.ui.adapters.movie.listener_for_adapters


interface RvClickListener<T> {
    fun onItemClick(item: T)
    fun onLongClick(item: T)
}