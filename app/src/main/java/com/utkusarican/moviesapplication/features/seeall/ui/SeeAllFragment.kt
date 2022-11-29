package com.utkusarican.moviesapplication.features.seeall.ui

import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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
import com.utkusarican.moviesapplication.features.seeall.data.model.HomeToSeeAllData
import com.utkusarican.moviesapplication.utils.DEFAULT_LANGUAGE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>() , AdapterClickListener{

    override fun getLayoutId(): Int = R.layout.fragment_see_all
    private lateinit var data : HomeToSeeAllData
    private var searchButtonClicked = false

    private val seeAllMoviesAdapter : SeeAllMoviesAdapter by lazy {
        SeeAllMoviesAdapter(this)
    }
    private val seeAllViewModel : SeeAllViewModel by viewModels()
    private val args : SeeAllFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = args.seeAllData
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
            seeAllSearchButton.setOnClickListener { view ->
                if(searchButtonClicked){
                    view.setBackgroundResource(R.drawable.ic_search)
                    this.seeAllPageName.visibility = View.VISIBLE
                    this.seeAllPageEditText.visibility = View.GONE
                    setUpViewModel(data.moviesType)
                    searchButtonClicked = !searchButtonClicked
                }
                else {
                    view.setBackgroundResource(R.drawable.ic_cancel)
                    this.seeAllPageName.visibility = View.GONE
                    this.seeAllPageEditText.visibility = View.VISIBLE
                    observeLiveData()
                    searchButtonClicked = !searchButtonClicked

                }
            }

            seeAllPageEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(!(p0.toString().isEmpty() || p0.toString() == "")){
                        seeAllViewModel.changeSearchQuery(p0.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}

            })

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

    private fun observeLiveData(){
        seeAllViewModel.searchMovie(DEFAULT_LANGUAGE).observe(viewLifecycleOwner){
            seeAllMoviesAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }


    override fun setOnClickListener(movieId: Int) {
        TODO("Not yet implemented")
    }


}