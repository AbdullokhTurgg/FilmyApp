package com.example.movieappazi.ui.movie.all_movies_screen.adapter.base

import com.example.data.data.models.IdResourceString
import com.example.movieappazi.app.recyclerview.Item

class HeaderItem(
    val onClickListener: () -> Unit,
    val titleId: IdResourceString,
    val showMoreIsVisible: Boolean = true,
) : Item