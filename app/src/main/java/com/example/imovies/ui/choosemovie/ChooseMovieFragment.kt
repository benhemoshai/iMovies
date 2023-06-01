package com.example.imovies.ui.choosemovie

import MovieViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imovies.R

class ChooseMovieFragment : Fragment() {

    private val sharedviewmodel : MovieViewModel by activityViewModels()

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
        sharedviewmodel.setMovieName(movie.name)
        sharedviewmodel.setResultImage(movie.imageResource)
        // Go back to the previous screen
        requireActivity().onBackPressed()
    }
}
