package com.example.movieappazi.ui.zAdapter.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.R
import com.example.movieappazi.ui.zAdapter.movie.diffcallbacks.PersonDetailsListDiffCallback
import com.example.movieappazi.ui.zAdapter.movie.diffcallbacks.PersonDiffCallback
import com.example.movieappazi.ui.zAdapter.movie.diffcallbacks.PersonsItemDiffCallBack
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.zAdapter.movie.view_holders.ObjectViewHolder
import com.example.movieappazi.uiModels.person.PersonUi

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
                R.layout.object_item_portrait, parent, false
            )
        )

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.bindPerson(person = personsList[position])
        holder.view.setOnClickListener {
            listener.onItemClick(item = personsList[position])
        }
    }

    override fun getItemCount() = personsList.size
}

