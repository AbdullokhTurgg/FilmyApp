package com.example.movieappazi.ui.movie.search_movies_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.utils.recyclerview.animations.AddableItemAnimator
import com.example.movieappazi.app.utils.recyclerview.animations.custom.SlideInLeftCommonAnimator
import com.example.movieappazi.app.utils.extensions.hideView
import com.example.movieappazi.app.utils.extensions.launchWhenViewStarted
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.showView
import com.example.movieappazi.databinding.FragmentSearchMoviesBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.adapters.tv.TvAdapter
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.onEach

@Parcelize
enum class SearchType : Parcelable {
    MOVIE, TV
}

@AndroidEntryPoint
class SearchMoviesFragment :
    BaseFragment<FragmentSearchMoviesBinding, SearchMoviesFragmentViewModel>(
        FragmentSearchMoviesBinding::inflate,
    ), RvClickListener<MovieUi>, TvAdapter.RecyclerOnClickListener {

    override fun onReady(savedInstanceState: Bundle?) {}
    override val viewModel: SearchMoviesFragmentViewModel by viewModels()
    private val args by navArgs<SearchMoviesFragmentArgs>()
    private val moviesSearchAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val seriesSearchAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupType()
    }

    private fun setupType() = with(requireBinding()) {
        when (args.type) {
            SearchType.MOVIE -> {
                setupClickers()
                searchMovie()
                setGenresImagesOfMovie()
                setUiMovie()
                nextAnim()
            }
            SearchType.TV -> {
                tvSearchImage.showView()
                genreLiner.hideView()
                setUiSerie()
                searchSeries()
                nextAnim()
            }
        }
    }

    private fun setUiMovie() = with(requireBinding()) {
        moviesRv.adapter = moviesSearchAdapter
    }

    private fun setUiSerie() = with(requireBinding()) {
        moviesRv.adapter = seriesSearchAdapter
    }

    private fun observeMovieData(key: String) = with(viewModel) {
        launchWhenViewStarted {
            searchMovie(key).observe {
                moviesSearchAdapter.submitList(it.movies)
                error.onEach { showErrorSnackbar(it) }
            }
        }
    }

    private fun observeTvData(key: String) = with(viewModel) {
        launchWhenViewStarted {
            searchSeries(key).observe {
                seriesSearchAdapter.seriesList = it.series
                error.onEach { showErrorSnackbar(it) }
            }
        }
    }

    private fun setupClickers() = with(requireBinding()){
        viewModel.apply {
            genresHorror.container.setOnDownEffectClickListener{goSeeMore(MovieType.HORROR)}
            genresAdventure.container.setOnDownEffectClickListener{goSeeMore(MovieType.ADVENTURE)}
            genreComedy.container.setOnDownEffectClickListener{goSeeMore(MovieType.COMEDY)}
            genreCrime.container.setOnDownEffectClickListener{goSeeMore(MovieType.CRIME)}
            genreDrama.container.setOnDownEffectClickListener{goSeeMore(MovieType.DRAMA)}
            genreFantasy.container.setOnDownEffectClickListener{goSeeMore(MovieType.FANTASY)}
            genreHistory.container.setOnDownEffectClickListener{goSeeMore(MovieType.HISTORICAL)}
            genresMusic.container.setOnDownEffectClickListener{goSeeMore(MovieType.MUSIK)}
            genresRomance.container.setOnDownEffectClickListener{goSeeMore(MovieType.ROMANCE)}
            genresWestern.container.setOnDownEffectClickListener{goSeeMore(MovieType.WESTERN)}
        }
    }

    @SuppressLint("CheckResult")
    private fun setGenresImagesOfMovie() = with(requireBinding()) {
        genresHorror.title.text = "Фильмы ужасов"
        Picasso.get().load(getString(R.string.horror_image)).into(genresHorror.cover)
        genresAdventure.title.text = "Приключенческие фильмы"
        Picasso.get().load(getString(R.string.deadpool_image)).into(genresAdventure.cover)
        genreCrime.title.text = "Криминальные фильмы"
        Picasso.get().load(getString(R.string.crime_image)).into(genreCrime.cover)
        genreComedy.title.text = "Комедии"
        Picasso.get().load(getString(R.string.comedy_image)).into(genreComedy.cover)
        genreDrama.title.text = "Драматические фильмы"
        Picasso.get().load(getString(R.string.drama_image)).into(genreDrama.cover)
        genreFantasy.title.text = "Фэнтези"
        Picasso.get().load(getString(R.string.fantasy_image)).into(genreFantasy.cover)
        genreHistory.title.text = "Исторические фильмы"
        Picasso.get().load(getString(R.string.history_image)).into(genreHistory.cover)
        genresMusic.title.text = "Музыкальные фильмы"
        Picasso.get().load(getString(R.string.music_image)).into(genresMusic.cover)
        genresRomance.title.text = "Романтические фильмы"
        Picasso.get().load(getString(R.string.romance_image)).into(genresRomance.cover)
        genresWestern.title.text = "Фильмы о войне"
        Picasso.get().load(getString(R.string.war_image)).into(genresWestern.cover)
    }

    private fun searchMovie() = with(requireBinding()) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != "") {
                    setVisible()
                    observeMovieData(query!!)
                } else {
                    setInvisible()
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != "") {
                    setVisible()
                    observeMovieData(query!!)
                } else {
                    setInvisible()
                }
                return true
            }
        })
    }

    private fun searchSeries() = with(requireBinding()) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != "") {
                    setVisibleTv()
                    observeTvData(query!!)
                } else {
                    setInvisibleTv()
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != "") {
                    setVisibleTv()
                    observeTvData(query!!)
                } else {
                    setInvisibleTv()
                }
                return true
            }
        })
    }

    private fun setVisible() = with(requireBinding()) {
        moviesRv.showView()
        itemMovie.hideView()
    }

    private fun setInvisible() = with(requireBinding()) {
        moviesRv.hideView()
        itemMovie.showView()
    }

    private fun setVisibleTv() = with(requireBinding()) {
        moviesRv.showView()
        tvSearchImage.hideView()
    }

    private fun setInvisibleTv() = with(requireBinding()) {
        moviesRv.hideView()
        tvSearchImage.showView()
    }

    private fun nextAnim() = with(requireBinding().moviesRv) {
        itemAnimator = AddableItemAnimator(

            SlideInLeftCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(R.layout.object_item_portrait, SlideInLeftCommonAnimator())

            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION

        }
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(movie = item)
        showSuccessSnackBar("Фильм ${item.title} успешно сохранено")
    }

    override fun onLongClick(item: MovieUi) {
        try {
            viewModel.launchMovieDetails(item)
        } catch (e: Exception) {
            showErrorSnackbar(e.toString())
        }
    }

    override fun onItemClick(tv: SeriesUi) {
        try {
            viewModel.launchTvDetails(tv)
        } catch (e: Exception) {
            showErrorSnackbar(e.toString())
        }
    }

    override fun onLongItemClick(tv: SeriesUi) {
        viewModel.saveTv(tv)
        showSuccessSnackBar("Сериал ${tv.name} успешно сохранено")

    }

}