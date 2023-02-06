
# MovieAppAzi 
# A program written entirely in Kotlin using the principles of Clean Architecture according to the MVVM pattern.

The application shows a list of films and actors, detailed information about them, the ability to
search, as well as add to favorites.Adding to favorites takes place in the local Room database.

[<img src="[meta/android/screenshots/screenshot_1.png" width=160>]([meta/android/screenshots/screenshot_1.png](https://user-images.githubusercontent.com/114995936/216970405-16e](https://user-images.githubusercontent.com/114995936/216970405-16effdc3-f0e3-4622-b81b-56081e3cdbd6.jpg)ffdc3-f0e3-4622-b81b-56081e3cdbd6.jpg))
[<img src="meta/android/screenshots/screenshot_2.png" width=160>](meta/android/screenshots/screenshot_2.png)
[<img src="meta/android/screenshots/screenshot_3.png" width=160>](meta/android/screenshots/screenshot_3.png)
[<img src="meta/android/screenshots/screenshot_4.png" width=160>](meta/android/screenshots/screenshot_4.png)
[<img src="meta/android/screenshots/screenshot_5.png" width=160>](meta/android/screenshots/screenshot_5.png)
[<img src="meta/android/screenshots/screenshot_6.png" width=160>](meta/android/screenshots/screenshot_6.png)
[<img src="meta/android/screenshots/screenshot_7.png" width=160>](meta/android/screenshots/screenshot_7.png)
[<img src="meta/android/screenshots/screenshot_8.png" width=160>](meta/android/screenshots/screenshot_8.png)
[<img src="meta/android/screenshots/screenshot_9.png" width=160>](meta/android/screenshots/screenshot_9.png)

## Libraries

### Android Jetpack

* [ViewBinding](https://developer.android.com/topic/libraries/view-binding) View binding is a
  feature that makes it easier for you to write code that interacts with views.

* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) An interface
  that automatically responds to lifecycle events.

* [Room](https://developer.android.com/jetpack/androidx/releases/room?gclsrc=aw.ds&gclid=Cj0KCQiA09eQBhCxARIsAAYRiyl9xrvsl7MKTcUVF73v6FB8EQyG-U8YVwhZyhA5rzq2UhpBvOUOUuEaAr5jEALw_wcB)
  The Room persistence library provides an abstraction layer on top of SQLite, providing more
  reliable access to the database while simultaneously using all the features of SQLite.

* [Paging 2](https://developer.android.com/topic/libraries/architecture/paging) Swap Library helps
  to load and display small pieces of data at the same time. Downloading partial data on demand
  reduces the use of network bandwidth and system resources.

* [Navigation](https://developer.android.com/guide/navigation?gclsrc=aw.ds&gclid=Cj0KCQiA09eQBhCxARIsAAYRiymyM6hTEs0cGr5ZCXOWtLhVUwDK1O86vf8V_Uq2DWvVYNFZwPFznzAaAllMEALw_wcB)
  Navigation refers to interactions that allow users to navigate through , enter, and exit various
  parts of the content in your app. Navigation component Android Jetpack helps you navigate, from
  simple button clicks to more complex templates like application panels and navigation bar. The
  navigation component also provides a consistent and predictable user interface, adhering to an
  established set of principles.

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) Data objects that
  notify views of changes to the underlying database.


* [Kotlin flows](https://developer.android.com/kotlin/flow) In coroutines, a flow is a type that can
  emit multiple values sequentially, as opposed to suspend functions that return only a single
  value. For example, you can use a flow to receive live updates from a database.

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Data related to
  the user interface that is not destroyed when the application is rotated. Easily schedule
  asynchronous tasks for optimal execution.

### DI

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) Hilt is a
  dependency injection library for Android that reduces the execution time of manual dependency
  injection into your project. Performing manual dependency injection requires that you create each
  class and its dependencies manually, and use containers to reuse and manage dependencies.

### Image

* [Glide](https://github.com/bumptech/glide) Image loading and caching library for Android, focused
  on smooth scrolling.

* [Picasso](https://square.github.io/picasso/) Picasso allows you to easily upload images to your
  app - often in a single line of code.

## HTTP

* [Retrofit2](https://github.com/square/retrofit) Type-safe HTTP client for Android and Java.

* [OkHttp](https://github.com/square/okhttp) HTTP + HTTP/2 client for Android and Java applications.

### Coroutines

* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) Coroutines is a rich library for
  coroutines developed by JetBrains. It contains a number of high-level primitives with support for
  coroutines, which are discussed in this guide, including startup, asynchrony, and others.

### Video Player

* [Youtube Player](https://github.com/PierfrancescoSoffritti/android-youtube-player)
  Android-Youtube-Player is a stable and customizable open source YouTube player for Android. It
  provides a simple view that can be easily integrated into each Activity/Fragment.

### GitHub Custom Libraries

* [Circular Progress View](https://jitpack.io/p/vaibhavlakhera/circular-progress-view)
  A customisable circular progress view for android.
  
