package pl.teamhandicap.but

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewOrderViewModel : ViewModel() {
    val products get() = ProductListProvider.get()

    val cartItems = MutableLiveData<List<String>>(listOf(
        "first item",
        "second item",
        "third item",
    ))
}