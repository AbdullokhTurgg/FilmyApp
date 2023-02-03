package com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.retrofit.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.databinding.ItemFavMoviesBinding
import com.example.movieappazi.uiModels.movie.MovieUi
import com.squareup.picasso.Picasso

class SavedMoviesAdapter(
    private val list: List<MovieUi>,
    private val listener: RecyclerFavOnClickListener,
) : ListAdapter<MovieUi, SavedMoviesAdapter.ViewHolder>(SavedMoviesDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fav_movies, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
        holder.itemView.setOnLongClickListener {
            listener.onLongClick(position)
            true
        }
//        holder.itemMovie.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,
//            R.anim.sli))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RecyclerFavOnClickListener {
        fun onLongClick(position: Int)
        fun onItemClick(position: Int)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavMoviesBinding.bind(itemView)
        val itemMovie = itemView.findViewById<ConstraintLayout>(R.id.const_fav)
        fun bind(article: MovieUi) = article.apply {
            with(binding) {
                Picasso.get().load(Utils.POSTER_PATH_URL + article.posterPath).into(savedImage)
                movieTitle.text = article.title
                dateSave.text = article.releaseDate
                rating.text = article.rating.toString()
            }
        }
    }
}

class SavedMoviesDiffCallBack : DiffUtil.ItemCallback<MovieUi>() {
    override fun areItemsTheSame(
        oldItem: MovieUi,
        newItem: MovieUi,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: MovieUi,
        newItem: MovieUi,
    ): Boolean {
        return oldItem == newItem
    }
}