package com.example.movieappazi.ui.movie.all_movies_screen.adapter.fingerprints

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseViewHolder
import com.example.movieappazi.app.recyclerview.Item
import com.example.movieappazi.app.recyclerview.ItemFingerprint
import com.example.movieappazi.app.recyclerview.fingerprints.FingerprintAdapter
import com.example.movieappazi.app.utils.extensions.startSlideInLeftAnim
import com.example.movieappazi.app.utils.extensions.toDp
import com.example.movieappazi.databinding.MainScreenPopularMovieBlockBinding
import com.example.movieappazi.ui.movie.all_movies_screen.adapter.base.AllMoviesPopularHorizontalItem

class AllMoviesPopularBlockFingerPrint(
    private val fingerprintsList: List<ItemFingerprint<*, *>>,
    private val viewPool: RecyclerView.RecycledViewPool,
) : ItemFingerprint<MainScreenPopularMovieBlockBinding, AllMoviesPopularHorizontalItem> {

    override fun isRelativeItem(item: Item) = item is AllMoviesPopularHorizontalItem

    override fun getLayoutId() = R.layout.main_screen_popular_movie_block

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
    ): BaseViewHolder<MainScreenPopularMovieBlockBinding, AllMoviesPopularHorizontalItem> {
        val binding = MainScreenPopularMovieBlockBinding.inflate(layoutInflater)
        val newLayoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        newLayoutParams.setMargins(0.toDp, 0.toDp, 8.toDp, 0.toDp)
        binding.root.layoutParams = newLayoutParams
        return MainScreenPopularMoviesViewHolder(binding, fingerprintsList, viewPool)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<AllMoviesPopularHorizontalItem>() {

        override fun areItemsTheSame(
            oldItem: AllMoviesPopularHorizontalItem,
            newItem: AllMoviesPopularHorizontalItem,
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: AllMoviesPopularHorizontalItem,
            newItem: AllMoviesPopularHorizontalItem,
        ) = oldItem == newItem

    }

}

class MainScreenPopularMoviesViewHolder(
    binding: MainScreenPopularMovieBlockBinding,
    fingerprints: List<ItemFingerprint<*, *>>,
    viewPool: RecyclerView.RecycledViewPool,
) : BaseViewHolder<MainScreenPopularMovieBlockBinding, AllMoviesPopularHorizontalItem>(binding) {

    private val fingerprintAdapter = FingerprintAdapter(fingerprints)

    init {
        with(binding.horizontalRecyclerView) {
            adapter = fingerprintAdapter
            setRecycledViewPool(viewPool)
        }
    }

    override fun onBind(item: AllMoviesPopularHorizontalItem) {
        super.onBind(item)
        setupViews()
    }

    private fun setupViews() = with(binding) {
        horizontalRecyclerView.startSlideInLeftAnim()
        fingerprintAdapter.submitList(item.items)
        horizontalRecyclerView.restoreState(item.state)
    }

    override fun onViewDetached() {
        binding.horizontalRecyclerView.onFlingListener = null
        item.state = binding.horizontalRecyclerView.layoutManager?.onSaveInstanceState() ?: return
    }

    private fun RecyclerView.restoreState(parcelable: Parcelable?) {
        if (parcelable == null) return
        layoutManager?.onRestoreInstanceState(parcelable)
    }

}