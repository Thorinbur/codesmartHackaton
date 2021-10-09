package pl.teamhandicap.but.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_confirmation.view.*
import pl.teamhandicap.but.CartItem
import pl.teamhandicap.but.R

class ConfirmationItemsAdapter(
    private val items: List<CartItem>,
    private val colors: IntArray?
) : RecyclerView.Adapter<ConfirmationItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_confirmation, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val model = items[position]
            itemView.apply {
                colors?.let {
                    val color = it[position % it.size]
//                    confirmationItemContainer.setCardBackgroundColor(color)
                }
                confirmationItemImage.setImageResource(model.product.iconRes)
                confirmationItemDetailsRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    val detailsAdapter = ConfirmationDetailsAdapter(model.details)
                    adapter = detailsAdapter
                    detailsAdapter.notifyDataSetChanged()
                }
                confirmationTitleTextView.text = context.getString(model.product.nameRes)
                val price = String.format("%.2f", model.product.price)
                val priceText = "$price PLN"
                confirmationTotalPrice.text = priceText
            }
        }
    }
}

data class TestModel(
    val name: String,
    val numberOfItems: Int,
    val details: List<CartItem>,
    val price: Double,
    @DrawableRes val imageRes: Int
)