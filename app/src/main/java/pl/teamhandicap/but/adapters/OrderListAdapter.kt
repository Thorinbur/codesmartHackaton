package pl.teamhandicap.but.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_order_details.view.*
import pl.teamhandicap.but.R
import pl.teamhandicap.but.network.Order
import pl.teamhandicap.but.network.Status
import pl.teamhandicap.but.screens.MainScreenFragmentDirections

class OrderListAdapter(private val items:List<Order>):RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(order:Order){
            when (order.status) {
                Status.Confirmed -> {
                    itemView.orderNumber.text = "Twoje zamówinie o numerze ${order.orderNumber.toString()} \n Jest w trakcie przygotowania."
                    itemView.actionButton.isEnabled = false
                }
                Status.Ready -> {
                    itemView.actionButton.setOnClickListener {
                        itemView.findNavController().navigate(MainScreenFragmentDirections.pickUp(order.id!!))
                    }
                    itemView.orderNumber.text = "Twoje zamówienie o numerze ${order.orderNumber.toString()} \n jest gotowe do odbioru! Zapraszamy!"
                }
                Status.Pending -> {
                    itemView.actionButton.isEnabled = false
                    itemView.orderNumber.text = "Twoje zamówienie o numerze ${order.orderNumber.toString()} \n czeka na akceptacje pracownika Nero"

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()
}