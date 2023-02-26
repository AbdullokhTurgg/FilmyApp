package com.example.movieappazi.app.utils.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.movieappazi.R
import com.example.movieappazi.app.models.movie.ResponseState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun RequestBuilder<Drawable>.doOnFinished(block: () -> Unit) =
    addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean,
        ): Boolean {
            block()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean,
        ): Boolean {
            block()
            return false
        }

    })

fun <T> createMutableSharedFlowAsSingleLiveEvent(): MutableSharedFlow<T> =
    MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)

fun <T> createMutableSharedFlowAsLiveData(): MutableSharedFlow<T> =
    MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)

fun changeResponseState(page: Int, totalPage: Int): ResponseState =
    ResponseState(totalPage = totalPage,
        page = page,
        isHasNextPage = page < totalPage,
        isHasPreviousPage = page > 1)


fun makeToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}


fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner) {
        it?.let(observer)
    }
}

class DetailsTransition : TransitionSet {
    constructor() {
        init()
    }

    /**
     * This constructor allows us to use this transition in XML
     */
    @Suppress("unused")
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
            .addTransition(ChangeTransform())
            .addTransition(ChangeImageTransform())
    }
}

fun posterTransitionName(movieId: Long) = "poster$movieId"
fun titleTransitionName(movieId: Long) = "title$movieId"
fun dateTransitionName(movieId: Long) = "date$movieId"