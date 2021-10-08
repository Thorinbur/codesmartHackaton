package pl.teamhandicap.but.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {
    private val retrofit = Retrofit.Builder()
            // TODO: add proper baseUrl
        .baseUrl("https://www.example.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: Service = retrofit.create(Service::class.java)
}