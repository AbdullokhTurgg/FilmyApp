package com.example.movieappazi.ui.movie.see_all_movies_screen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.extensions.*
import com.example.movieappazi.app.utils.motion.MotionListener
import com.example.movieappazi.app.utils.motion.MotionState
import com.example.movieappazi.app.utils.recyclerview.animations.AddableItemAnimator
import com.example.movieappazi.app.utils.recyclerview.animations.custom.SlideBackCommonAnimator
import com.example.movieappazi.app.utils.recyclerview.animations.custom.SlideInTopCommonAnimator
import com.example.movieappazi.databinding.FragmentSeeMoreBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.ADVENTUREHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.COMEDYHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.CRIMEHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.DRAMAHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.FAMILYHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.FANTASYHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.HISTORYHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.HORRORHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.MUSICHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.MYSTERYHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.NOWPLAYINGHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.POPULARHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.ROMANCEHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.THRILLERHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.TOPRATEDHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.UPCOMINGHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragment.Companion.WESTERNHEADER
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@Parcelize
enum class MovieType : Parcelable {
    POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING, COMEDY, HISTORICAL, DRAMA, WESTERN, MYSTERY, FAMILY, CRIME, THRILLER, HORROR,
    ADVENTURE, FANTASY, MUSIK, ROMANCE
}

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class SeeAllMoviesFragment :
    BaseFragment<FragmentSeeMoreBinding, AllMoviesFragmentViewModel>(FragmentSeeMoreBinding::inflate),
    RvClickListener<MovieUi> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val viewModel: AllMoviesFragmentViewModel by viewModels()
    override fun onReady(savedInstanceState: Bundle?) {}

    private val args by navArgs<SeeAllMoviesFragmentArgs>()

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Фильм ${item.title} успешно сохранено")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onLongClick(item: MovieUi) {
        try {
            viewModel.goMovieDetailsFromSeeMore(item)
        } catch (e: Exception) {
            showErrorSnackbar(e.toString())
        }
    }

    private val ratingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val popularAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val upcomingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val nowPlayingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val comedyAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val historyMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val mysteryMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val westernMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val dramaMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val familyMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val crimeMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val thrillerMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val horrorMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val adventureMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val fantasyMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val musicMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val romanceMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val motionListener = MotionListener(::setToolbarState)

    private fun setupViews() = with(requireBinding()) {
        root.addTransitionListener(motionListener)
    }

    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED -> {
                viewModel.updateMotionPosition(COLLAPSED)
                requireBinding().scrollView.smoothScrollTo(0, 0)
            }

            MotionState.EXPANDED -> viewModel.updateMotionPosition(EXPANDED)
            else -> Unit
        }
    }

    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextAnim()
        setupViews()
        setupTypes()
        setupClickers()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setupClickers() = with(requireBinding()) {
        upButton.setOnDownEffectClickListener { viewModel.navigateBack() }

        viewModel.apply {
            nextBtnMovie.setOnDownEffectClickListener {
                nextPage()
                nextAnim()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }

            prevBtnMovie.setOnDownEffectClickListener {
                previousPage()
                prevAnim()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }

    }

    private fun visibilities() = with(requireBinding()) {
        pageConstraint.showView()
        isEmptyLoading.hideView()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun setupTypes() = with(requireBinding()) {
        when (args.type) {
            MovieType.TOP_RATED -> {
                moviesRv.adapter = ratingAdapter
                title.text = TOPRATEDHEADER
                observeAllTopRated()
            }
            MovieType.UPCOMING -> {
                moviesRv.adapter = upcomingAdapter
                title.text = UPCOMINGHEADER
                observeAllUpcoming()
            }
            MovieType.POPULAR -> {
                moviesRv.adapter = popularAdapter
                title.text = POPULARHEADER
                observeAllPopular()
            }
            MovieType.NOW_PLAYING -> {
                moviesRv.adapter = nowPlayingAdapter
                title.text = NOWPLAYINGHEADER
                observeAllNowPlaying()
            }
            MovieType.COMEDY -> {
                moviesRv.adapter = comedyAdapter
                title.text = COMEDYHEADER
                observeAllComedy()
            }
            MovieType.FAMILY -> {
                moviesRv.adapter = familyMoviesAdapter
                title.text = FAMILYHEADER
                observeAllFamily()
            }
            MovieType.HISTORICAL -> {
                moviesRv.adapter = historyMoviesAdapter
                title.text = HISTORYHEADER
                observeAllHistorical()
            }
            MovieType.DRAMA -> {
                moviesRv.adapter = dramaMoviesAdapter
                title.text = DRAMAHEADER
                observeAllDrama()
            }
            MovieType.MYSTERY -> {
                moviesRv.adapter = mysteryMoviesAdapter
                title.text = MYSTERYHEADER
                observeAllMystery()
            }
            MovieType.WESTERN -> {
                moviesRv.adapter = westernMoviesAdapter
                title.text = WESTERNHEADER
                observeAllWestern()
            }
            MovieType.CRIME -> {
                moviesRv.adapter = crimeMoviesAdapter
                title.text = CRIMEHEADER
                observeAllCrime()
            }
            MovieType.THRILLER -> {
                moviesRv.adapter = thrillerMoviesAdapter
                title.text = THRILLERHEADER
                observeAllThriller()
            }
            MovieType.HORROR -> {
                moviesRv.adapter = horrorMoviesAdapter
                title.text = HORRORHEADER
                observeAllHorror()
            }
            MovieType.ADVENTURE -> {
                moviesRv.adapter = adventureMoviesAdapter
                title.text = ADVENTUREHEADER
                observeAllAdventure()
            }
            MovieType.FANTASY -> {
                moviesRv.adapter = fantasyMoviesAdapter
                title.text = FANTASYHEADER
                observeAllFantasy()
            }
            MovieType.MUSIK -> {
                moviesRv.adapter = musicMoviesAdapter
                title.text = MUSICHEADER
                observeAllMusic()
            }
            MovieType.ROMANCE -> {
                moviesRv.adapter = romanceMoviesAdapter
                title.text = ROMANCEHEADER
                observeAllRomance()
            }
        }
        observeBtns()
    }


    private fun observeAllNowPlaying() = with(viewModel) {
        launchWhenViewStarted {
            publishedAtMoviesFlow.observe {
                nowPlayingAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllUpcoming() = with(viewModel) {
        launchWhenViewStarted {
            upcomingMoviesFlow.observe {
                upcomingAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllPopular() = with(viewModel) {
        launchWhenViewStarted {
            popularMoviesFlow.observe {
                popularAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllTopRated() = with(viewModel) {
        launchWhenViewStarted {
            ratingMoviesFlow.observe {
                ratingAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllComedy() = with(viewModel) {
        launchWhenViewStarted {
            comedyMoviesFlow.observe {
                comedyAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllWestern() = with(viewModel) {
        launchWhenViewStarted {
            westernMoviesFlow.observe {
                westernMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllFamily() = with(viewModel) {
        launchWhenViewStarted {
            familyMoviesFlow.observe {
                familyMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllHistorical() = with(viewModel) {
        launchWhenViewStarted {
            historyMoviesFlow.observe {
                historyMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllDrama() = with(viewModel) {
        launchWhenViewStarted {
            dramaMoviesFlow.observe {
                dramaMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllMystery() = with(viewModel) {
        launchWhenViewStarted {
            mysteryMoviesFlow.observe {
                mysteryMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllCrime() = with(viewModel) {
        launchWhenViewStarted {
            crimeMoviesFlow.observe {
                crimeMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllThriller() = with(viewModel) {
        launchWhenViewStarted {
            thrillerMoviesFlow.observe {
                thrillerMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllHorror() = with(viewModel) {
        launchWhenViewStarted {
            horrorMoviesFlow.observe {
                horrorMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllAdventure() = with(viewModel) {
        launchWhenViewStarted {
            adventureMoviesFlow.observe {
                adventureMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllFantasy() = with(viewModel) {
        launchWhenViewStarted {
            fantasyMoviesFlow.observe {
                fantasyMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllMusic() = with(viewModel) {
        launchWhenViewStarted {
            musicMoviesFlow.observe {
                musicMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun observeAllRomance() = with(viewModel) {
        launchWhenViewStarted {
            romanceMoviesFlow.observe {
                romanceMoviesAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }


    private fun FragmentSeeMoreBinding.observeBtns() = lifecycleScope.launchWhenResumed {
        viewModel.movieResponseState.collectLatest { state ->
            prevPageText.text = state.previousPage.toString()
            currentPageText.text = state.page.toString()
            nextPageText.text = state.nextPage.toString()

            prevBtnMovie.apply {
                isClickable = state.isHasPreviousPage
                isFocusable = state.isHasPreviousPage
            }
            nextBtnMovie.apply {
                isClickable = state.isHasNextPage
                isFocusable = state.isHasNextPage
            }
        }
    }

    private fun nextAnim() = with(requireBinding().moviesRv) {
        itemAnimator = AddableItemAnimator(

            SlideInTopCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(R.layout.object_item_portrait, SlideInTopCommonAnimator())

            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    private fun prevAnim() = with(requireBinding().moviesRv) {
        itemAnimator = AddableItemAnimator(

            SlideBackCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(R.layout.object_item_portrait, SlideBackCommonAnimator())

            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }
}