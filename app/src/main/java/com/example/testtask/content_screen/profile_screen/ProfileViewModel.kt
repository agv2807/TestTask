package com.example.testtask.content_screen.profile_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.R

class ProfileViewModel : ViewModel() {

    val itemsLiveData = MutableLiveData<List<ProfileListItem>>()

    init {
        loadItems()
    }

    private fun loadItems() {
        val items = listOf(
            ProfileListItem(R.drawable.wallet, "Trade store", true),
            ProfileListItem(R.drawable.wallet, "Payment method", true),
            ProfileListItem(R.drawable.wallet, "Balance", false, "$1593"),
            ProfileListItem(R.drawable.wallet, "Trade history", true),
            ProfileListItem(R.drawable.ic_baseline_sync_24, "trade Store", true),
            ProfileListItem(R.drawable.ic_baseline_help_outline_24, "Help", false),
            ProfileListItem(R.drawable.ic_baseline_login_24, "Log out", false),
        )
        itemsLiveData.value = items
    }

}