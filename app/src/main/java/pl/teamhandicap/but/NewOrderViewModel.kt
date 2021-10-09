package pl.teamhandicap.but

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize

class NewOrderViewModel : ViewModel() {
    val products get() = ProductListProvider.get()

    val cartItems = MutableLiveData<List<CartItem>>(emptyList())

    fun removeItemFromCart(item: CartItem) {
        cartItems.value?.takeIf { !it.isNullOrEmpty() }?.toMutableList()?.let {
            it.remove(item)
            cartItems.postValue(it)
        }
    }

    fun addProductToOrder(id: Int, details: List<Detail>) {
        val cart = cartItems.value!!.toMutableList()
        val product = ProductListProvider.get().find { it.id == id } ?: return
        cart.add(CartItem(product, details))
        cartItems.postValue(cart)
    }
}

@Parcelize
data class CartItem(
    val product: Product,
    val details: List<Detail>,
) : Parcelable