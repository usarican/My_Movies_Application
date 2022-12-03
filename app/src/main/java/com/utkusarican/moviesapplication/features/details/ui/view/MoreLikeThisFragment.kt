package com.utkusarican.moviesapplication.features.details.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.AdapterClickListener
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentMoreLikeThisBinding
import com.utkusarican.moviesapplication.features.details.ui.adapter.SimilarMoviePagingAdapter
import com.utkusarican.moviesapplication.features.details.ui.viewmodel.DetailsViewModel
import com.utkusarican.moviesapplication.features.details.ui.viewmodel.MoreLikeThisViewModel
import com.utkusarican.moviesapplication.utils.DEFAULT_LANGUAGE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreLikeThisFragment(private val movieId : Int) : BaseFragment<FragmentMoreLikeThisBinding>() , AdapterClickListener{
    override fun getLayoutId(): Int = R.layout.fragment_more_like_this

    private val viewModel : MoreLikeThisViewModel by viewModels()
    private val similarMoviePagingAdapter : SimilarMoviePagingAdapter by lazy {
        SimilarMoviePagingAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MoreLikeThisFragment",movieId.toString())
        setUpViewModel(movieId)
        setUpView()
    }

    override fun setOnClickListener(movieId: Int) {
        // TODO: Movie details should be change when i click item but it is in the same fragment
    }

    private fun setUpViewModel(movieId: Int){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getSimilarMovies(DEFAULT_LANGUAGE,movieId).collectLatest {
                similarMoviePagingAdapter.submitData(it)
            }
        }
    }

    private fun setUpView(){
        binding.apply {
            with(similarMoviesRecyclerView){
                layoutManager = GridLayoutManager(context,2)
                adapter = similarMoviePagingAdapter
                setHasFixedSize(true)
            }
        }
    }


}