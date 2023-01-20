package com.example.domain.domainRepositories.network.video

import com.example.domain.domainModels.video.VideosDomain
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideos(movieId: Int): Flow<VideosDomain>
}