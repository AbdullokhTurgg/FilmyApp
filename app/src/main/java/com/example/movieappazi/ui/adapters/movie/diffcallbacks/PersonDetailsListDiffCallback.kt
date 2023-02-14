package com.example.movieappazi.ui.adapters.movie.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappazi.app.models.movie.CastUi
import com.example.movieappazi.app.models.person.PersonUi

class PersonDetailsListDiffCallback(
    private val oldList: List<CastUi>,
    private val newList: List<CastUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}

class PersonsItemDiffCallBack : DiffUtil.ItemCallback<PersonUi>() {
    override fun areItemsTheSame(oldItem: PersonUi, newItem: PersonUi): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PersonUi, newItem: PersonUi): Boolean =
        oldItem == newItem
}

class PersonDiffCallback(
    private val oldList: List<PersonUi>,
    private val newList: List<PersonUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}