package com.example.movieappazi.ui.actors_screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.data.dataModel.movie.MovieData
import com.example.data.network.retrofit.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentActorsBinding
import com.example.movieappazi.ui.all_movies_screen.AllMoviesFragmentDirections
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.zAdapter.person.PersonAdapter
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import com.example.movieappazi.uiModels.person.PersonUi
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ActorsFragment : Fragment(), RvClickListener<PersonUi> {
    private val binding by lazy {
        FragmentActorsBinding.inflate(layoutInflater)
    }
    private val viewModel: ActorsFragmentViewModel by viewModels()
    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter(this)
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
        setupRecyclerView()
        observePersons()
    }

    private fun setupRecyclerView() {
        binding.personRv.adapter = personAdapter
    }

    private fun observePersons() {
        lifecycleScope.launchWhenResumed {
            viewModel.persons.collectLatest {
                binding.personRv.visibility = View.VISIBLE
//                binding.shimmerLayoutForPerson.visibility = View.INVISIBLE
                personAdapter.personsList = it.persons
            }
        }
    }

    @SuppressLint("CutPasteId")
    private fun openDialogSheet(item: PersonUi) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.alert_item_for_movie_det)
        val movieImage = bottomSheet.findViewById<ImageView>(R.id.profile_picture)
        val movieMore = bottomSheet.findViewById<MaterialButton>(R.id.play_button)
        val movieTitle = bottomSheet.findViewById<TextView>(R.id.name)
        val cancelBtn = bottomSheet.findViewById<ImageView>(R.id.close_btn)
        cancelBtn?.setOnClickListener {
            bottomSheet.dismiss()
        }
        Picasso.get().load(Utils.POSTER_PATH_URL + item.profile_path).into(movieImage)
        movieTitle?.text = item.name
        movieMore?.setOnClickListener {
            findNavController().navigate(ActorsFragmentDirections.actionNavPersonToActorsDetailsFragment(
                item))
            bottomSheet.dismiss()
        }
        bottomSheet.setCancelable(true)
        bottomSheet.show()
    }


    override fun onLongClick(item: PersonUi) {

    }

    override fun onItemClick(item: PersonUi) {
        openDialogSheet(item = item)
    }


}