package com.example.movieappazi.ui.zAdapter.video

import java.io.Serializable

class Model {
    data class MovieVideo(
        var id: String,
        var key: String,
        var name: String,
        var site: String

    ): Serializable
}