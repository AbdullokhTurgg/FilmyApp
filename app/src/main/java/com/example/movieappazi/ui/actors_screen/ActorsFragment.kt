package com.example.movieappazi.ui.actors_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.data.network.retrofit.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentActorsBinding
import com.example.movieappazi.extensions.showView
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.zAdapter.person.PersonAdapter
import com.example.movieappazi.uiModels.person.PersonUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ActorsFragment :
    BaseFragment<FragmentActorsBinding, ActorsFragmentViewModel>(FragmentActorsBinding::inflate),
    RvClickListener<PersonUi> {

    override val viewModel: ActorsFragmentViewModel by viewModels()
    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observePersons()
    }

    private fun setupRecyclerView() = with(requireBinding()) {
        personRv.adapter = personAdapter
    }

    private fun observePersons() = with(requireBinding()) {
        lifecycleScope.launchWhenResumed {
            viewModel.persons.collectLatest {
                shimmerLayout.visibility = View.INVISIBLE
                personRv.visibility = View.VISIBLE
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
            viewModel.goPersonDetails(person = item)
            bottomSheet.dismiss()
        }
        bottomSheet.setCancelable(true)
        bottomSheet.show()
    }

    override fun onLongClick(item: PersonUi) {}

    override fun onItemClick(item: PersonUi) {
        openDialogSheet(item = item)
    }

    override fun onReady(savedInstanceState: Bundle?) {}


}