package pl.teamhandicap.but.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cart_product.view.*
import pl.teamhandicap.but.CartItem
import pl.teamhandicap.but.R

class CartAdapter(
    private var list: List<CartItem>,
    private val clickListener: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: CartItem) {
            itemView.apply {
                cartItemText.text = context.getString(model.product.nameRes)
                cartItemDetails.apply {
                    isVisible = model.details.isNotEmpty()
                    val details = model.details
                        .joinToString(separator = ", ") { itemView.context.getText(it.nameRes) }
                    text = details
                }
                setOnClickListener { clickListener.invoke(model) }
            }
        }
    }
}