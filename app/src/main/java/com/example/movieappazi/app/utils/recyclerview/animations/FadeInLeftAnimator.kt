package com.example.newsappazi.adapter.animations

import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.app.utils.recyclerview.animations.BaseItemAnimator

open class FadeInLeftAnimator : BaseItemAnimator {
    constructor()
    constructor(interpolator: Interpolator) {
        this.interpolator = interpolator
    }

    override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationX(-holder.itemView.rootView.width * .25f)
            alpha(0f)
            duration = removeDuration
            interpolator = interpolator
            setListener(DefaultRemoveAnimatorListener(holder))
            startDelay = getRemoveDelay(holder)
        }.start()
    }

    override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationX = -holder.itemView.rootView.width * .25f
        holder.itemView.alpha = 0f
    }

    override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationX(0f)
            alpha(1f)
            duration = addDuration
            interpolator = interpolator
            setListener(DefaultAddAnimatorListener(holder))
            startDelay = getAddDelay(holder)
        }.start()
    }
}