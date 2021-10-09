package pl.teamhandicap.but.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_confirmation_details.view.*
import pl.teamhandicap.but.Detail
import pl.teamhandicap.but.R

class ConfirmationDetailsAdapter(
    private val items: List<Detail>
) : RecyclerView.Adapter<ConfirmationDetailsAdapter.DetailItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailItemViewHolder {
        return DetailItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_confirmation_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class DetailItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Detail) {
            itemView.apply {
                confirmationItemDetailsImage.setImageResource(model.iconRes)
                confirmationItemDetailsText.text = model.name
            }
        }
    }
}