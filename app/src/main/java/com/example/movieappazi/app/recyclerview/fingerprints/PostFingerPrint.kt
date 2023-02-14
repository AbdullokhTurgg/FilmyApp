package com.example.movieappazi.app.recyclerview.fingerprints

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.recyclerview.Item
import com.example.movieappazi.app.recyclerview.ItemFingerprint
import com.example.movieappazi.app.base.BaseViewHolder
import com.example.movieappazi.databinding.ObjectItemPortraitBinding
import com.example.movieappazi.app.models.movie.MovieUi
import com.squareup.picasso.Picasso

class PostFingerPrint(
) : ItemFingerprint<ObjectItemPortraitBinding, MovieUi> {

    override fun isRelativeItem(item: Item) = item is MovieUi

    override fun getLayoutId() = R.layout.object_item_portrait

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<ObjectItemPortraitBinding, MovieUi> {
        val binding = ObjectItemPortraitBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<MovieUi>() {

        override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi) = oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi) = oldItem == newItem

        override fun getChangePayload(oldItem: MovieUi, newItem: MovieUi): Any? {
            if (oldItem.title != newItem.title) return newItem.title
            return super.getChangePayload(oldItem, newItem)
        }

    }

}

class PostViewHolder(
    binding: ObjectItemPortraitBinding,
) : BaseViewHolder<ObjectItemPortraitBinding, MovieUi>(binding) {

    override fun onBind(item: MovieUi) {
        super.onBind(item)
        with(binding) {
            Picasso.get().load(Utils.POSTER_PATH_URL + item.posterPath).into(posterImage)


//            Picasso.get().load(Utils.POSTER_PATH_URL + item.posterPath).into(R.id.saved_image)
        }
    }

    override fun onBind(item: MovieUi, payloads: List<Any>) {
        super.onBind(item, payloads)
        val isSaved = payloads.last() as Boolean
//        binding.tbLike.setChecked(isSaved)
    }
}