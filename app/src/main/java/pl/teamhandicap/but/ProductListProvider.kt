package pl.teamhandicap.but

object ProductListProvider {
    fun get() = listOf(
        Product(
            id = 1,
            nameRes = R.string.coffee,
            iconRes = R.drawable.ic_cafe,
            price = 3.0,
            details = listOf(Detail.SUGAR),
        ),
        Product(
            id = 2,
            nameRes = R.string.tea,
            iconRes = R.drawable.ic_cafe,
            price = 3.0,
            details = listOf(Detail.SUGAR),
        ),
    )
}

data class Product(
    val id: Int,
    val nameRes: Int,
    val iconRes: Int,
    val price: Double,
    val details: List<Detail>? = null,
)

enum class Detail(val nameRes: Int, val iconRes: Int = R.drawable.ic_cafe) {
    SUGAR(R.string.sugar),
}