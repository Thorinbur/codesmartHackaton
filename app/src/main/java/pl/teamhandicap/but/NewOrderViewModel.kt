package pl.teamhandicap.but

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewOrderViewModel : ViewModel() {
    val cartItems = MutableLiveData<List<String>>(listOf(
        "first item",
        "second item",
        "third item",
    ))
}