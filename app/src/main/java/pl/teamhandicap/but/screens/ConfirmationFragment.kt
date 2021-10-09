package pl.teamhandicap.but.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_confirmation_layout.*
import pl.teamhandicap.but.MainActivity
import pl.teamhandicap.but.R
import pl.teamhandicap.but.adapters.ConfirmationItemsAdapter
import pl.teamhandicap.but.adapters.DetailsModel
import pl.teamhandicap.but.adapters.TestModel

class ConfirmationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmation_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colors = context?.resources?.getIntArray(R.array.box_colors)
        val adapter = ConfirmationItemsAdapter(createTestData(), colors)
        val layoutManager = LinearLayoutManager(context)
        (activity as MainActivity).setActionBarTitle("Checkout")
        confirmationRecyclerView.apply {
            setLayoutManager(layoutManager)
            setAdapter(adapter)
        }
        confirmationPriceSummaryText.text = "12.90 PLN"
    }

    private fun createTestData(): List<TestModel> {
        return listOf(
            TestModel(
                name = "Latte",
                numberOfItems = 2,
                details = listOf(
                    DetailsModel(
                        name = "Cukier x1",
                        imageRes = R.drawable.ic_detail_outline
                    ),
                    DetailsModel(
                        name = "Mieszadelko",
                        imageRes = R.drawable.ic_detail_outline
                    ),
                    DetailsModel(
                        name = "Mleko bez laktozy",
                        imageRes = R.drawable.ic_detail_outline
                    )
                ),
                imageRes = R.drawable.ic_cafe_outline,
                price = 3.0
            ),
            TestModel(
                name = "Black Coffe",
                numberOfItems = 0,
                details = listOf(
                    DetailsModel(
                        name = "Cukier Trzcinowy x2",
                        imageRes = R.drawable.ic_detail_outline
                    ),
                    DetailsModel(
                        name = "Mieszadelko",
                        imageRes = R.drawable.ic_detail_outline
                    ),
                ),
                imageRes = R.drawable.ic_cafe_outline,
                price = 3.0
            ),
            TestModel(
                name = "Kanapka Indyk",
                numberOfItems = 1,
                details = listOf(),
                imageRes = R.drawable.ic_food_outline,
                price = 6.90
            )
        )
    }
}