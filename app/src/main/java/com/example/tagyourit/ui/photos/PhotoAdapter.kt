package com.example.tagyourit.ui.photos


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tagyourit.data.model.Photo
import com.example.tagyourit.databinding.PhotoItemBinding


class PhotoAdapter(private val listener: PhotoItemListener) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    interface PhotoItemListener {
        fun onClickedPhoto(photoId: Int?)
    }

    private val photoList = ArrayList<Photo>()

    fun setPhoto(photoList: ArrayList<Photo>) {
        this.photoList.clear()
        this.photoList.addAll(photoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding: PhotoItemBinding =
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.load(photoList[position])

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

        fun load(photo: Photo) {
            this.photo = photo
            Glide.with(vBind.root)
                .load(photo.src?.small)
                .into(vBind.image)
        }

        override fun onClick(v: View?) {
            listener.onClickedPhoto(photo.id)
            vBind.root.setOnClickListener(this)
        }
    }
}


