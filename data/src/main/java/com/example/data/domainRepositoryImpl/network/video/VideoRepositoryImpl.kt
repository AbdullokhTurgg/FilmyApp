package com.example.data.domainRepositoryImpl.network.video

import com.example.data.dataModel.video.VideosData
import com.example.data.network.cloud.source.video.CloudDataSourceVideo
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.video.VideosDomain
import com.example.domain.domainRepositories.network.video.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class VideoRepositoryImpl(
    private val dataSourceVideo: CloudDataSourceVideo,
    private val mapFromVideosDataToDomain: BaseMapper<VideosData, VideosDomain>,
    private val dispatchersProvider: DispatchersProvider,
) : VideoRepository {
    override fun getVideos(movieId: Int): Flow<VideosDomain> =
        dataSourceVideo.getVideos(movieId = movieId)
            .map(mapFromVideosDataToDomain::map)
            .flowOn(dispatchersProvider.default())

}