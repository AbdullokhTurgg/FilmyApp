package com.example.movieappazi.ui.adapters.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.models.person.PersonUi
import com.example.movieappazi.app.utils.extensions.downEffect
import com.example.movieappazi.app.utils.extensions.startSlideInLeftAnim
import com.example.movieappazi.ui.adapters.movie.ObjectViewHolder
import com.example.movieappazi.ui.adapters.movie.diffcallbacks.PersonsItemDiffCallBack
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class PersonAdapter(
    private val listener: RvClickListener<PersonUi>,
) : ListAdapter<PersonUi, ObjectViewHolder>(PersonsItemDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_for_seemore, parent, false
            )
        )


    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.bindPerson(person = getItem(position))

        holder.view.downEffect().setOnClickListener {
            listener.onItemClick(item = getItem(position))

        }
    }
}
