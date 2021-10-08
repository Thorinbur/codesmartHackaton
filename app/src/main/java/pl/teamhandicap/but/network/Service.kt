package pl.teamhandicap.but.network

import retrofit2.Call
import retrofit2.http.POST

interface Service {
    //TODO: add proper url
    @POST("newOrder")
    fun postNewOrder(): Call<Unit>
}