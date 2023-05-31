package com.example.imovies.ui.detailitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.imovies.data.utils.autoCleared
import com.example.imovies.ui.ItemViewModel
import com.example.imovies.databinding.DetailItemLayoutBinding

class DetailedItemFragment : Fragment() {

    private  var binding: DetailItemLayoutBinding by autoCleared()
    private val viewModel : ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailItemLayoutBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.chosenItem.observe(viewLifecycleOwner) {
            binding.itemTitle.text = it.title
            binding.itemDesc.text = it.description
            Glide.with(requireContext()).load(it.photo)
                .into(binding.itemImage)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}