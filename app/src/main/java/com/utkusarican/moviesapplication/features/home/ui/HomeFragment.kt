package com.utkusarican.moviesapplication.features.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentHomeBinding
import com.utkusarican.moviesapplication.features.home.ui.adapter.BannerMoviesAdapter
import com.utkusarican.moviesapplication.utils.DEFAULT_LANGUAGE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val homeViewModel : HomeViewModel by viewModels()

    private val bannerMoviesAdapter = BannerMoviesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    private fun setUpView(){
        binding.apply {
            bannerMovieRecyclerView.apply {
                adapter = bannerMoviesAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun setUpViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.apply {
                        getNowPlayingMovies(DEFAULT_LANGUAGE).collectLatest { pagingData ->
                            bannerMoviesAdapter.submitData(pagingData)
                        }
                    }
                }
            }
        }

    }


}