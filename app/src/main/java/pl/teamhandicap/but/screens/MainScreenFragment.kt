package pl.teamhandicap.but.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_main_layout.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.teamhandicap.but.MainActivity
import pl.teamhandicap.but.R
import pl.teamhandicap.but.adapters.OrderListAdapter
import pl.teamhandicap.but.network.Repository
import retrofit2.Retrofit

class MainScreenFragment : Fragment() {
    val adapter = OrderListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.newOrderButton.setOnClickListener {
            findNavController().navigate(MainScreenFragmentDirections.newOrder())
        }
        Repository.getOrders {
            view.orderList.adapter = adapter
            adapter.changeItems(it)
        }
        (activity as MainActivity).setActionBarTitle("Twoje zamówienia")
        viewLifecycleOwner.lifecycleScope.launch {
            while(true) {
                delay(2000)
                Repository.getOrders {
                    adapter.changeItems(it)
                }
            }
        }
    }
}