package com.utkusarican.moviesapplication.features.home.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.utkusarican.moviesapplication.databinding.BannerMovieItemBinding
import com.utkusarican.moviesapplication.features.home.domain.model.Genre
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.utils.HandleUtils
import com.utkusarican.moviesapplication.utils.addImage

class BannerMoviesAdapter() : PagingDataAdapter<Movie,BannerMoviesAdapter.BannerMovieViewHolder>(BANNER_MOVIE_COMPARATOR) {

    private var genreList : List<Genre> = listOf()

    class BannerMovieViewHolder(private val binding: BannerMovieItemBinding) : ViewHolder(binding.root){
        fun bind(movie: Movie,context: Context,genreList : List<Genre>){
            binding.apply {
                bannerMovieImage.addImage(movie.image,context)
                bannerMovieName.text = movie.name
                bannerMovieGenreText.text = HandleUtils.handleGenreText(genreList,movie.genre_ids)
            }
        }
    }

    override fun onBindViewHolder(holder: BannerMovieViewHolder, position: Int) {
        val context = holder.itemView.context
        val currentMovie = getItem(position)
        currentMovie?.let {
            Log.d("BannerMoviesAdapter",genreList.toString())
            holder.bind(it,context,genreList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerMovieViewHolder {
        val binding = BannerMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BannerMovieViewHolder(binding)
    }

    companion object {
        val BANNER_MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

        }
    }

    fun setGenreList(newGenreList : List<Genre>) {
        this.genreList = newGenreList
    }
}