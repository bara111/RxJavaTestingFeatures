package com.example.rxjavatestingfeatures.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavatestingfeatures.data.models.toprated.Movie
import com.example.rxjavatestingfeatures.databinding.MovieItemLayoutBinding


class MainAdapter(
    var itemClick: (Movie) -> Unit
) : ListAdapter<Movie, MainAdapter.DetailsViewHolder>(
    WeatherDC()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder.from(
            parent
        ).apply {
            itemView.setOnClickListener {
                itemClick(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class DetailsViewHolder(
        private val binding: MovieItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding) {
                movie = data
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): DetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemLayoutBinding.inflate(layoutInflater, parent, false)
                return DetailsViewHolder(
                    binding
                )
            }
        }
    }

    private class WeatherDC : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem == newItem
    }
}