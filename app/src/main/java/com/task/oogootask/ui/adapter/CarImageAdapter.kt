package com.task.oogootask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.oogootask.databinding.ItemImageCoursalBinding
import com.task.oogootask.ui.data.CarImages

class CarImageAdapter(private val images: List<CarImages>) :
    RecyclerView.Adapter<CarImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ItemImageCoursalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: CarImages) {
            val imageResId = itemView.context.resources.getIdentifier(
                image.imageName, "drawable", itemView.context.packageName
            )
            binding.ivCarImage.setImageResource(imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageCoursalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}
