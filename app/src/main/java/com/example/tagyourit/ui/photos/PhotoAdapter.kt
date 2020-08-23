package com.example.tagyourit.ui.photos

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.tagyourit.data.model.Photo
import com.example.tagyourit.databinding.PhotoItemBinding
import com.example.tagyourit.utils.TYPE

class PhotoAdapter(private val listener: PhotoItemListener) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val items: MutableList<Photo> = mutableListOf()

    interface PhotoItemListener {
        fun onClickedPhoto(photoId: Int?)
    }

    fun setPhotos(photos: MutableList<Photo>) {
        this.items.clear()
        this.items.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            PhotoViewHolder {
        val binding: PhotoItemBinding =
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size


    class PhotoViewHolder(
        private val vBind: PhotoItemBinding,
        private val listener: PhotoAdapter.PhotoItemListener
    ) :
        RecyclerView.ViewHolder(vBind.root),

        View.OnClickListener {

        private lateinit var photo: Photo

        init {
            vBind.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Photo) {
            this.photo = item
            Glide.with(vBind.root)
                .load(item.url)
                .transform(CircleCrop())
                .into(vBind.image)
        }

        fun load(photo: Photo) {
            this.photo = photo
            Glide.with(vBind.root)
                .load(photo.src?.original)
                .into(vBind.image)
        }

        override fun onClick(v: View?) {
            listener.onClickedPhoto(photo.id)
        }
    }
}
