package pl.teamhandicap.but.network

data class Order(
    val products: List<Product>?,
    val totalAmount: Double?,
    val status: Status? = null
)

enum class Status {
    Pending,
    Confirmed,
    Ready
}

data class Product(
    val name: String?,
    val additionalNote: String?
)

data class OrderResponse(
    val id:String?
)