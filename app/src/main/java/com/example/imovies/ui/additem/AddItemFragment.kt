package com.example.imovies.ui.additem

import MovieViewModel
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imovies.data.model.Item
import com.example.imovies.ui.ItemViewModel
import com.example.imovies.R
import com.example.imovies.databinding.AddItemLayoutBinding
import java.lang.Thread.sleep
import java.util.*

class AddItemFragment : Fragment() {

    private val viewModel : ItemViewModel by activityViewModels()
    private val movieviewmodel : MovieViewModel by activityViewModels()

    private var _binding : AddItemLayoutBinding?  = null
    private val binding get() = _binding!!

   private var imgeUri : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddItemLayoutBinding.inflate(inflater, container, false)

        movieviewmodel.movieName.observe(viewLifecycleOwner) { movieName ->
            binding.MovieName.text = movieName.toString()
            val drawableResId = getDrawableResourceIdForMovie(movieName)
            if (drawableResId != 0) {
                binding.resultImage.setImageResource(drawableResId)
                imgeUri =
                    Uri.parse("android.resource://${requireContext().packageName}/${drawableResId}")
            }
        }

        binding.finishBtn.setOnClickListener {
            if (binding.MovieName.text.toString() == "Not yet selected") {
                Toast.makeText(requireContext(), "Pick a movie", Toast.LENGTH_LONG).show()
            } else {
                movieviewmodel.movieName.observe(viewLifecycleOwner) {
                    binding.MovieName.text = it
                }

                val item = Item(
                    binding.MovieName.text.toString(),
                    binding.itemDescription.text.toString(),
                    imgeUri.toString()
                )
                viewModel.addItem(item)
                findNavController().navigate(R.id.action_addItemFragment_to_allItemsFragment)
                sleep(1000)
                movieviewmodel.setResultImage(0)
                movieviewmodel.setMovieName("Not yet selected")
            }
        }
            binding.movieButton.setOnClickListener {
                findNavController().navigate(R.id.action_addItemFragment_to_chooseMovieFragment)
            }

            return binding.root
        }


    private fun getDrawableResourceIdForMovie(movieName: String?): Int {
        return when (movieName) {
            "Movie 1" -> R.drawable.movie1
            "Movie 2" -> R.drawable.movie2
            "Movie 3" -> R.drawable.movie3
            // Add more cases for other movie names and their corresponding drawable resources
            else -> 0 // Return 0 if no matching resource is found
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    }
