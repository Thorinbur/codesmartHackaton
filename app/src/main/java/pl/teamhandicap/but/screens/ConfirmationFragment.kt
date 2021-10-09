package pl.teamhandicap.but.screens

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_confirmation_layout.*
import pl.teamhandicap.but.CartItem
import pl.teamhandicap.but.MainActivity
import pl.teamhandicap.but.R
import pl.teamhandicap.but.adapters.ConfirmationItemsAdapter
import pl.teamhandicap.but.network.Order
import pl.teamhandicap.but.network.Product
import pl.teamhandicap.but.network.Repository

class ConfirmationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation_layout, container, false)
    }

    private val args by navArgs<ConfirmationFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colors = context?.resources?.getIntArray(R.array.box_colors)
        val data = args.orderConfirmation
        val adapter = ConfirmationItemsAdapter(data.items, colors)
        val layoutManager = LinearLayoutManager(context)
        (activity as MainActivity).setActionBarTitle("Checkout")
        confirmationRecyclerView.apply {
            setLayoutManager(layoutManager)
            setAdapter(adapter)
        }
        payNowButton.setOnClickListener {
            Toast.makeText(context, "Dostępne wkrótce", Toast.LENGTH_SHORT).show()
        }
        var price = 0.0
        data.items.forEach {
            price += it.product.price
        }
        val formattedPrice = String.format("%.2f", price)
        val priceText = "$formattedPrice PLN"
        confirmationPriceSummaryText.text = priceText
        payLaterButton.setOnClickListener {
            val products = data.items.map {
                Product(
                    name = context?.getString(it.product.nameRes),
                    additionalNote = it.details.joinToString(separator = ", ") {
                        requireContext().getText(it.nameRes)
                    },
                )
            }
            Repository.postNewOrder(
                Order(products, price)
            ) {
                Toast.makeText(context, "Request Successful", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Parcelize
data class OrderConfirmationData(
    val items: List<CartItem>
) : Parcelable