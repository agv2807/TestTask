package com.example.testtask.content_screen.home_screen

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.R

class HomeViewModel : ViewModel() {

    val categoriesLiveData = MutableLiveData<List<Category>>()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        val list = listOf(
            Category(R.drawable.telephones, "Phones"),
            Category(R.drawable.headphones, "Headphones"),
            Category(R.drawable.games, "Games"),
            Category(R.drawable.cars, "Cars"),
            Category(R.drawable.furniture, "Furniture"),
            Category(R.drawable.kids, "Kids"),
            )
        categoriesLiveData.value = list
    }
}