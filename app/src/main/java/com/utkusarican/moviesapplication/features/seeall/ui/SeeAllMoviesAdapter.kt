package com.utkusarican.moviesapplication.features.seeall.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.utkusarican.moviesapplication.core.ui.AdapterClickListener
import com.utkusarican.moviesapplication.databinding.SeeAllMovieItemBinding
import com.utkusarican.moviesapplication.features.home.domain.model.Movie
import com.utkusarican.moviesapplication.utils.HandleUtils
import com.utkusarican.moviesapplication.utils.addImage

class SeeAllMoviesAdapter(private val onItemClickListener: AdapterClickListener) : PagingDataAdapter<Movie,SeeAllMoviesAdapter.SeeAllMoviesViewHolder>(
    HandleUtils.diffUtilObject
) {

    inner class SeeAllMoviesViewHolder(private val binding : SeeAllMovieItemBinding) : ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val currentItem = getItem(position)
                    currentItem?.let {
                        onItemClickListener.setOnClickListener(currentItem.id)
                    }
                }
            }
        }

        fun bind(movie: Movie,context : Context){
            binding.apply {
                movieItemImage.addImage(movie.image,context)
                movieItemScore.text = movie.score.toString()

            }
        }
    }

    override fun onBindViewHolder(holder: SeeAllMoviesViewHolder, position: Int) {
        val context = holder.itemView.context
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it,context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllMoviesViewHolder {
        val binding = SeeAllMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SeeAllMoviesViewHolder(binding)
    }


}