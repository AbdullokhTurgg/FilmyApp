package com.example.movieappazi.ui.adapters.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.utils.extensions.downEffect
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.startSlideInLeftAnim
import com.example.movieappazi.app.utils.extensions.startSlideInRightAnim
import com.example.movieappazi.databinding.ItemFavMoviesBinding
import com.squareup.picasso.Picasso

class TvStorageAdapter(
    private val listener: RecyclerFavOnClickListener
) : ListAdapter<SeriesUi, TvStorageAdapter.ViewHolder>(TvDiffCallBack2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_fav_movies, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener.onTvClearItemClick(getItem(position))
        }
        holder.itemView.downEffect().setOnClickListener {
            listener.onItemClick(getItem(position))
        }
        holder.bind(getItem(position))
    }

    interface RecyclerFavOnClickListener {
        fun onItemClick(seriesUi: SeriesUi)
        fun onTvClearItemClick(seriesUi: SeriesUi)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavMoviesBinding.bind(itemView)
        fun bind(movie: SeriesUi) = movie.apply {
            with(binding) {
                Picasso.get().load(Utils.POSTER_PATH_URL + posterPath).into(posterImage)

                buttonBookmark.setOnDownEffectClickListener {
                    listener.onTvClearItemClick(getItem(adapterPosition))
                }
                motionLayout.setOnDownEffectClickListener {
                    listener.onItemClick(getItem(adapterPosition))
                }
                motionLayout.startSlideInRightAnim()

                movieTitle.text = movie.name
                dateSave.text = movie.firstAirDate
                rating.text = popularity.toString()
                voteCount.text = movie.voteCount.toFloat().toString()
            }
        }
    }
}

class TvDiffCallBack2 : DiffUtil.ItemCallback<SeriesUi>() {

    override fun areItemsTheSame(oldItem: SeriesUi, newItem: SeriesUi) =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SeriesUi, newItem: SeriesUi) =
        oldItem == newItem

}

