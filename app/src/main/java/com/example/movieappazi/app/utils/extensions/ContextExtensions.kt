package com.example.movieappazi.app.utils.extensions

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieappazi.R
import com.example.movieappazi.app.utils.blur.BlurTransformation
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


private fun shimmerDrawable(): ShimmerDrawable {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1800) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    // This is the placeholder for the imageView
    return ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
}

fun Context.showRoundedImage(
    roundedSize: Int = 8.toDp,
    imageUrl: String,
    imageView: ImageView,
    @DrawableRes placeHolder: Int = R.drawable.ron,
) {
    val requestOptions = RequestOptions()
        .transforms(CenterCrop(), RoundedCorners(roundedSize))
        .timeout(3000)
        .placeholder(shimmerDrawable())
    Glide.with(this)
        .load(imageUrl)
        .apply(requestOptions)
        .into(imageView)
}
fun Context.showBlurImage(
    blurSize: Float,
    imageUrl: String,
    imageView: ImageView
) {
    Glide.with(this)
        .load(imageUrl)
        .transform(BlurTransformation(blurSize))
        .into(imageView)
}