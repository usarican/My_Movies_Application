package com.utkusarican.moviesapplication.features.home.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.AdapterClickListener
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentHomeBinding
import com.utkusarican.moviesapplication.features.home.data.model.enum.MoviesType
import com.utkusarican.moviesapplication.features.home.ui.adapter.BannerMoviesAdapter
import com.utkusarican.moviesapplication.features.home.ui.adapter.CategoryMoviesAdapter
import com.utkusarican.moviesapplication.features.seeall.data.model.HomeToSeeAllData
import com.utkusarican.moviesapplication.utils.DEFAULT_LANGUAGE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), AdapterClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val homeViewModel : HomeViewModel by viewModels()

    private val bannerMoviesAdapter : BannerMoviesAdapter by lazy { BannerMoviesAdapter(this) }
    private val popularMoviesAdapter : CategoryMoviesAdapter by lazy { CategoryMoviesAdapter(this) }
    private val topRatedMoviesAdapter : CategoryMoviesAdapter by lazy { CategoryMoviesAdapter(this) }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpGenreList()
        setUpRecyclerViews()
        setUpView()
        observeLiveDatas()
    }

    private fun setUpView(){
        binding.apply {
            bannerMovieRecyclerView.apply {
                adapter = bannerMoviesAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
            }
            categoryPopularRecyclerView.apply {
                adapter = popularMoviesAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
            }
            categoryTopRatedRecylerView.apply {
                adapter = topRatedMoviesAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
            }

            categoryPopularSeeAllButton.setOnClickListener {
                val data = HomeToSeeAllData("Popular Movies",MoviesType.POPULAR)
                val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(data)
                findNavController().navigate(action)
            }
            categoryTopRatedSeeAllButton.setOnClickListener {
                val data = HomeToSeeAllData("Top Rated Movies",MoviesType.TOP_RATED)
                val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(data)
                findNavController().navigate(action)
            }
        }
    }

    private fun setUpRecyclerViews() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.apply {
                launch {
                    getNowPlayingMovies(DEFAULT_LANGUAGE).collectLatest { nowPlayingMovies ->
                        bannerMoviesAdapter.submitData(nowPlayingMovies)
                    }
                }
                launch {
                    getPopularMovies(DEFAULT_LANGUAGE).collectLatest { popularMovies ->
                        popularMoviesAdapter.submitData(popularMovies)
                    }
                }
                launch {
                    getTopRatedMovies(DEFAULT_LANGUAGE).collectLatest { topRatedMovies ->
                        topRatedMoviesAdapter.submitData(topRatedMovies)
                    }
                }
            }
        }
    }

    private fun setUpGenreList(){
        homeViewModel.getGenreList(DEFAULT_LANGUAGE)
    }

    private fun observeLiveDatas(){
        homeViewModel.apply {
            getGenresLiveData().observe(viewLifecycleOwner){ listGenre ->
                bannerMoviesAdapter.setGenreList(listGenre)
                popularMoviesAdapter.setUpGenreList(listGenre)
                topRatedMoviesAdapter.setUpGenreList(listGenre)
            }
        }
    }

    override fun setOnClickListener(movieId: Int) {
        Log.d("HOMEFRAGMENT",movieId.toString())
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId)
        findNavController().navigate(action)
    }
}