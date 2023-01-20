package com.example.movieappazi.ui.movie_details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.data.network.retrofit.utils.Utils
import com.example.domain.state.DataRequestState
import com.example.movieappazi.databinding.FragmentMovieDetailsBinding
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.zAdapter.person.PersonAdapter
import com.example.movieappazi.ui.zAdapter.person.PersonDetailsAdapter
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), RvClickListener<MovieUi>,
    PersonDetailsAdapter.RvClickListener {

    private val binding: FragmentMovieDetailsBinding by lazy {
        FragmentMovieDetailsBinding.inflate(layoutInflater)
    }

    private val movieId: Int by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.id
    }
    private val actorsIds: List<Int> by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.genre_ids
    }

    @Inject
    lateinit var viewModelFactory: MovieDetailsFragmentViewModelFactory.Factory

    private val viewModel by viewModels<MovieDetailsFragmentViewModel> {
        viewModelFactory.create(movieId = movieId, actorsIds = actorsIds)
    }

    private val similarMoviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this@MovieDetailsFragment)
    }

    private val recommendMoviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this@MovieDetailsFragment)
    }

    private val personAdapter: PersonDetailsAdapter by lazy {
        PersonDetailsAdapter(this@MovieDetailsFragment, PersonDetailsAdapter.PORTRAIT_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdaptersToRv()
        observeMovieDetails()
        observeRecommended()
        observePerson()
        observeSimilarMovies()
    }

    private fun setAdaptersToRv() = with(binding) {
        recommendMoviesRv.adapter = recommendMoviesAdapter
        similarMoviesRv.adapter = similarMoviesAdapter
        actorsRv.adapter = personAdapter
    }

    private fun observeRecommended() {
        lifecycleScope.launchWhenResumed {
            viewModel.recommendMoviesFlow.collectLatest {
                recommendMoviesAdapter.submitList(it.movies)
            }
        }
    }

    private fun observeSimilarMovies() {
        lifecycleScope.launchWhenStarted {
            viewModel.similarMoviesFlow.collectLatest {
                similarMoviesAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePerson() {
        lifecycleScope.launchWhenStarted {
            viewModel.persons.collectLatest {
                personAdapter.personsList = it
            }
        }
    }

    private fun observeMovieDetails() {
        lifecycleScope.launchWhenStarted {
            viewModel.movieFlow.collectLatest {
                when (it) {
                    is DataRequestState.Success -> {
                        setMovieUi(it.data)
                    }
                    is DataRequestState.Error -> {
                        makeToast(it.error.message.toString(), requireContext())
                    }
                }
            }
        }

        viewModel.error.onEach {
            makeToast(it, requireContext())
        }

    }

    private fun setMovieUi(movie: MovieDetailsUi) {
        with(binding) {
            topTitle.text = movie.title
            title.text = movie.title
            popularity.text = movie.popularity.toString()
            voteCount.text = movie.voteCount.toString()
            budget.text = movie.budget.toString()
            voteAverage.rating = movie.voteAverage.toFloat()
            originalLanguage.text = movie.originalLanguage
            originalTitle.text = movie.originalTitle
            releaseDate.text = movie.releaseDate
            status.text = movie.status
            overview.text = movie.overview
            Picasso.get().load(Utils.POSTER_PATH_URL + movie.backdrop_path).into(top_main_image)
            Picasso.get().load(Utils.POSTER_PATH_URL + movie.posterPath).into(poster_image)
        }
    }

    override fun onItemClick(item: MovieUi) {
    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(
            item))
    }

    override fun onPersonItemClick(person: PersonDetailsUi) = Unit
}