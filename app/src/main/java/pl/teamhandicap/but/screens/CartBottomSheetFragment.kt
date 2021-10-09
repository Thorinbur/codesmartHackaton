package pl.teamhandicap.but.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_cart_bottom_sheet.*
import pl.teamhandicap.but.NewOrderViewModel
import pl.teamhandicap.but.R

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
            cartItems.forEach { cartItem ->
                val textView = TextView(context).apply {
                    val productName = context.getText(cartItem.product.nameRes)
                    val details = cartItem.details
                    text = "$productName\n$details"
                }
                productsList.addView(textView)
            }
        }
        orderButton.setOnClickListener {
            findNavController().navigate(
                CartBottomSheetFragmentDirections.confirmOrder()
            )
        }
    }
}