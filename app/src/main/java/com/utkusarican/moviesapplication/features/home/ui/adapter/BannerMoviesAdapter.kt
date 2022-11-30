package com.utkusarican.moviesapplication.features.home.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.utkusarican.moviesapplication.core.ui.AdapterClickListener
import com.utkusarican.moviesapplication.databinding.BannerMovieItemBinding
import com.utkusarican.moviesapplication.features.home.domain.model.Genre
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.utils.HandleUtils
import com.utkusarican.moviesapplication.utils.addImage

class BannerMoviesAdapter(private val onClickListener : AdapterClickListener) : PagingDataAdapter<Movie,BannerMoviesAdapter.BannerMovieViewHolder>(BANNER_MOVIE_COMPARATOR) {

    private var genreList : List<Genre> = listOf()

    inner class BannerMovieViewHolder(private val binding: BannerMovieItemBinding) : ViewHolder(binding.root){
        fun bind(movie: Movie,context: Context,genreList : List<Genre>){
            binding.apply {
                bannerMovieImage.addImage(movie.image,context)
                bannerMovieName.text = movie.name
                bannerMovieGenreText.text = HandleUtils.handleGenreText(genreList,movie.genre_ids)

                banmerMovieAddMylistButton.setOnClickListener {
                    Toast.makeText(context,"${movie.name} My List'e Eklendi",Toast.LENGTH_SHORT).show()
                }
                banmerMovieInfoButton.setOnClickListener {
                    onClickListener.setOnClickListener(movie.id)
                }
                banmerMoviePlayButton.setOnClickListener {
                    Toast.makeText(context,"${movie.name} Play Tıklandı.",Toast.LENGTH_SHORT).show()
                }
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