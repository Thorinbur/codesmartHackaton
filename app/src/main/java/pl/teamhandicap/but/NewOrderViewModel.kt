package pl.teamhandicap.but

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.teamhandicap.but.adapters.Product

class NewOrderViewModel : ViewModel() {
    val products = MutableLiveData(listOf(
        Product("first product", R.drawable.ic_food),
        Product("second product", R.drawable.ic_food),
        Product("third product", R.drawable.ic_food),
        Product("fourth product", R.drawable.ic_food),
        Product("fifth product", R.drawable.ic_food),
    ))

    val cartItems = MutableLiveData<List<String>>(listOf(
        "first item",
        "second item",
        "third item",
    ))
}