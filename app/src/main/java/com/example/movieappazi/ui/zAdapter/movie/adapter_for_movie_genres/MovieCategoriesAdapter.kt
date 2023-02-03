package com.example.movieappazi.ui.zAdapter.movie.adapter_for_movie_genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.databinding.ItemGenresBinding
import com.example.movieappazi.uiModels.movie.movie_category.MovieCategoriesUi

class MovieCategoriesAdapter :
    ListAdapter<MovieCategoriesUi, MovieCategoriesAdapter.CategoriesViewHolder>(
        CategoriesDiffCallback) {


    inner class CategoriesViewHolder(private val binding: ItemGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(content: MovieCategoriesUi) {

            binding.titleGenre.text = content.name
            binding.kategoryItem.setOnClickListener {
                it.findNavController()
//                    .navigate(SearchMoviesFragmentDirections.actionNavSearchToGenreDetailsFragment(
//                        content.id,
//                        content.name))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenresBinding.inflate(layoutInflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(content = getItem(position))
    }

}

object CategoriesDiffCallback : DiffUtil.ItemCallback<MovieCategoriesUi>() {
    override fun areItemsTheSame(oldItem: MovieCategoriesUi, newItem: MovieCategoriesUi): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MovieCategoriesUi,
        newItem: MovieCategoriesUi,
    ): Boolean {
        return oldItem == newItem
    }
}
