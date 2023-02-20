package com.example.data.storage.movie.movie

class MovieDetailsState(
    val budget: String,
    val genres: String,
    val status: String,
    val movieTitle: String,
    val moviePopularity: String,
    val movieVoteCount: String,
    val rating: String,
    val originalLanguage: String,
    val originalTitle: String,
    val release: String,
    val description: String,
    val actors: String,
    val videos: String,
    val similarMoviesText: String,
    val recommendMoviesText: String,
    val movieRuntime: String,
) {
    companion object {
        fun getRuDetails(): MovieDetailsState {
            return MovieDetailsState(
                budget = "Бюджет:",
                genres = "Жанры:",
                status = "Статус:",
                movieTitle = "Название:",
                moviePopularity = "Популярность:",
                movieVoteCount = "Проголосовали:",
                rating = "Рейтинг:",
                originalLanguage = "Оригинальный язык:",
                originalTitle = "Оригинальное название:",
                release = "Премьера:",
                description = "Описание:",
                actors = "В ролях:",
                videos = "Трейлеры и фрагменты:",
                similarMoviesText = "Похожие фильмы:",
                recommendMoviesText = "Рекомендуемые фильмы:",
                movieRuntime = "Продолжительность:"
            )
        }

        fun getUsDetails(): MovieDetailsState {
            return MovieDetailsState(
                budget = "Budget:",
                genres = "Genres",
                status = "Status",
                movieTitle = "Title:",
                moviePopularity = "Popularity:",
                movieVoteCount = "Vote:",
                rating = "Rating:",
                originalLanguage = "Original language:",
                originalTitle = "Original title:",
                release = "Release:",
                description = "Description:",
                actors = "Actors:",
                videos = "Trailers:",
                similarMoviesText = "Similar movies:",
                recommendMoviesText = "Recommend movies:",
                movieRuntime = "Runtime:"
            )
        }
    }
}