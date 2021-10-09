package pl.teamhandicap.but.adapters

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_product_item.view.*
import pl.teamhandicap.but.Product
import pl.teamhandicap.but.view.ProductItemView

class ProductListAdapter(
    private var items: List<ProductAdapterItem>,
    private val onItemClicked: (Int) -> Unit,
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductItemView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { item ->
            holder.bind(item) {
                onItemCLicked(it)
            }
        }
    }

    override fun getItemCount() = items.size

    private fun onItemCLicked(id: Int) {
        this.onItemClicked(id)
    }

    fun updateItems(newItems: List<ProductAdapterItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    data class ProductAdapterItem(
        val product: Product,
        val selectedQuantity: Int = 0
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val label get() = itemView.label
        private val icon get() = itemView.icon
        private val quantity get() = itemView.itemQuantityCounter
        private val price get() = itemView.price

        fun bind(model: ProductAdapterItem, onClickAction: (Int) -> Unit) {
            label.text = itemView.context.getText(model.product.nameRes)
            icon.setImageResource(model.product.imageRes)
            quantity.text = model.selectedQuantity.toString()
            quantity.isVisible = model.selectedQuantity > 0
            itemView.setOnClickListener { onClickAction(model.product.id) }
            price.text = "${String.format(" % .2f", model.product.price)} PLN"
        }
    }
}