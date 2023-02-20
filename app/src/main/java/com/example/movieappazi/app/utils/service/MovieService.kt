package com.example.movieappazi.app.utils.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.movieappazi.ui.main.MainActivity
import com.example.movieappazi.R
import com.example.movieappazi.app.models.movie.MovieUi
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess


class MovieService : Service() {

//    fun showNotification(movieUi: MovieUi) {
//        val notificationManager = getSystemService("notification_service") as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel = NotificationChannel("channel_id",
//                "channel_name",
//                NotificationManager.IMPORTANCE_DEFAULT)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        val notification =
//            NotificationCompat.Builder(this, "channel_id").setContentTitle(movieUi.title)
//                .setContentText(movieUi.overview).setSmallIcon(movieUi.posterPath.toInt()).build()
//        notificationManager.notify(2, notification)
//    }

    private val movie = MutableLiveData<MovieUi>()
    val song: LiveData<MovieUi> = movie


    private val _snackbarVisible = MutableLiveData<Boolean>(false)
    val snackbarVisible: LiveData<Boolean> = _snackbarVisible

    private val _errorViewVisible = MutableLiveData<Boolean>(false)
    val errorViewVisible: LiveData<Boolean> = _errorViewVisible

    private val _errorViewText = MutableLiveData<String>()
    val errorViewText: LiveData<String> = _errorViewText

    private val _currentProgress = MutableLiveData<Long>(0)
    val currentProgress: LiveData<Long> = _currentProgress

    private val _playerState = MutableLiveData<Int>()
    val playerState: LiveData<Int> = _playerState

    // Binder given to clients
    private val binder = LocalBinder()

    private var player: ExoPlayer? = null

    private lateinit var playerNotificationManager: PlayerNotificationManager
    private lateinit var playerNotification: PlayerNotificationManager.Builder

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private val notificationId = 4131

    private var firstSet = false

    var notification: Notification? = null

    companion object {
        var isRunning: Boolean = false
    }

    inner class LocalBinder : Binder() {
        fun getService(): MovieService {
            return this@MovieService
        }
    }

    override fun onCreate() {
        super.onCreate()
        println("onCreate in MusicService")

        isRunning = true
        initializePlayer()

        playerNotification =
            PlayerNotificationManager.Builder(this@MovieService, notificationId, getChannelId())
                .setMediaDescriptionAdapter(descriptionAdapter)

        playerNotificationManager = playerNotification.build()

        playerNotificationManager.setUseStopAction(false)
        playerNotificationManager.setUsePlayPauseActions(true)
        playerNotificationManager.setUseNextAction(true)
        playerNotificationManager.setUsePreviousAction(true)
        playerNotificationManager.setUseChronometer(true)
        playerNotificationManager.setPlayer(player)
        playerNotificationManager.setUseFastForwardAction(false)
        playerNotificationManager.setUsePreviousActionInCompactView(true)
        playerNotificationManager.setUseNextActionInCompactView(true)
        playerNotificationManager.setUseRewindAction(false)
        playerNotificationManager.setUseRewindActionInCompactView(false)

        /*
        _song.observe(this){
            if(it != null){
                val notification = NotificationCompat.Builder(this@MusicService, getChannelId()).build()
                this.startForeground(notificationId, notification)
            }
        }
        */
    }
    /*
    val notificationListener = object: PlayerNotificationManager.NotificationListener{
        override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
            super.onNotificationCancelled(notificationId, dismissedByUser)
        }
    }
    */

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        println("beat da fuck out of the store owner")
        player?.let {
            player!!.pause()
        }
        val intent = Intent(this, MovieService::class.java)

        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
        super.onTaskRemoved(rootIntent)
        exitProcess(0)

    }


    override fun onDestroy() {
        println("onDestroy in MusicService")
        releasePlayer()
        isRunning = false
        stopSelf()

        notification = null

        super.onDestroy()
        exitProcess(0)
    }

    fun buildNotification() {
        if (notification == null) {
            println("notification build")
            notification = NotificationCompat.Builder(this@MovieService, getChannelId()).build()
            this.startForeground(notificationId, notification)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private fun getChannelId(): String {
        return createNotificationChannel("spotify_clone", "SpotifyClone555")
    }

    @Suppress("SameParameterValue")
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    val descriptionAdapter = object : PlayerNotificationManager.MediaDescriptionAdapter {

        override fun getCurrentContentTitle(player: Player): String = movie.value?.title ?: ""

        override fun getCurrentContentText(player: Player): String = movie.value?.releaseDate ?: ""

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback,
        ) = getCurrentLargeIcon()

        fun getCurrentLargeIcon(): Bitmap? {
            var myBitmap: Bitmap? = BitmapFactory.decodeResource(resources,
                R.drawable.queens_of_the_stone_age_lullabies_to_paralyze)

            Glide.with(this@MovieService).asBitmap()
                .load(movie.value?.id ?: "https://cdn.webrazzi.com/uploads/2015/09/google-logo.png")
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?,
                    ) {
                        myBitmap = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })

            return myBitmap
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {

            val notificationIntent = Intent(this@MovieService, MainActivity::class.java)

            return PendingIntent.getActivity(this@MovieService, 0, notificationIntent, 0)
        }
    }

    fun isPlaying(): Boolean {
        return player!!.isPlaying
    }

    fun resume() {
        player?.let {
            play()
        }
    }


    fun pause() {
        player?.let {
            player!!.pause()
        }
    }

    fun play() {
        if (!firstSet) {
            firstSet = true
            buildNotification()
            println("hey")
        }
        player?.let {
            if (!player!!.isPlaying) player!!.play()
        }
    }

    fun skipNext() {
//        player!!.seekToNextMediaItem()

        /*
        if(_song.value!!.queue < _playlist.value!!.size)
            _song.value = _playlist.value!!.get(player!!.currentMediaItemIndex)

         */
    }

    fun skipPrevious() {
        player!!.seekToPrevious()

        /*
        if(_song.value!!.queue != 0)
            _song.value = _playlist.value!!.get(player!!.currentMediaItemIndex)

         */
    }

    fun progressChanged(progress: Int, fromUser: Boolean) {
        if (fromUser) {
            player!!.seekTo(progress.toLong())
        }
    }

    fun setSong(songItem: MovieUi) {
        movie.value = songItem

        _snackbarVisible.value = true
    }


    fun initializePlayer() {
        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            exoPlayer.prepare()
        }

        player!!.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                when (reason) {
                    Player.MEDIA_ITEM_TRANSITION_REASON_AUTO -> {
//                            _song.value = _playlist.value!!.get(player!!.currentMediaItem)
                    }
                    Player.MEDIA_ITEM_TRANSITION_REASON_SEEK -> {
//                            _song.value = _playlist.value!!.get(player!!.currentMediaItemIndex)
                    }
                }
            }
//
//            override fun onIsPlayingChanged(isPlaying: Boolean) {
//                super.onIsPlayingChanged(isPlaying)
//                when (isPlaying) {
//                    true -> {
//                        _isSongPlaying.value = true
//                    }
//                    false -> {
//                        _isSongPlaying.value = false
//                    }
//                }
//            }
//
//            override fun onPlaybackStateChanged(playbackState: Int) {
//                super.onPlaybackStateChanged(playbackState)
//                when (playbackState) {
//                    Player.STATE_BUFFERING -> {
//                        _currentSongDurationInText.value = "-:--"
//                    }
//                    Player.STATE_READY -> {
//
//                        _currentSongDuration.value = player!!.duration
//
//                        _errorViewVisible.value = false
//
//                        formatTotalTime()
//                    }
//                    Player.STATE_IDLE -> {
//                        if (errorViewVisible.value!!) player!!.prepare()
//                    }
//                }
//            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                when (error.errorCode) {
                    PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> {
                        _errorViewVisible.value = true
                        _errorViewText.value = error.message
                    }
                    else -> {
                        _errorViewVisible.value = false
                    }
                }
            }
        })

        updateCurrentPlayerPosition()
    }

    fun getIsQueue(): Boolean {
        player?.let { exoPlayer ->
            return player!!.mediaItemCount > 0
        }
        return false
    }

    fun releasePlayer() {
        player?.let {
            it.release()
        }
        player = null
    }

    private fun updateCurrentPlayerPosition() {
        serviceScope.launch {
            while (true) {
                player?.let {
                    val position = player!!.currentPosition
                    //val position = playbackState.value?.currentPlaybackPosition
                    if (_currentProgress.value!! != position) {
                        _currentProgress.postValue(position)
                        //_curSongDuration.postValue(MusicService.currentSongDuration)
                    }
                    delay(100L)
                }
            }
        }
    }

    //viewmodel
    fun setSnackbar(isVisible: Boolean) {
        _snackbarVisible.value = isVisible
    }

//    fun formatTotalTime() {
//        _currentSongDurationInText.value = String.format("%d:%02d",
//            TimeUnit.MILLISECONDS.toMinutes(_currentSongDuration.value!!) % TimeUnit.HOURS.toMinutes(
//                1),
//            TimeUnit.MILLISECONDS.toSeconds(_currentSongDuration.value!!) % TimeUnit.MINUTES.toSeconds(
//                1))
//    }

    //viewmodel
    fun formatTime(value: Long): String {
        return String.format("%d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(value) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(value) % TimeUnit.MINUTES.toSeconds(1))
    }
}