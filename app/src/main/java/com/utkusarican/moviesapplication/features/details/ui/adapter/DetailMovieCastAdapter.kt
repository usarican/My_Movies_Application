package com.utkusarican.moviesapplication.features.details.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.utkusarican.moviesapplication.databinding.DetailMovieCastItemBinding
import com.utkusarican.moviesapplication.features.details.data.model.MovieCredits
import com.utkusarican.moviesapplication.features.details.domain.model.MovieCast
import com.utkusarican.moviesapplication.utils.addImage

class DetailMovieCastAdapter : ListAdapter<MovieCast,DetailMovieCastAdapter.MovieCastViewHolder>(COMPARE_CAST) {
    class MovieCastViewHolder(private val binding : DetailMovieCastItemBinding) : ViewHolder(binding.root){
        fun bind(cast : MovieCast,context: Context){
            binding.apply {
                castItemImage.addImage(cast.profileImage,context)
                castItemRealName.text = cast.name
                castItemMovieName.text = cast.characterName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val binding = DetailMovieCastItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        val context = holder.itemView.context
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(currentItem,context)
        }
    }

    companion object {
        val COMPARE_CAST = object : DiffUtil.ItemCallback<MovieCast>(){
            override fun areItemsTheSame(
                oldItem: MovieCast,
                newItem: MovieCast
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: MovieCast,
                newItem: MovieCast
            ): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }
}