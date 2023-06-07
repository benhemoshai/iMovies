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
    private val movieList : List<MovieAdapter.Movie> by lazy {
        listOf(
            MovieAdapter.Movie(getString(R.string.movie_name_1), R.drawable.movie1),
            MovieAdapter.Movie(getString(R.string.movie_name_2), R.drawable.movie2),
            MovieAdapter.Movie(getString(R.string.movie_name_3), R.drawable.movie3),
            MovieAdapter.Movie(getString(R.string.movie_name_4), R.drawable.movie4),
            MovieAdapter.Movie(getString(R.string.movie_name_5), R.drawable.movie5),
            MovieAdapter.Movie(getString(R.string.movie_name_6), R.drawable.movie6),
            MovieAdapter.Movie(getString(R.string.movie_name_7), R.drawable.movie7),
            MovieAdapter.Movie(getString(R.string.movie_name_8), R.drawable.movie8),
            MovieAdapter.Movie(getString(R.string.movie_name_9), R.drawable.movie9),
            MovieAdapter.Movie(getString(R.string.movie_name_10), R.drawable.movie10)
        )



    }

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
