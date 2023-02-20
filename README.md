# MovieAppAzi

# A program written entirely in Kotlin using the principles of Clean Architecture according to the MVVM pattern.

The application shows a list of films and actors, detailed information about them, the ability to
search, as well as add to favorites.Adding to favorites takes place in the local Room database.

![1676917746711 1](https://user-images.githubusercontent.com/114995936/220178303-b02763de-220d-4396-b3a8-8efee6f4113f.jpg)
![1676917746706 1](https://user-images.githubusercontent.com/114995936/220178327-b133bd2c-1743-4010-8cdf-ec4c30ca8701.jpg)
![1676917746687 1](https://user-images.githubusercontent.com/114995936/220178367-b63f9776-75cf-458e-abc9-17d5957fd714.jpg)
![1676917746682 1](https://user-images.githubusercontent.com/114995936/220178387-a40fd25c-942c-4695-9285-92ff14acd463.jpg)



## Libraries

### Android Jetpack ...

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

* [Coroutines Flow](https://developer.android.com/topic/libraries/architecture/livedata) Data
  objects that notify views of changes to the underlying database.


* [Kotlin flows](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
  In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend
  functions that return only a single value. For example, you can use a flow to receive live updates
  from a database.

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
  
 
