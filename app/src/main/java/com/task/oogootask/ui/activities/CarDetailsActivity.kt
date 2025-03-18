package com.task.oogootask.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.gson.Gson
import com.task.oogootask.R
import com.task.oogootask.databinding.ActivityCarDetailsBinding
import com.task.oogootask.ui.adapter.CarImageAdapter
import com.task.oogootask.ui.data.Car
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

class CarDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCarDetailsBinding.inflate(layoutInflater)
    }
    private lateinit var car: Car
    private lateinit var carImageAdapter: CarImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Change battery icon and text color
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        // Get data from intent
        val carJson = intent.getStringExtra("carData")
        car = Gson().fromJson(carJson, Car::class.java)

        initViews()
        setupViewPager()
    }

    private fun setupViewPager() {
        carImageAdapter = CarImageAdapter(car.listOfImages)

        binding.viewpager2.apply {
            adapter = carImageAdapter
            offscreenPageLimit = 1
            isUserInputEnabled = true
            clipToPadding = false
            clipChildren = false
            setPageTransformer(CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
                addTransformer { page, position ->
                    val r = 1 - abs(position)
                    page.scaleY = 0.85f + r * 0.15f
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        Log.d("CarDetails", "Car Name: ${car.carName}")
        Log.d("CarDetails", "Car Details: ${car.carDetails}")
        Log.d("CarDetails", "Car Price: $${car.carPrice}")
        Log.d("CarDetails", "Car Model: ${car.carModel}")
        Log.d("CarDetails", "Car Brand: ${car.carBrand}")
        Log.d("CarDetails", "Post Date: ${SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(
            Date(car.postDate)
        )}")

        // Log list of images
        car.listOfImages.forEachIndexed { index, image ->
            Log.d("CarDetails", "Image $index: $image")
        }

        binding.apply {
            tvCarName.text = car.carName
            binding.carDetails.text = car.carDetails
            binding.carModel.text = "Model: ${car.carModel}"
            binding.carBrand.text = "Brand: ${car.carBrand}"
            binding.carPrice.text = "Price: $${car.carPrice}"
            binding.carKm.text = "Kilometers Driven: ${car.carKm} KM"
            binding.status.text = "Status: ${car.status}"
            binding.postDate.text = "Posted On: ${SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(car.postDate))}"

            backBtn.setOnClickListener {
                finish()
            }
        }
    }

}