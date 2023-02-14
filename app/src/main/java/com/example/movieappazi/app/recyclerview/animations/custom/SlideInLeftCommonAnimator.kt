package com.example.movieappazi.app.recyclerview.animations.custom

import androidx.recyclerview.widget.RecyclerView

class SlideInLeftCommonAnimator : CommonItemAnimator {

    override fun animateRemove(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().translationX(-holder.itemView.rootView.width.toFloat())
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().translationX(1f)
    }

    override fun preAnimateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationX = -holder.itemView.rootView.width.toFloat()
    }

}