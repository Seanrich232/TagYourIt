package com.example.tagyourit.ui.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tagyourit.databinding.FragmentPhotoSearchBinding
import com.example.tagyourit.databinding.FragmentPhotoSearchBinding.*

class PhotoSearchFragment : Fragment(), PhotoAdapter.PhotoItemListener {

    private lateinit var binding: FragmentPhotoSearchBinding
    private val viewModel: PhotoViewModel by viewModels()
    private lateinit var adapter: PhotoAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = PhotoAdapter(this)

    }

    private fun setupObservers() {

    }

    override fun onClickedPhoto(photoId: Int?) {
        TODO("Not yet implemented")
    }


}