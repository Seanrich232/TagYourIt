package com.example.tagyourit.ui.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.utils.autoCleared
import com.example.tagyourit.R
import com.example.tagyourit.databinding.FragmentPhotoSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.tagyourit.utils.Resource
import com.example.tagyourit.utils.extensions.toast
import timber.log.Timber

@AndroidEntryPoint
class PhotoSearchFragment : Fragment(), PhotoAdapter.PhotoItemListener {

    private var binding: FragmentPhotoSearchBinding by autoCleared()
    private val viewModel: PhotoViewModel by viewModels()
    private lateinit var adapter: PhotoAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = PhotoAdapter(this)
        binding.rvPhoto.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPhoto.adapter = adapter
    }

    override fun onClickedPhoto(photoId: Int?) {
        adapter = PhotoAdapter(this)
        binding.rvPhoto.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPhoto.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.photos.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) adapter.setPhotos(ArrayList(it.data))
                }
                Resource.Status.ERROR -> {
                    context?.toast(it.message)
                }
            }
        })
    }


}
