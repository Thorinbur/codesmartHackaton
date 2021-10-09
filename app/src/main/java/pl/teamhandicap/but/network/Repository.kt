package pl.teamhandicap.but.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    var lastGuid:String? = null
    private val service: Service get() = ServiceProvider.service

    fun postNewOrder(onResponse:(Response<OrderResponse>) -> Unit) = service.postNewOrder(
        Order(
            listOf(
                Product("Test", "with Milk")
            ),
            12.43
        )
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
}