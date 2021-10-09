package pl.teamhandicap.but.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.fragment_product_details.*
import pl.teamhandicap.but.Detail
import pl.teamhandicap.but.NewOrderViewModel
import pl.teamhandicap.but.R

class ProductDetailsDialogFragment: DialogFragment() {
    private val viewModel by activityViewModels<NewOrderViewModel>()
    private val args by navArgs<ProductDetailsDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        val product = viewModel.products.find { it.id == args.productId }!!
        product.details?.forEach {
            val switch = SwitchMaterial(requireContext()).apply {
                text = context.getText(it.nameRes)
            }
            detailsList.addView(switch)
        }
        acceptButton.setOnClickListener {
            val details = mutableListOf<Detail>()
            detailsList.children.forEachIndexed { index, view ->
                if ((view as? SwitchMaterial)?.isChecked == true) {
                    product.details?.get(index)?.let {
                        details.add(it)
                    }
                }
            }
            viewModel.addProductToOrder(args.productId, details)
            findNavController().popBackStack()
        }
    }
}