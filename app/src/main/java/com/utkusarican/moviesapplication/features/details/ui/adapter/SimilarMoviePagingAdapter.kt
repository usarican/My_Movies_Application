package com.utkusarican.moviesapplication.features.details.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.utkusarican.moviesapplication.core.ui.AdapterClickListener
import com.utkusarican.moviesapplication.databinding.SeeAllMovieItemBinding
import com.utkusarican.moviesapplication.features.details.data.model.SimilarMovieResponse
import com.utkusarican.moviesapplication.utils.addImage
import com.utkusarican.moviesapplication.utils.oneDecimal

class SimilarMoviePagingAdapter(private val onItemClickListener: AdapterClickListener) : PagingDataAdapter<SimilarMovieResponse.SimilarMovie, SimilarMoviePagingAdapter.SimilarMoviesViewHolder>(
COMPARE_SIMILAR_MOVIES ) {

    inner class SimilarMoviesViewHolder(private val binding : SeeAllMovieItemBinding) : RecyclerView.ViewHolder(binding.root){
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

        fun bind(movie: SimilarMovieResponse.SimilarMovie, context : Context){
            binding.apply {
                movieItemImage.addImage(movie.image,context)
                movieItemScore.text = movie.score.oneDecimal().toString()

            }
        }
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        val context = holder.itemView.context
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(currentItem,context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        val binding = SeeAllMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimilarMoviesViewHolder(binding)
    }


    companion object {
        val COMPARE_SIMILAR_MOVIES = object : DiffUtil.ItemCallback<SimilarMovieResponse.SimilarMovie>(){
            override fun areItemsTheSame(
                oldItem: SimilarMovieResponse.SimilarMovie,
                newItem: SimilarMovieResponse.SimilarMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SimilarMovieResponse.SimilarMovie,
                newItem: SimilarMovieResponse.SimilarMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}