package com.example.movieappazi.ui.adapters.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.databinding.TrailerLayoutItemBinding

class TrailerAdapter(
    var trailerList: List<Model.MovieVideo>,
    private val trailerOnClick: (Model.MovieVideo) -> Unit,
) : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {

    class TrailerViewHolder(val binding: TrailerLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trailer: Model.MovieVideo, trailerOnClick: (Model.MovieVideo) -> Unit) {
            itemView.setOnClickListener { trailerOnClick(trailer) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {

        val view =
            TrailerLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.binding.tvTrailerTitle.text = trailerList[position].name
        holder.bind(trailerList[position], trailerOnClick)
    }
}