package pl.teamhandicap.but.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {
    @POST("orders")
    fun postNewOrder(@Body order: Order): Call<OrderResponse>

    @GET("orders/{guid}")
    fun getOrder(@Path("guid") guid: String): Call<Order>
}

