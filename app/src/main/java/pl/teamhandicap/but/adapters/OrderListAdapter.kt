package pl.teamhandicap.but.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_order_details.view.*
import pl.teamhandicap.but.CartItem
import pl.teamhandicap.but.Detail
import pl.teamhandicap.but.Product
import pl.teamhandicap.but.R
import pl.teamhandicap.but.network.Order
import pl.teamhandicap.but.network.Status
import pl.teamhandicap.but.screens.MainScreenFragmentDirections
import pl.teamhandicap.but.screens.OrderConfirmationData

class OrderListAdapter(private var items: List<Order>) :
    RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Order) {
            when (order.status) {
                Status.Confirmed -> {
                    itemView.status.text = "Potwierdzona"
                    //itemView.status.setTextColor(itemView.context.getColor(R.color.light_blue))
                    itemView.infoText.text =
                        "Twoje zamówinie o numerze ${order.orderNumber.toString()} \nJest w trakcie przygotowania."
                    itemView.actionButton.visibility = View.INVISIBLE
                }
                Status.Ready -> {
                    itemView.status.text = "Gotowe"
                    itemView.actionButton.text = "Odbierz"
                    //   itemView.status.setTextColor(itemView.context.getColor(R.color.light_green))
                    itemView.actionButton.setOnClickListener {
                        itemView.findNavController()
                            .navigate(MainScreenFragmentDirections.pickUp(order.id!!))
                    }
                    itemView.actionButton.visibility = View.VISIBLE
                    itemView.infoText.text =
                        "Twoje zamówienie o numerze ${order.orderNumber.toString()} \njest gotowe do odbioru! Zapraszamy!"
                }
                Status.Pending -> {
                    itemView.status.text = "Oczekuje"
                    //  itemView.status.setTextColor(itemView.context.getColor(R.color.light_yellow))
                    itemView.actionButton.visibility = View.INVISIBLE
                    itemView.infoText.text =
                        "Twoje zamówienie o numerze ${order.orderNumber.toString()} \nczeka na akceptacje pracownika Nero"
                }
                Status.Completed -> {
                    itemView.status.text = "Odebrane"
                    //  itemView.status.setTextColor(itemView.context.getColor(R.color.light_yellow))
                    itemView.actionButton.visibility = View.VISIBLE
                    itemView.actionButton.text = "Zamów ponownie"
                    itemView.actionButton.setOnClickListener {
                        itemView.findNavController().navigate(
                            MainScreenFragmentDirections.actionMainScreenFragmentToConfirmationFragment(
                                OrderConfirmationData(
                                    listOf(
                                        CartItem(
                                            Product(
                                                id = 1,
                                                nameRes = R.string.coffee_AMERICANA,
                                                imageRes = R.raw.americano,
                                                iconRes = R.drawable.ic_cafe_outline,
                                                price = 3.0,
                                                details = emptyList(),
                                            ),
                                            details = listOf(Detail.SUGAR, Detail.MILK),
                                        ),
                                        CartItem(
                                            Product(
                                                id = 2,
                                                nameRes = R.string.sandwich_CHEESE_AND_HAM,
                                                imageRes = R.raw.sandwich_3,
                                                iconRes = R.drawable.ic_food_outline,
                                                price = 6.90,
                                                details = emptyList(),
                                            ),
                                            details = emptyList(),
                                        ),
                                        CartItem(
                                            Product(
                                                id = 3,
                                                nameRes = R.string.coffee_FLAT_WHITE,
                                                imageRes = R.raw.flat_white,
                                                iconRes = R.drawable.ic_cafe_outline,
                                                price = 3.0,
                                                details = emptyList(),
                                            ),
                                            details = listOf(Detail.SUGAR, Detail.MILK),
                                        ),
                                    )
                                )
                            )
                        )
                    }
                    itemView.infoText.text =
                        "Zamówienie skompletowane 1 dzień temu"

                }
            }
        }
    }

    fun changeItems(newItems: List<Order>) {
        items = newItems.take(5).mapIndexed{ i, item -> if (i<3) item else item.copy(status = Status.Completed)}
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()
}