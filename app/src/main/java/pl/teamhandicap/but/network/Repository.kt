package pl.teamhandicap.but.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    var lastGuid:String? = null
    private val service: Service get() = ServiceProvider.service

    fun postNewOrder(order:Order, onResponse:(Response<OrderResponse>) -> Unit) = service.postNewOrder(
       order
    ).enqueue(object: Callback<OrderResponse>{
        override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
            lastGuid = response.body()?.id
            onResponse.invoke(response)
        }

        override fun onFailure(call: Call<OrderResponse>, t: Throwable) {

        }

    })

    fun getOrderDetails(guid:String, onResponse:(Response<Order>) -> Unit) = service.getOrder(
        guid
    ).enqueue(object: Callback<Order>{
        override fun onResponse(call: Call<Order>, response: Response<Order>) {
            onResponse.invoke(response)
        }

        override fun onFailure(call: Call<Order>, t: Throwable) {
        }
    })

    fun getOrders(onResponse:(List<Order>) -> Unit) = service.getOrders().enqueue(object: Callback<List<Order>>{
        override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
            onResponse.invoke(response.body()!!)
        }

        override fun onFailure(call: Call<List<Order>>, t: Throwable) {
        }
    })
}