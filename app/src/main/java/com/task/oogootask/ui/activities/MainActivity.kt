package com.task.oogootask.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.task.oogootask.databinding.ActivityMainBinding
import com.task.oogootask.di.MyApplication
import com.task.oogootask.interfaces.CarItemClickListener
import com.task.oogootask.ui.adapter.CarAdapter
import com.task.oogootask.ui.data.Car
import com.task.oogootask.utils.PreferenceManager
import com.task.oogootask.viewModel.CarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CarItemClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val carViewModel: CarViewModel by viewModels()
    private lateinit var carAdapter: CarAdapter
    private var carList: List<Car> = emptyList()

    private var backPressedTime: Long = 0
    private val doubleBackPressInterval = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Change battery icon and text color
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setupRecyclerView()
        insertSampleData()
        observeData()
        initViews()
    }

    private fun initViews() {
        binding.apply {
            searchEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    filterList(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun filterList(query: String) {
        val filteredCars = carList.filter { car ->
            car.carName.contains(query, ignoreCase = true) ||
                    car.carBrand.contains(query, ignoreCase = true) ||
                    car.carModel.contains(query, ignoreCase = true)
        }
        carAdapter.updateList(filteredCars)
    }

    private fun observeData() {
        carViewModel.allCars.observe(this) { cars ->
            carList = cars
            carAdapter.updateList(cars)
        }
    }

    private fun setupRecyclerView() {
        carAdapter = CarAdapter(emptyList(), this)
        binding.recyclerViewCars.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = carAdapter
        }
    }

    private fun insertSampleData() {
        val isDataInserted = MyApplication.mInstance.preferenceManager.getBoolean(PreferenceManager.Key.isDataInserted , false)
        if (isDataInserted){
            Log.d("TAG", "insertSampleData: Data Already Inserted")
            checkDataInLog()
        } else {
            carViewModel.insertSampleCars()
            Log.d("TAG", "insertSampleData: Inserted Data...")
            MyApplication.mInstance.preferenceManager.put(PreferenceManager.Key.isDataInserted , true)
            checkDataInLog()
        }
    }

    private fun checkDataInLog() {
        carViewModel.allCars.observe(this) { cars ->
            if (cars.isNotEmpty()) {
                for (car in cars) {
                    Log.d("CarData", "ID: ${car.id}, Name: ${car.carName}, Brand: ${car.carBrand}, Price: ${car.carPrice}, Images: ${car.listOfImages}")
                }
            } else {
                Log.d("CarData", "No cars found in database")
            }
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + doubleBackPressInterval > System.currentTimeMillis()) {
            super.onBackPressed()
            finishAffinity()
            return
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }

        backPressedTime = System.currentTimeMillis()
    }

    override fun onCarClick(car: Car) {
        val intent = Intent(this, CarDetailsActivity::class.java)
        intent.putExtra("carData", Gson().toJson(car))  // Convert object to JSON
        startActivity(intent)
    }
}