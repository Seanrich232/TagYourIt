package com.example.tagyourit.ui.photos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.tagyourit.data.model.Photo
import com.example.tagyourit.databinding.PhotoItemBinding

class PhotoAdapter(private val listener: PhotoItemListener) :
    RecyclerView.Adapter<PhotoViewHolder>() {

    interface PhotoItemListener {
        fun onClickedPhoto(photoId: Int)
    }

    private val items = ArrayList<Photo>()

    fun setItems(items: ArrayList<Photo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding: PhotoItemBinding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}

class PhotoViewHolder(private val itemBinding: PhotoItemBinding, private val listener: PhotoAdapter.PhotoItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var photo: Photo

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Photo) {
        this.photo = item
        Glide.with(itemBinding.root)
            .load(item.url)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        photo.id?.let { listener.onClickedPhoto(it) }
    }
}