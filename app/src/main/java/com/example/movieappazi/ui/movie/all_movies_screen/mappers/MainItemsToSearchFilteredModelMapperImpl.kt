package com.example.movieappazi.ui.movie.all_movies_screen.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.HomeScreenItems
import com.example.domain.models.movie.MovieDomain
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.recyclerview.Item
import com.example.movieappazi.ui.movie.all_movies_screen.adapter.base.AllMoviesPopularHorizontalItem
import com.example.movieappazi.ui.movie.all_movies_screen.adapter.model.MovieAdapterModel
import com.example.movieappazi.ui.movie.all_movies_screen.listeners.MovieItemOnClickListener
import javax.inject.Inject

class MainItemsToSearchFilteredModelMapperImpl @Inject constructor(
    private val mapFromMovieDomainToMovieUi: BaseMapper<MovieDomain, MovieUi>,
) : MainItemsToSearchFilteredModelMapper {
    private companion object {
        const val MAX_ITEMS_SHOW_COUNT_IN_MAIN_SCREEN = 5
    }

    override fun map(
        items: HomeScreenItems,
        movieItemOnClickListener: MovieItemOnClickListener,
    ): Triple<List<Item>, List<Item>, List<Item>> {


        val filteredPopularMovieList = items.popularMovies
            .map(mapFromMovieDomainToMovieUi::map)
            .map { MovieAdapterModel(it.id.toString(),it.posterPath.toString(), movieItemOnClickListener) }
            .take(MAX_ITEMS_SHOW_COUNT_IN_MAIN_SCREEN)

        val filteredUpcomingMovieList = items.upcomingMovies
            .map(mapFromMovieDomainToMovieUi::map)
            .map { MovieAdapterModel(it.id.toString(),it.posterPath.toString(),movieItemOnClickListener) }
            .take(MAX_ITEMS_SHOW_COUNT_IN_MAIN_SCREEN)

        val filteredNowPlayingMovies = items.nowPlayingMovies
            .map(mapFromMovieDomainToMovieUi::map)
            .map { MovieAdapterModel(it.id.toString(),it.posterPath.toString(),movieItemOnClickListener)}
            .take(MAX_ITEMS_SHOW_COUNT_IN_MAIN_SCREEN)


        val allPopularBlockItems = mutableListOf<Item>()

        val popularMovieItem = mutableListOf<Item>()
        listOf(filteredPopularMovieList)
        popularMovieItem.addAll(filteredPopularMovieList.shuffled())
        val popularMovieBlockItem =
            AllMoviesPopularHorizontalItem(items = popularMovieItem.shuffled())
        allPopularBlockItems.addAll(listOf(popularMovieBlockItem))


        val allUpcomingBlockItems = mutableListOf<Item>()

        val upcomingMovieItem = mutableListOf<Item>()
        listOf(filteredPopularMovieList)
        upcomingMovieItem.addAll(filteredUpcomingMovieList.shuffled())
        val upcomingMovieBlockItem =
            AllMoviesPopularHorizontalItem(items = upcomingMovieItem.shuffled())
        allUpcomingBlockItems.addAll(listOf(upcomingMovieBlockItem))


        val allNowPlayingMovieItems = mutableListOf<Item>()

        listOf(filteredNowPlayingMovies)
        allNowPlayingMovieItems.addAll(filteredNowPlayingMovies.shuffled())
        val nowPlayingMovieBlockItem =
            AllMoviesPopularHorizontalItem(items = allNowPlayingMovieItems.shuffled())
        allNowPlayingMovieItems.addAll(listOf(nowPlayingMovieBlockItem))


        return Triple(allNowPlayingMovieItems, allPopularBlockItems, allUpcomingBlockItems)
    }
}