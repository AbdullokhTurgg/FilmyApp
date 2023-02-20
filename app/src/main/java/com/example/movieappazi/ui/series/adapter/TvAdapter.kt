package com.example.movieappazi.ui.series.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.R
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.utils.extensions.downEffect
import com.example.movieappazi.app.utils.extensions.startSlideInLeftAnim
import com.example.movieappazi.ui.adapters.movie.ObjectViewHolder

class TvAdapter(
    private val objectViewType: Int,
    private val listener: RecyclerOnClickListener,
) : RecyclerView.Adapter<ObjectViewHolder>() {

    var seriesList = listOf<SeriesUi>()
        set(value) {
            val callback = TvDiffCallBack(seriesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val layout = when (viewType) {
            FANCY_TYPE -> R.layout.item_person_for_movie_details
            PORTRAIT_TYPE -> R.layout.object_item_portrait
            HORIZONTAL_TYPE -> R.layout.item_for_seemore
            POPULAR_TYPE -> R.layout.item_popular_movies
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ObjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onItemClick(seriesList[position])
        }
        holder.view.downEffect().setOnLongClickListener {
            listener.onLongItemClick(seriesList[position])
            true
        }
        holder.bindTvMovie(tv = seriesList[position])

        holder.itemMovie.startSlideInLeftAnim()

    }

    override fun getItemCount() = seriesList.size

    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == PORTRAIT_TYPE) {
            PORTRAIT_TYPE
        } else if (objectViewType == FANCY_TYPE) {
            FANCY_TYPE

        } else if (objectViewType == POPULAR_TYPE) {
            POPULAR_TYPE
        } else HORIZONTAL_TYPE

    }

    companion object {
        const val PORTRAIT_TYPE = 1
        const val HORIZONTAL_TYPE = 0
        const val FANCY_TYPE = 2
        const val POPULAR_TYPE = 3
    }

    interface RecyclerOnClickListener {
        fun onItemClick(movie: SeriesUi)
        fun onLongItemClick(movie: SeriesUi)
    }
}

class TvDiffCallBack(
    private val oldList: List<SeriesUi>,
    private val newList: List<SeriesUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}