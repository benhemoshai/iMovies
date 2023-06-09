package com.example.imovies.ui.choosemovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imovies.R


class MovieAdapter(
    private val movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_layout, parent, false)
        return MovieViewHolder(view)
    }

    data class Movie( val name: String,val imageResource: Int, val year: String)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.item_image)
        private val movieNameTextView: TextView = itemView.findViewById(R.id.item_title)
        private val movieYear: TextView = itemView.findViewById(R.id.item_description)

        fun bind(movie: Movie) {
            movieNameTextView.text = movie.name
            imageView.setImageResource(movie.imageResource)
            movieYear.text = movie.year
        }
    }
}
