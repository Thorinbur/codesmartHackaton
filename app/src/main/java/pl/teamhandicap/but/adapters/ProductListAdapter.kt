package pl.teamhandicap.but.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_product_item.view.*
import pl.teamhandicap.but.Product
import pl.teamhandicap.but.view.ProductItemView

class ProductListAdapter(
    private val items: List<Product>,
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val label get() = itemView.label
        private val icon get() = itemView.icon

        fun bind(model: Product, onClickAction: (Int) -> Unit) {
            label.text = itemView.context.getText(model.nameRes)
//            val drawable = AppCompatResources.getDrawable(itemView.context, model.iconRes)
            icon.setImageResource(model.iconRes)
//            icon.setImageDrawable(drawable)
            itemView.setOnClickListener { onClickAction(model.id) }
        }
    }
}