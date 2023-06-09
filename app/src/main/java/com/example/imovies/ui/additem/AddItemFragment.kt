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
import java.util.Calendar
import java.util.Date
import com.example.imovies.databinding.AddItemLayoutBinding
import java.lang.Thread.sleep
import java.text.SimpleDateFormat
import java.util.*

class AddItemFragment : Fragment() {

    private val viewModel : ItemViewModel by activityViewModels()
    private val movieviewmodel : MovieViewModel by activityViewModels()
    private var selectedDate: Date? = null
    private var _binding : AddItemLayoutBinding?  = null
    private val binding get() = _binding!!

    private var imgeUri : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddItemLayoutBinding.inflate(inflater, container, false)


        //observes changes in the movie name, gets the imageID and parses it to URI
        movieviewmodel.movieName.observe(viewLifecycleOwner) { movieName ->
            binding.MovieName.text = movieName.toString()
            val drawableResId = getDrawableResourceIdForMovie(movieName)
            if (drawableResId != 0) {
                binding.resultImage.setImageResource(drawableResId)
                imgeUri =
                    Uri.parse("android.resource://${requireContext().packageName}/${drawableResId}")
            }
        }

        //observes the changes in the date
        movieviewmodel.date.observe(viewLifecycleOwner){ date->
            binding.date.text = date
        }

        //when the user clicks on finish button - checks if he selected a date and a movie
        //if not, it shows a toast message. if yes it creates an item which includes
        //a movie name,description, date and an image.

        binding.finishBtn.setOnClickListener {
            if (binding.MovieName.text.toString() == getString(R.string.movie_list_button)) {
                Toast.makeText(requireContext(), getString(R.string.movie_toast), Toast.LENGTH_LONG).show()
            } else if (binding.date.text.toString() == getString(R.string.date_button)) {
                Toast.makeText(requireContext(), getString(R.string.date_toast), Toast.LENGTH_LONG).show()
            } else {
                movieviewmodel.movieName.observe(viewLifecycleOwner) {
                    binding.MovieName.text = it
                }

                val item = Item(
                    binding.MovieName.text.toString(),
                    binding.itemDescription.text.toString(),
                    binding.date.text.toString(),
                    imgeUri.toString()
                )
                viewModel.addItem(item)
                findNavController().navigate(R.id.action_addItemFragment_to_allItemsFragment)
                movieviewmodel.setResultImage(0)
                movieviewmodel.setDate(getString(R.string.date_button))
                movieviewmodel.setMovieName(getString(R.string.movie_list_button))
            }
        }
        //when the user clicks on the date button - opens a date dialog
        binding.date.setOnClickListener {
            showDatePicker()
        }

        //when the user clicks on "pick a movie" button - moves to the movie list fragment
        binding.movieButton.setOnClickListener {
            findNavController().navigate(R.id.action_addItemFragment_to_chooseMovieFragment)
        }

        return binding.root

    }

    //a function that gets a movie name and returns its imageID
    private fun getDrawableResourceIdForMovie(movieName: String?): Int {
        return when (movieName) {
            getString(R.string.movie_name_1) -> R.drawable.movie1
            getString(R.string.movie_name_2) -> R.drawable.movie2
            getString(R.string.movie_name_3)-> R.drawable.movie3
            getString(R.string.movie_name_4) -> R.drawable.movie4
            getString(R.string.movie_name_5) -> R.drawable.movie5
            getString(R.string.movie_name_6) -> R.drawable.movie6
            getString(R.string.movie_name_7) -> R.drawable.movie7
            getString(R.string.movie_name_8) -> R.drawable.movie8
            getString(R.string.movie_name_9)-> R.drawable.movie9
            getString(R.string.movie_name_10)-> R.drawable.movie10
            else -> 0 // Return 0 if no matching resource is found
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, day)
                selectedDate = selectedCalendar.time

                updateSelectedDateView()
            },
            currentYear,
            currentMonth,
            currentDay
        )

        // Disable past dates
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000

        datePickerDialog.show()
    }
    private fun updateSelectedDateView() {
        if (selectedDate != null) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate!!)
            binding.date.text = formattedDate
            movieviewmodel.setDate(binding.date.text.toString())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
