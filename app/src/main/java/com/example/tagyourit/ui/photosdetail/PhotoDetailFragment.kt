package com.example.tagyourit.ui.photosdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.utils.autoCleared
import com.example.tagyourit.R
import com.example.tagyourit.data.model.Photo
import com.example.tagyourit.databinding.FragmentPhotoDetailBinding
import com.example.tagyourit.utils.Resource
import com.example.tagyourit.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {

    private var binding: FragmentPhotoDetailBinding by autoCleared()
    private val viewModel: PhotoDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.photo.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    bindPhoto(resource.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.photoCl.visibility = View.VISIBLE

                }
                Resource.Status.ERROR -> {
                    context?.toast(resource.message)
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.photoCl.visibility = View.GONE
                }

            }
        })
    }

    private fun bindPhoto(photo: Photo) {
        binding.photographer.text = photo.photographer
        binding.liked.text = photo.liked.toString()
        Glide.with(binding.root)
            .load(photo.src?.medium)
            .transform(CircleCrop())
            .into(binding.IvPhoto)
    }

}