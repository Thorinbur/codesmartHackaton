package pl.teamhandicap.but.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_cart_bottom_sheet.*
import pl.teamhandicap.but.NewOrderViewModel
import pl.teamhandicap.but.R
import pl.teamhandicap.but.adapters.CartAdapter

class CartBottomSheetFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        val viewModel by activityViewModels<NewOrderViewModel>()
        viewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            if (cartItems.isEmpty()) {
                dismiss()
                return@observe
            }
            productsListRecyclerView.adapter = CartAdapter(cartItems) {
                viewModel.removeItemFromCart(it)
            }
            productsListRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        orderButton.setOnClickListener {
            viewModel.cartItems.value?.toList()?.let {
                val data = OrderConfirmationData(it)
                findNavController().navigate(
                    CartBottomSheetFragmentDirections.confirmOrder(data)
                )
            }
        }
    }
}