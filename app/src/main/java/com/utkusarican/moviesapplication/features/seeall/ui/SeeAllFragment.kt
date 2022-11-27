package com.utkusarican.moviesapplication.features.seeall.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.utkusarican.moviesapplication.R
import com.utkusarican.moviesapplication.core.ui.AdapterClickListener
import com.utkusarican.moviesapplication.core.ui.BaseFragment
import com.utkusarican.moviesapplication.databinding.FragmentSeeAllBinding
import com.utkusarican.moviesapplication.features.home.data.model.enum.MoviesType
import com.utkusarican.moviesapplication.utils.DEFAULT_LANGUAGE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>() , AdapterClickListener{

    override fun getLayoutId(): Int = R.layout.fragment_see_all

    private val seeAllMoviesAdapter : SeeAllMoviesAdapter by lazy {
        SeeAllMoviesAdapter(this)
    }
    private val seeAllViewModel : SeeAllViewModel by viewModels()
    private val args : SeeAllFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = args.seeAllData
        setUpViewModel(data.moviesType)
        setUpView()
    }

    private fun setUpView(){
        binding.apply {
            seeAllRecyclerView.apply {
                adapter = seeAllMoviesAdapter
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
            }
            seeAllBackButton.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }

    private fun setUpViewModel(moviesType: MoviesType) {
        viewLifecycleOwner.lifecycleScope.launch {
            seeAllViewModel.apply {
                when (moviesType) {
                 MoviesType.POPULAR -> getPopularMovies(DEFAULT_LANGUAGE).collectLatest { popularMovies ->
                        seeAllMoviesAdapter.submitData(
                            popularMovies
                        )
                    }
                    MoviesType.TOP_RATED -> getTopRatedMovies(DEFAULT_LANGUAGE).collectLatest { topRatedMovies ->
                        seeAllMoviesAdapter.submitData(
                            topRatedMovies
                        )
                    }
                    MoviesType.NOW_PLAYING -> Unit
                }
            }
        }
    }

    override fun setOnClickListener(movieId: Int) {
        TODO("Not yet implemented")
    }


}