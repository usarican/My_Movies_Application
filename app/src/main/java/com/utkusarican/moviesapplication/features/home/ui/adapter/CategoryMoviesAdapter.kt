package com.utkusarican.moviesapplication.features.home.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.utkusarican.moviesapplication.databinding.CategoryMovieItemBinding
import com.utkusarican.moviesapplication.features.home.domain.model.Genre
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.utils.HandleUtils
import com.utkusarican.moviesapplication.utils.addImage

class CategoryMoviesAdapter : PagingDataAdapter<Movie,CategoryMoviesAdapter.CategoryMoviesViewHolder>(
    COMPARE_MOVIE_ITEM) {

    private var genreList : List<Genre> = emptyList()

    class CategoryMoviesViewHolder(private val binding : CategoryMovieItemBinding) : ViewHolder(binding.root){
        fun bind(movie: Movie ,context : Context ,genreList : List<Genre>) {
            binding.apply {
                movieItemImage.addImage(movie.image,context)
                movieItemName.text = movie.name
                movieItemScore.text = movie.score.toString()
                movieItemRelasedate.text = HandleUtils.handleRealseDate(movie.date)
                movieItemGenre.text = HandleUtils.handleGenreOneText(genreList,movie.genre_ids)
            }
        }
    }

    companion object {
        val COMPARE_MOVIE_ITEM = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onBindViewHolder(holder: CategoryMoviesViewHolder, position: Int) {
        val currentItem = getItem(position)
        val context = holder.itemView.context
        currentItem?.let {
            holder.bind(it,context,genreList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMoviesViewHolder {
        val binding = CategoryMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryMoviesViewHolder(binding)
    }

    fun setUpGenreList(newGenreList : List<Genre>) {
        this.genreList = newGenreList
    }
}