package com.example.movieappazi.ui.root_fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentRootBinding
import com.example.movieappazi.ui.all_movies_screen.AllMoviesFragment
import com.example.movieappazi.ui.tv_shows_screen.TvShowsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.DelicateCoroutinesApi

class FragmentRoot : Fragment() {

    private val binding: FragmentRootBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentRootBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
        setTabLayoutColor(tabLayout = binding.tabLayout)


    }

    fun Fragment.setTabLayoutColor(tabLayout: TabLayout) {
        when (requireContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                tabLayout.setBackgroundColor(requireContext().getAppThemeColor())
                tabLayout.setTabTextColors(requireContext().getColor(R.color.white),
                    requireContext().getColor(R.color.white))
            }
            Configuration.UI_MODE_NIGHT_YES -> tabLayout.setBackgroundColor(requireContext().getGreyColor())
        }
    }

    fun Context.getAppThemeColor(): Int = this.resources.getColor(R.color.background800)
    fun Context.getGreyColor(): Int = this.resources.getColor(R.color.backGroundAd)

    private fun setupTabLayout() {
        binding.apply {
            viewPager.isSaveEnabled = false
            viewPager.adapter =
                StudentViewPagerAdapter(fm = requireActivity().supportFragmentManager,
                    lifecycle = lifecycle)

            TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                when (pos) {
                    0 -> tab.text = getString(R.string.storage_movies)
                    1 -> tab.text = getString(R.string.storage_persons)
                }
            }.attach()
        }
    }

    private fun View.showView() {
        this.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }
}

class StudentViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    @OptIn(DelicateCoroutinesApi::class)
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllMoviesFragment()
            else -> TvShowsFragment()
        }
    }
}