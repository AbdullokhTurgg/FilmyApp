package com.example.data.network.paging.paging

//class AllPopularMoviesPagingCloudSource @AssistedInject constructor(
//    private val movieApi: MovieApi,
//    @Assisted("query") private val query: String,
//    private val map: BaseMapper<MovieCloud, MovieData>,
//) : PagingSource<Int, MovieData>() {
//
//
//    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
//        val anchorPosition = state.anchorPosition ?: return null
//        val page = state.closestPageToPosition(anchorPosition) ?: return null
//        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
//    }
//
//    @AssistedFactory
//    interface Factory {
//        fun create(@Assisted("query") query: String): AllPopularMoviesPagingCloudSource
//    }
//
//    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, MovieData> {
//        if (query.isEmpty()) {
//            return PagingSource.LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
//        }
//        val page: Int = params.key ?: 1
//        val pageSize: Int = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)
//
//        val response = movieApi.getPopularMovies(page = page, pageSize = pageSize)
//        if (response.isSuccessful) {
//            val articles = checkNotNull(response.body()).movies.map(map::map)
//            val nextKey = if (articles.size < pageSize) null else page + 1
//            val prefKey = if (page == 1) null else page - 1
//            return PagingSource.LoadResult.Page(articles, prefKey, nextKey)
//        } else {
//            return PagingSource.LoadResult.Error(HttpException(response))
//        }
//    }
//}
