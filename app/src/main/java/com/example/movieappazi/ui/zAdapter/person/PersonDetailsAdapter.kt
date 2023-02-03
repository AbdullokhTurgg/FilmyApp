package com.example.movieappazi.ui.zAdapter.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.retrofit.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.diffcallbacks.PersonDetailsListDiffCallback
import com.example.movieappazi.ui.zAdapter.movie.view_holders.ObjectViewHolder
import com.example.movieappazi.uiModels.movie.CastUi
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import com.example.movieappazi.uiModels.person.PersonUi
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class PersonDetailsAdapter(
    private val listener: RvClickListener,
) : RecyclerView.Adapter<ObjectViewHolder>() {

    var personsList = emptyList<CastUi>()
        set(value) {
            val callback = PersonDetailsListDiffCallback(value, personsList)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.object_item_portrait, parent, false)
        return ObjectViewHolder(view)
    }


    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onPersonItemClick(personsList[position])
        }
        holder.bindActors(personsList[position])
    }


    override fun getItemCount(): Int = personsList.size

    interface RvClickListener {
        fun onPersonItemClick(person: CastUi)
    }
}