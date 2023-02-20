package com.example.movieappazi.app.recyclerview.animations.custom

import androidx.recyclerview.widget.RecyclerView

class SlideInTopCommonAnimator : CommonItemAnimator {

    override fun preAnimateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationY = holder.itemView.height.toFloat() * 5
        holder.itemView.alpha = 1f
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().translationY(1f)
        holder.itemView.animate().alpha(1f)
    }


    override fun animateRemove(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().translationY(-holder.itemView.height.toFloat() * 8)
        holder.itemView.animate().duration = 1000
    }
}