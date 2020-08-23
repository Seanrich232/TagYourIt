package com.example.tagyourit.ui.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tagyourit.databinding.FragmentPhotoSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.tagyourit.utils.Resource
import com.example.tagyourit.utils.extensions.toast

@AndroidEntryPoint
class PhotoSearchFragment : Fragment(), PhotoAdapter.PhotoItemListener {

    private lateinit var binding: FragmentPhotoSearchBinding
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
        setupRecyclerView()
        setupObservers()
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
        viewModel.photos.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
//                    resource.data?.photos?.let { list -> adapter.setPhotos(list as MutableList<Photo>) }
                }
                Resource.Status.ERROR -> {
                    context?.toast("There was an error loading the next page")
                }
            }
        })
    }


}
