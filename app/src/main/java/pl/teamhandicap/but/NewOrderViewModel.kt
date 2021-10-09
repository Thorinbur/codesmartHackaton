package pl.teamhandicap.but

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewOrderViewModel : ViewModel() {
    val products get() = ProductListProvider.get()

    val cartItems = MutableLiveData<List<Product>>(emptyList())

    fun addProductToOrder(id: Int) {
        val cart = cartItems.value!!.toMutableList()
        val product = ProductListProvider.get().find { it.id == id } ?: return
        cart.add(product)
        cartItems.postValue(cart)
    }
}