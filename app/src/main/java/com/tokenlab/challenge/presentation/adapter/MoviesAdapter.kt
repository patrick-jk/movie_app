package com.tokenlab.challenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tokenlab.challenge.R
import com.tokenlab.challenge.databinding.ItemMovieBinding
import com.tokenlab.challenge.domain.model.Movie

class MoviesAdapter(private val onItemClicked: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieTitle.text = movie.title
            binding.tvRating.text = movie.voteAverage.toString()
            binding.tvGenres.text = if (movie.genres.isNotEmpty()) movie.genres[0] else ""

            val errorIcon = R.drawable.ic_movie
            Picasso.get()
                .load(movie.posterUrl)
                .placeholder(errorIcon)
                .error(errorIcon)
                .into(binding.ivMovieIcon)

            binding.root.setOnClickListener {
                onItemClicked(movie)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}