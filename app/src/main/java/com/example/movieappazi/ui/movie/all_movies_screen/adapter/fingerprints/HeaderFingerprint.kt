package com.example.movieappazi.ui.movie.all_movies_screen.adapter.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.example.movieappazi.R
import com.example.movieappazi.app.recyclerview.Item
import com.example.movieappazi.app.recyclerview.ItemFingerprint
import com.example.movieappazi.app.base.BaseViewHolder
import com.example.movieappazi.databinding.ItemHeaderBinding
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.startSlideInLeftAnim
import com.example.movieappazi.ui.movie.all_movies_screen.adapter.base.HeaderItem

class HeaderFingerprint : ItemFingerprint<ItemHeaderBinding, HeaderItem> {


    override fun isRelativeItem(item: Item) = item is HeaderItem

    override fun getLayoutId() = R.layout.item_header

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<ItemHeaderBinding, HeaderItem> {
        val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
        return HeaderViewHolder(binding = binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<HeaderItem>() {

        override fun areItemsTheSame(oldItem: HeaderItem, newItem: HeaderItem) = true

        override fun areContentsTheSame(oldItem: HeaderItem, newItem: HeaderItem) = true
    }
}

class HeaderViewHolder(
    binding: ItemHeaderBinding,
) : BaseViewHolder<ItemHeaderBinding, HeaderItem>(binding) {

    override fun onBind(item: HeaderItem) {
        super.onBind(item)
        with(binding) {
            container.startSlideInLeftAnim()
            seeMore.isVisible = item.showMoreIsVisible
            title.text = item.titleId.format(title.context)
            if (item.showMoreIsVisible) root.setOnDownEffectClickListener { item.onClickListener() }
        }
    }
}