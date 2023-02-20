package com.example.movieappazi.ui.adapters.person

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.R
import com.example.movieappazi.ui.adapters.movie.diffcallbacks.PersonDiffCallback
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.adapters.movie.ObjectViewHolder
import com.example.movieappazi.app.models.person.PersonUi
import com.example.movieappazi.app.utils.extensions.startSlideInLeftAnim

class PersonAdapter(
    private val listener: RvClickListener<PersonUi>,
) : RecyclerView.Adapter<ObjectViewHolder>() {

    var personsList = listOf<PersonUi>()
        set(value) {
            val callback = PersonDiffCallback(value, personsList)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_for_seemore, parent, false
            )
        )

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.bindPerson(person = personsList[position])

        holder.view.setOnClickListener {
            listener.onItemClick(item = personsList[position])

        }
        holder.itemView.startSlideInLeftAnim()
    }

    override fun getItemCount() = personsList.size
}
