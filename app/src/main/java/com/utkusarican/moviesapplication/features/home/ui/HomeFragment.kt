package com.utkusarican.moviesapplication.features.home.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentHomeBinding
import com.utkusarican.moviesapplication.features.home.domain.model.Category
import com.utkusarican.moviesapplication.features.home.ui.adapter.BannerMoviesAdapter
import com.utkusarican.moviesapplication.features.home.ui.adapter.CategoryMoviesAdapter
import com.utkusarican.moviesapplication.utils.DEFAULT_LANGUAGE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val homeViewModel : HomeViewModel by viewModels()

    private val bannerMoviesAdapter : BannerMoviesAdapter by lazy { BannerMoviesAdapter() }
    private val popularMoviesAdapter : CategoryMoviesAdapter by lazy { CategoryMoviesAdapter() }
    private val topRatedMoviesAdapter : CategoryMoviesAdapter by lazy { CategoryMoviesAdapter() }


    private val categoryList : ArrayList<Category> = arrayListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpView()
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
        }
    }

    private fun setUpViewModel() {
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


}