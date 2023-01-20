package com.example.movieappazi.ui.zAdapter.movie.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.retrofit.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import com.example.movieappazi.uiModels.person.PersonUi
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class ObjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.posterImage)
    private val progressView = view.findViewById<CircularProgressView>(R.id.progressView)
    val itemMovie = view.findViewById<CardView>(R.id.motion_layout)

    fun bindMovie(movie: MovieUi) {
        bind(movie.posterPath, movie.rating)
    }

    fun bindPerson(person: PersonUi) {
        bind(person.profile_path, person.popularity)
    }

    fun bindPersonDetails(person: PersonDetailsUi) {
        bind(person.profile_path, person.popularity)
    }

    private fun bind(posterPath: String?, popularity: Double) {
        Picasso.get().load(Utils.POSTER_PATH_URL + posterPath).into(image)
        val voteAverage = (popularity * 10.0)
        if (voteAverage.toInt() >= 70) {
            progressView.setProgressColorRes(R.color.green)
        } else if (voteAverage.toInt() in 51..69) {
            progressView.setProgressColorRes(android.R.color.holo_orange_light)
        } else {
            progressView.setProgressColorRes(android.R.color.holo_red_dark)
        }
        progressView.setProgress(voteAverage.toInt(), true)
    }
}