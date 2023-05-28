package com.example.imovies.ui.choosemovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imovies.R

class chooseMovieFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val movieList = listOf(
        MovieAdapter.Movie(R.drawable.movie1, "Movie 1"),
        MovieAdapter.Movie(R.drawable.movie2, "Movie 2"),
        MovieAdapter.Movie(R.drawable.movie3, "Movie 3"),
        // Add more movie resources as needed
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_choose_movie, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = MovieAdapter(movieList) { movie ->
            showMovieDetails(movie)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }



    private fun showMovieDetails(movie: MovieAdapter.Movie) {
        val movieName = movie.name

        // Pass the movie name to the previous screen
        parentFragmentManager.setFragmentResult("movieDetailsRequestKey", bundleOf("movieName" to movieName))

        // Go back to the previous screen
        requireActivity().onBackPressed()
    }
}
