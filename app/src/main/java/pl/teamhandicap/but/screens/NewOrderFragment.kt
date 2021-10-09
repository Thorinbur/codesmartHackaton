package pl.teamhandicap.but.screens

import android.os.Bundle
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

        productsList.adapter = ProductListAdapter(viewModel.products, ::onProductClicked)
        productsList.layoutManager = GridLayoutManager(context, 3)
        (activity as MainActivity).setActionBarTitle("Menu")

        showCartButton.setOnClickListener {
            findNavController().navigate(
                NewOrderFragmentDirections.showCart()
            )
        }

        viewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            showCartButton.isVisible = cartItems.isNotEmpty()
            cartItemsCounter.isVisible = cartItems.isNotEmpty()
            cartItemsCounter.text = "${cartItems.size}"
        }
    }

    private fun onProductClicked(id: Int) {
        findNavController().navigate(
            NewOrderFragmentDirections.selectProduct(id)
        )
    }
}