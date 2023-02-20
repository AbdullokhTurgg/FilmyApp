package com.example.movieappazi.ui.adapters.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.extensions.downEffect
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.startSlideInRightAnim
import com.example.movieappazi.databinding.ItemFavMoviesBinding
import com.squareup.picasso.Picasso

class SavedMoviesAdapter(
    private val listener: RecyclerFavOnClickListener,
) : ListAdapter<MovieUi, SavedMoviesAdapter.ViewHolder>(TvDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fav_movies, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))


        holder.itemView.downEffect().setOnClickListener {
            listener.onItemClick(getItem(position))
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
                    listener.onClearItemClick(getItem(adapterPosition))
                }
                motionLayout.setOnDownEffectClickListener {
                    listener.onItemClick(getItem(adapterPosition))
                }
                motionLayout.startSlideInRightAnim()

                movieTitle.text = title
                dateSave.text = releaseDate
                rating.text = popularity.toString()
                voteCount.text = movie.rating.toFloat().toString()
            }
        }
    }

}

class TvDiffCallBack : DiffUtil.ItemCallback<MovieUi>() {

    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi) = oldItem == newItem

}