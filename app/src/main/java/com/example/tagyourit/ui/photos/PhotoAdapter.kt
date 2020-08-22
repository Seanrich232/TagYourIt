package com.example.tagyourit.ui.photos

<<<<<<< HEAD
import android.annotation.SuppressLint
=======
>>>>>>> master
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
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
=======
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.tagyourit.data.model.Photo
import com.example.tagyourit.databinding.PhotoItemBinding

class PhotoAdapter(private val listener: PhotoItemListener): RecyclerView.Adapter<PhotoViewHolder>() {

    private val photoList = ArrayList<Photo>()

    interface  PhotoItemListener{
        fun onClickedPhoto(photoId: Int?)
    }

    fun setPhotos(photos: ArrayList<Photo> ){
        this.photoList.clear()
        this.photoList.addAll(photos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            PhotoViewHolder{
>>>>>>> master
        val binding: PhotoItemBinding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding, listener)
    }

<<<<<<< HEAD
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}

class PhotoViewHolder(private val itemBinding: PhotoItemBinding, private val listener: PhotoAdapter.PhotoItemListener) : RecyclerView.ViewHolder(itemBinding.root),
=======
    override fun getItemCount(): Int  = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int)= holder.load(photoList[position])

}

class PhotoViewHolder(
    private val vBind: PhotoItemBinding,
    private val listener: PhotoAdapter.PhotoItemListener):
    RecyclerView.ViewHolder(vBind.root),
>>>>>>> master
    View.OnClickListener {

    private lateinit var photo: Photo

    init {
<<<<<<< HEAD
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
=======
        vBind.root.setOnClickListener(this)
    }

    fun load(photo: Photo){
     this.photo = photo
        Glide.with(vBind.root)
            .load(photo.src?.small)
            .into(vBind.image)
            }

    override fun onClick(v: View?) {
        listener.onClickedPhoto(photo.id)
>>>>>>> master
    }
}