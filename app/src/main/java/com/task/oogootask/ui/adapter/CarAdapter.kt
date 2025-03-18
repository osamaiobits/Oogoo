package com.task.oogootask.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.oogootask.databinding.ItemCarBinding
import com.task.oogootask.interfaces.CarItemClickListener
import com.task.oogootask.ui.data.Car

class CarAdapter(
    private var cars: List<Car>,
    private val listener: CarItemClickListener
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(car: Car) {
            binding.apply {
                tvCarName.text = car.carName
                tvCarDetails.text = car.carDetails
                tvCarPrice.text = "Price: $${car.carPrice}"
                tvCarModel.text = "Model: ${car.carModel}"
                tvCarBrand.text = "Brand: ${car.carBrand}"
                tvCarDate.text = "Posted: ${convertMillisToDate(car.postDate)}"

                // Load the first image (from drawable)
                val context = ivCarImage.context
                val imageResId = context.resources.getIdentifier(
                    car.listOfImages.firstOrNull()?.imageName ?: "ic_launcher",
                    "drawable",
                    context.packageName
                )
                ivCarImage.setImageResource(imageResId)

                root.setOnClickListener {
                    listener.onCarClick(car)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount(): Int = cars.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newCars: List<Car>) {
        cars = newCars
        notifyDataSetChanged()
    }

    private fun convertMillisToDate(millis: Long): String {
        val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(millis))
    }
}