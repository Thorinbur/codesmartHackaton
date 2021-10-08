package pl.teamhandicap.but.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_cart_bottom_sheet.*
import pl.teamhandicap.but.NewOrderViewModel
import pl.teamhandicap.but.R

class CartBottomSheetFragment: BottomSheetDialogFragment() {
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
        val viewModel by viewModels<NewOrderViewModel>()
        viewModel.cartItems.value!!.forEach { itemName ->
            productsList.addView(TextView(context).apply { text = itemName })
        }
    }

    companion object {
        fun newInstance() =  CartBottomSheetFragment()
    }
}