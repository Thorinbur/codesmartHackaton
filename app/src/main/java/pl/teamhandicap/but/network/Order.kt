package pl.teamhandicap.but.network

data class Order(
    val products: List<Product>?,
    val totalAmount: Double?,
    val status: Status? = null,
    val orderNumber: Int? = null,
    val id: String? = null
)

enum class Status {
    Pending,
    Confirmed,
    Ready,
    Completed
}

data class Product(
    val name: String?,
    val additionalNote: String?
)

data class OrderResponse(
    val id:String?
)