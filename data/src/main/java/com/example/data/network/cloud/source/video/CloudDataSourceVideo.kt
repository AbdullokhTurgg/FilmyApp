package com.example.data.network.cloud.source.video

import com.example.data.dataModel.video.VideosData
import kotlinx.coroutines.flow.Flow

interface CloudDataSourceVideo {
    fun getVideos(movieId: Int): Flow<VideosData>
}