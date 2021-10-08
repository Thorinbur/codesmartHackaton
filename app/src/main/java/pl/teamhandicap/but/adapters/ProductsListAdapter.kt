package pl.teamhandicap.but.adapters

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_product_item.view.*
import pl.teamhandicap.but.view.ProductItemView

class ProductsListAdapter(
    private val items: List<Product>
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductItemView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { item -> holder.bind(item) }
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val label get() = itemView.label
        private val icon get() = itemView.icon

        fun bind(model: Product) {
            label.text = model.name
            val drawable = AppCompatResources.getDrawable(itemView.context, model.iconRes)
            icon.setImageDrawable(drawable)
        }
    }
}

data class Product(
    val name: String,
    val iconRes: Int,
)