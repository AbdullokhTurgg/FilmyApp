package com.example.movieappazi.app.utils.extensions

import android.view.View
import android.view.animation.AnimationUtils
import com.example.movieappazi.R
import com.thekhaeng.pushdownanim.PushDownAnim


fun View.startSlideInLeftAnim() {
    this.startAnimation(AnimationUtils.loadAnimation(this.context, com.example.ui_core.R.anim.item_anim))
}
fun View.startSlideInRightAnim() {
    this.startAnimation(AnimationUtils.loadAnimation(this.context, com.airbnb.lottie.R.anim.abc_slide_in_top))
}

fun View.downEffect(): View {
    PushDownAnim.setPushDownAnimTo(this)
    return this
}

fun View.setOnDownEffectClickListener(onClickListener: View.OnClickListener): View {
    PushDownAnim.setPushDownAnimTo(this).setOnClickListener(onClickListener)
    return this
}