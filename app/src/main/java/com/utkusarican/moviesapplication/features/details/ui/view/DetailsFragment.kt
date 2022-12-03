package com.utkusarican.moviesapplication.features.details.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentDetailsBinding
import com.utkusarican.moviesapplication.features.details.data.model.MovieDetails
import com.utkusarican.moviesapplication.features.details.ui.adapter.DetailMovieCastAdapter
import com.utkusarican.moviesapplication.features.details.ui.adapter.ViewPagerAdapter
import com.utkusarican.moviesapplication.features.details.ui.model.ViewPagerItem
import com.utkusarican.moviesapplication.features.details.ui.viewmodel.DetailsViewModel
import com.utkusarican.moviesapplication.utils.DEFAULT_LANGUAGE
import com.utkusarican.moviesapplication.utils.HandleUtils
import com.utkusarican.moviesapplication.utils.addImage
import com.utkusarican.moviesapplication.utils.oneDecimal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val args : DetailsFragmentArgs by navArgs()
    private val detailsViewModel : DetailsViewModel by viewModels()
    private val castAdapter : DetailMovieCastAdapter by lazy {
        DetailMovieCastAdapter()
    }

    private var movieId = 0
    private val viewPagerItemList = ArrayList<ViewPagerItem>()

    override fun getLayoutId(): Int = R.layout.fragment_details


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = args.movieId
        getMovieDetails(movieId)
        getMovieCredits(movieId)
        setUpViewModel()
    }

    private fun setUpViewModel(){
        detailsViewModel.getMovieDetailLiveData().observe(viewLifecycleOwner){
            if(it != null){
                setUpView(it)
            }

        }
        detailsViewModel.getMovieCreditsLiveData().observe(viewLifecycleOwner){
            castAdapter.submitList(it)
        }
    }

    private fun setUpView(movieDetails : MovieDetails){
        binding.apply {
            detailPageMovieImage.addImage(movieDetails.image,requireContext())
            detailPageMovieName.text = movieDetails.title
            detailPageMovieScore.text = movieDetails.score.oneDecimal().toString()
            detailPageMovieOverview.text = movieDetails.overview
            detailPageMovieRealeseDate.text = HandleUtils.handleRealseDate(movieDetails.date)
            detailPageMovieGenre.text = HandleUtils.handleGenreTextDetailMovie(movieDetails.detailGenres)
            detailPageMovieCastRecyclerView.apply {
                adapter = castAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
            }
            viewPagerItemList.add(ViewPagerItem(MoreLikeThisFragment(movieId),"More Like This"))
            viewPagerItemList.add(ViewPagerItem(CommentsFragment(),"Comments"))
            val viewPagerAdapter = ViewPagerAdapter(requireActivity(),viewPagerItemList)
            detailPageViewPager.adapter = viewPagerAdapter
            TabLayoutMediator(detailPageTabLayout,detailPageViewPager) { tab, position ->
                tab.text = viewPagerItemList[position].layoutHeadline
            }.attach()


        }
    }

    private fun getMovieDetails(movieId : Int){
        detailsViewModel.getMovieDetails(DEFAULT_LANGUAGE, movieId)
    }

    private fun getMovieCredits(movieId : Int){
        detailsViewModel.getMovieCredits(DEFAULT_LANGUAGE, movieId)
    }


}