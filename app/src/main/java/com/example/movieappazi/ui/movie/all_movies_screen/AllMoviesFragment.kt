package com.example.movieappazi.ui.movie.all_movies_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.recyclerview.animations.AddableItemAnimator
import com.example.movieappazi.app.utils.recyclerview.animations.custom.SlideInLeftCommonAnimator
import com.example.movieappazi.app.utils.extensions.*
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.movie.search_movies_screen.SearchType
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllSeriesFragment.Companion.PRESSONCE
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.android.synthetic.main.item_popular_movies.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
@AndroidEntryPoint
class AllMoviesFragment :
    BaseFragment<FragmentAllMoviesBinding, AllMoviesFragmentViewModel>(FragmentAllMoviesBinding::inflate),
    RvClickListener<MovieUi> {

    override fun onReady(savedInstanceState: Bundle?) {}
    override val viewModel: AllMoviesFragmentViewModel by viewModels()

    private val popularAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val upcomingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val publAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val ratingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val comedyAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val historyMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val mysteryMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val westernMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val dramaMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val familyMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val crimeMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }
    private val thrillerMoviesAdapter by lazy { MovieItemAdapter(MovieItemAdapter.MAINITEM, this) }

    private fun setHeaders() = with(requireBinding()) {
        popularMovie.header.text = POPULARHEADER
        topRatedMovie.header.text = TOPRATEDHEADER
        nowPlayingMovie.header.text = NOWPLAYINGHEADER
        upcomingMovie.header.text = UPCOMINGHEADER
        comedyMovie.header.text = COMEDYHEADER
        historyMovie.header.text = HISTORYHEADER
        mysteryMovie.header.text = MYSTERYHEADER
        westernMovie.header.text = WESTERNHEADER
        dramaMovie.header.text = DRAMAHEADER
        familyMovie.header.text = FAMILYHEADER
        crimeMovie.header.text = CRIMEHEADER
        thrillerMovie.header.text = THRILLERHEADER
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            popularMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.POPULAR) }
            topRatedMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.TOP_RATED) }
            nowPlayingMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.NOW_PLAYING) }
            upcomingMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.UPCOMING) }
            comedyMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.COMEDY) }
            historyMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.HISTORICAL) }
            mysteryMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.MYSTERY) }
            westernMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.WESTERN) }
            dramaMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.DRAMA) }
            familyMovie.header.setOnDownEffectClickListener { goMoreMovieFragment(MovieType.FAMILY) }
            crimeMovie.header.setOnDownEffectClickListener{goMoreMovieFragment(MovieType.CRIME)}
            thrillerMovie.header.setOnDownEffectClickListener{goMoreMovieFragment(MovieType.THRILLER)}
            goSearchBtn.setOnDownEffectClickListener { goSearchMovie(SearchType.MOVIE) }
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeaders()
        setAdapterToRv()
        observeData()
        setupClickers()
        itemAnim()
    }

    private fun observeData() = with(viewModel) {
        launchWhenViewStarted {
            ratingMoviesFlow.observe { ratingAdapter.submitList(it.movies) }
            publishedAtMoviesFlow.observe { publAdapter.submitList(it.movies) }
            comedyMoviesFlow.observe { comedyAdapter.submitList(it.movies) }
            upcomingMoviesFlow.observe { upcomingAdapter.submitList(it.movies) }
            historyMoviesFlow.observe { historyMoviesAdapter.submitList(it.movies) }
            westernMoviesFlow.observe { westernMoviesAdapter.submitList(it.movies) }
            familyMoviesFlow.observe { familyMoviesAdapter.submitList(it.movies) }
            mysteryMoviesFlow.observe { mysteryMoviesAdapter.submitList(it.movies) }
            dramaMoviesFlow.observe { dramaMoviesAdapter.submitList(it.movies) }
            crimeMoviesFlow.observe { crimeMoviesAdapter.submitList(it.movies) }
            thrillerMoviesFlow.observe { thrillerMoviesAdapter.submitList(it.movies) }
            popularMoviesFlow.observe { popularAdapter.submitList(it.movies)
                visibilities() }
            error.onEach { showErrorSnackbar(it) }
        }
    }

    private fun setAdapterToRv() = with(requireBinding()) {
        popularMovie.recyclerviewPostersList.adapter = popularAdapter
        upcomingMovie.recyclerviewPostersList.adapter = upcomingAdapter
        nowPlayingMovie.recyclerviewPostersList.adapter = publAdapter
        topRatedMovie.recyclerviewPostersList.adapter = ratingAdapter
        comedyMovie.recyclerviewPostersList.adapter = comedyAdapter
        historyMovie.recyclerviewPostersList.adapter = historyMoviesAdapter
        dramaMovie.recyclerviewPostersList.adapter = dramaMoviesAdapter
        westernMovie.recyclerviewPostersList.adapter = westernMoviesAdapter
        mysteryMovie.recyclerviewPostersList.adapter = mysteryMoviesAdapter
        familyMovie.recyclerviewPostersList.adapter = familyMoviesAdapter
        crimeMovie.recyclerviewPostersList.adapter = crimeMoviesAdapter
        thrillerMovie.recyclerviewPostersList.adapter = thrillerMoviesAdapter

    }

    private fun itemAnim() = with(requireBinding()) {
        popularMovie.recyclerviewPostersList.apply {
            itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
                anim.addViewTypeAnimation(R.layout.object_item_portrait,
                    SlideInLeftCommonAnimator())
            }
        }
        topRatedMovie.recyclerviewPostersList.apply {
            itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
                anim.addViewTypeAnimation(R.layout.object_item_portrait,
                    SlideInLeftCommonAnimator())
            }
        }
    }

    private fun visibilities() {
        requireBinding().shimmerLayout.hideView()
        requireBinding().allConst.showView()
    }

    override fun onLongClick(item: MovieUi) {
        try {
            viewModel.goMovieDetails(item)
        } catch (e: Exception) {
            showErrorSnackbar(e.message.toString())
        }
    }

    override fun onItemClick(item: MovieUi) {
        try { viewModel.saveMovie(item)
            showSuccessSnackBar("Фильм ${item.title} успешно сохранено!") }
        catch (e:Exception){ showErrorSnackbar(e.message.toString()) }

    }

    companion object {
        const val POPULARHEADER = "Популярные фильмы"
        const val TOPRATEDHEADER = "Рейтинговые фильмы"
        const val NOWPLAYINGHEADER = "Сейчас на экранах"
        const val UPCOMINGHEADER = "Ожидающиеся фильмы"
        const val COMEDYHEADER = "Комедии"
        const val HISTORYHEADER = "Исторические фильмы"
        const val MYSTERYHEADER = "Тайные фильмы"
        const val WESTERNHEADER = "Западные фильмы"
        const val DRAMAHEADER = "Драматические фильмы"
        const val FAMILYHEADER = "Семейные фильмы"
        const val CRIMEHEADER = "Криминальные фильмы"
        const val THRILLERHEADER = "Триллеры"
        const val HORRORHEADER = "Фильмы ужасов"
        const val ADVENTUREHEADER = "Приключении"
        const val FANTASYHEADER = "Фильмы с жанром Фентези"
        const val MUSICHEADER = "Музыкальные"
        const val ROMANCEHEADER = "Романтические"
    }
}

