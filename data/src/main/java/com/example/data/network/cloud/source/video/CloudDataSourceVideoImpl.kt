package com.example.data.network.cloud.source.video

import com.example.data.dataModel.video.VideosData
import com.example.data.network.cloud.cloudModels.video.VideosCloud
import com.example.data.network.retrofit.api.video.VideoApi
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CloudDataSourceVideoImpl(
    private val videoApi: VideoApi,
    private val mapFromVideosCloudToData: BaseMapper<VideosCloud, VideosData>,
    private val dispatchersProvider: DispatchersProvider,
) : CloudDataSourceVideo {
    override fun getVideos(movieId: Int): Flow<VideosData> = flow {
        emit(videoApi.getTrailers(id = movieId))
    }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(mapFromVideosCloudToData::map)
        .flowOn(dispatchersProvider.default())
}