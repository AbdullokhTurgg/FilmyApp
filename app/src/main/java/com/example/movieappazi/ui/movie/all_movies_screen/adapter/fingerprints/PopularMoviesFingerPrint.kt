package com.example.movieappazi.ui.movie.all_movies_screen.adapter.fingerprints

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseViewHolder
import com.example.movieappazi.app.recyclerview.Item
import com.example.movieappazi.app.recyclerview.ItemFingerprint
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.showRoundedImage
import com.example.movieappazi.databinding.ItemPopularMoviesBinding
import com.example.movieappazi.ui.movie.all_movies_screen.adapter.model.MovieAdapterModel
import com.squareup.picasso.Picasso

class PopularMoviesFingerPrint : ItemFingerprint<ItemPopularMoviesBinding, MovieAdapterModel> {

    override fun isRelativeItem(item: Item) = item is MovieAdapterModel

    override fun getLayoutId() = R.layout.item_popular_movies

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<ItemPopularMoviesBinding, MovieAdapterModel> {
        val binding = ItemPopularMoviesBinding.inflate(layoutInflater, parent, false)
        return PopularMoviesViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<MovieAdapterModel>() {

        override fun areItemsTheSame(
            oldItem: MovieAdapterModel,
            newItem: MovieAdapterModel,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieAdapterModel,
            newItem: MovieAdapterModel,
        ) = oldItem == newItem
    }

}

class PopularMoviesViewHolder(
    binding: ItemPopularMoviesBinding,
) : BaseViewHolder<ItemPopularMoviesBinding, MovieAdapterModel>(binding) {

    override fun onBind(item: MovieAdapterModel) {
        super.onBind(item)
        setupViews()
    }

    private fun setupViews() = with(binding) {
        itemView.context.showRoundedImage(imageUrl = item.posterImg, imageView = posterImage)


        Picasso.get().load(Utils.POSTER_PATH_URL + item.posterImg).into(posterImage)

        Glide.with(itemView.context).asBitmap().load(Utils.POSTER_PATH_URL + item.posterImg)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?,
                ) {
                    createPaletteAsync(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit
            })
    }

    private fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            val color = when {
                palette?.lightVibrantSwatch != null -> palette.lightVibrantSwatch!!.rgb
                palette?.lightMutedSwatch != null -> palette.lightMutedSwatch!!.rgb
                palette?.vibrantSwatch != null -> palette.vibrantSwatch!!.rgb
                palette?.mutedSwatch != null -> palette.mutedSwatch!!.rgb
                else -> itemView.context.getColor(R.color.purple_200)
            }
//            binding.re.setBackgroundColor(color)
        }
    }

}
