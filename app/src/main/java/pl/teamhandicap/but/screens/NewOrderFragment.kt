package pl.teamhandicap.but.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_new_order.*
import pl.teamhandicap.but.MainActivity
import pl.teamhandicap.but.NewOrderViewModel
import pl.teamhandicap.but.R
import pl.teamhandicap.but.adapters.ProductListAdapter

class NewOrderFragment : Fragment() {
    private val viewModel by activityViewModels<NewOrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val models = viewModel.products.map { ProductListAdapter.ProductAdapterItem(it) }
        val adapter = ProductListAdapter(models, ::onProductClicked)
        productsList.adapter = adapter
        productsList.layoutManager = GridLayoutManager(context, 3)
        (activity as MainActivity).setActionBarTitle("Menu")
        viewModel.cartItems.observe(viewLifecycleOwner, { selectedItems ->
            showCartButton.isVisible = selectedItems.isNotEmpty()
            cartItemsCounter.text = selectedItems.size.toString()
            cartItemsCounter.isVisible = selectedItems.isNotEmpty()
            val groups =
                selectedItems.groupBy { it.product.id }
            val newList = viewModel.products.map { product ->
                val quantity =
                    groups.takeIf { !it.isNullOrEmpty() }?.get(product.id)?.size ?: 0
                ProductListAdapter.ProductAdapterItem(
                    product,
                    quantity
                )
            }
            adapter.updateItems(newList)
        })

        showCartButton.setOnClickListener {
            findNavController().navigate(
                NewOrderFragmentDirections.showCart()
            )
        }
    }

    private fun onProductClicked(id: Int) {
        findNavController().navigate(
            NewOrderFragmentDirections.selectProduct(id)
        )
    }
}