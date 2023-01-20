package com.example.movieappazi.ui.zAdapter.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.R
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.diffcallbacks.PersonDetailsListDiffCallback
import com.example.movieappazi.ui.zAdapter.movie.view_holders.ObjectViewHolder
import com.example.movieappazi.uiModels.person.PersonDetailsUi

class PersonDetailsAdapter(
    private val listener: RvClickListener,
    private val objectViewType: Int,
) : RecyclerView.Adapter<ObjectViewHolder>() {

    var personsList = emptyList<PersonDetailsUi>()
        set(value) {
            val callback = PersonDetailsListDiffCallback(value, personsList)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == MovieItemAdapter.PORTRAIT_TYPE) {
            MovieItemAdapter.PORTRAIT_TYPE
        } else MovieItemAdapter.HORIZONTAL_TYPE
    }

    companion object {
        const val PORTRAIT_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val layout = when (viewType) {
            MovieItemAdapter.PORTRAIT_TYPE -> R.layout.object_item_portrait
            MovieItemAdapter.HORIZONTAL_TYPE -> R.layout.object_item_horizontal
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ObjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onPersonItemClick(personsList[position])
        }
        holder.bindPersonDetails(personsList[position])
    }


    override fun getItemCount(): Int = personsList.size

    interface RvClickListener {
        fun onPersonItemClick(person: PersonDetailsUi)
    }
}