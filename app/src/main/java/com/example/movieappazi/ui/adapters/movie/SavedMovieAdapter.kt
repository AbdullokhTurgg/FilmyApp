package com.example.movieappazi.ui.adapters.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.extensions.downEffect
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.startSlideInRightAnim
import com.example.movieappazi.databinding.ItemFavMoviesBinding
import com.example.movieappazi.ui.adapters.movie.diffcallbacks.MovieDiffCallBack
import com.squareup.picasso.Picasso

class SavedMoviesAdapter(
    private val listener: RecyclerFavOnClickListener,
) :  RecyclerView.Adapter<SavedMoviesAdapter.ViewHolder>() {

    var moviesList = listOf<MovieUi>()
        set(value) {
            val callback = MovieDiffCallBack(moviesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fav_movies, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(moviesList[position])


        holder.itemView.downEffect().setOnClickListener {
            listener.onItemClick(moviesList[position])
        }
    }

    interface RecyclerFavOnClickListener {
        fun onItemClick(movie: MovieUi)
        fun onClearItemClick(movie: MovieUi)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavMoviesBinding.bind(itemView)
        fun bind(movie: MovieUi) = movie.apply {
            with(binding) {
                Picasso.get().load(Utils.POSTER_PATH_URL + posterPath).into(posterImage)

                buttonBookmark.setOnDownEffectClickListener {
                    listener.onClearItemClick(moviesList[adapterPosition])
                }
                motionLayout.setOnDownEffectClickListener {
                    listener.onItemClick(moviesList[adapterPosition])
                }
                motionLayout.startSlideInRightAnim()

                movieTitle.text = title
                dateSave.text = releaseDate
                rating.text = popularity.toString()
                voteCount.text = movie.rating.toFloat().toString()
            }
        }
    }

    override fun getItemCount(): Int  = moviesList.size

}
