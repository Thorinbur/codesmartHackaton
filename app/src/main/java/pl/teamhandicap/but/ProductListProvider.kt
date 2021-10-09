package pl.teamhandicap.but

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

object ProductListProvider {
    fun get() = listOf(
        Product(
            id = 1,
            nameRes = R.string.coffee_AMERICANA,
            iconRes = R.raw.americano,
            price = 3.0,
            details = Detail.values().toList(),
        ),
        Product(
            id = 2,
            nameRes = R.string.coffee_CAPPUCCINO,
            iconRes = R.raw.cappuchino,
            price = 3.0,
            details = Detail.values().toList(),
        ),
        Product(
            id = 3,
            nameRes = R.string.coffee_ESPRESSO,
            iconRes = R.raw.espresso,
            price = 3.0,
            details = Detail.values().toList(),
        ),
        Product(
            id = 4,
            nameRes = R.string.coffee_FLAT_WHITE,
            iconRes = R.raw.flat_white,
            price = 3.0,
            details = Detail.values().toList(),
        ),
        Product(
            id = 5,
            nameRes = R.string.coffee_MOCHA,
            iconRes = R.raw.mocha,
            price = 3.0,
            details = Detail.values().toList(),
        ),
        Product(
            id = 6,
            nameRes = R.string.coffee_ICED_FLAT_WHITE,
            iconRes = R.raw.flat_white,
            price = 3.0,
            details = Detail.values().toList(),
        ),
        Product(
            id = 7,
            nameRes = R.string.tea,
            iconRes = R.raw.herbata,
            price = 3.0,
            details = listOf(),
        ),
        Product(
            id = 8,
            nameRes = R.string.sandwich_CHEESE_AND_HAM,
            iconRes = R.raw.sandwich_2,
            price = 3.0,
            details = listOf(),
        ),
        Product(
            id = 9,
            nameRes = R.string.sandwich_CHICKEN_AND_FETA_CHEESE,
            iconRes = R.raw.sandwich_3,
            price = 3.0,
            details = listOf(),
        ),
        Product(
            id = 10,
            nameRes = R.string.sandwich_HAM,
            iconRes = R.raw.sandwich_4,
            price = 3.0,
            details = listOf(),
        ),
        Product(
            id = 11,
            nameRes = R.string.sandwich_SALMON_AND_GUACAMOLE,
            iconRes = R.raw.sandwich_5,
            price = 3.0,
            details = listOf(),
        ),
        Product(
            id = 12,
            nameRes = R.string.sandwich_TUNA_BAGUETTE,
            iconRes = R.raw.kanapka,
            price = 3.0,
            details = listOf(),
        )
    )
}

@Parcelize
data class Product(
    val id: Int,
    val nameRes: Int,
    val iconRes: Int,
    val price: Double,
    val details: List<Detail>? = null,
) : Parcelable

enum class Detail(val nameRes: Int, val iconRes: Int = R.drawable.ic_cafe) {
    SUGAR(R.string.sugar),
    SUGAR_CANE(R.string.sugar_cane),
    MIXER(R.string.mixer),
    MILK(R.string.milk),
    SOY_MILK(R.string.milk_soy),
    LACTOSE_FREE_MILK(R.string.milk_lactose_free)
}