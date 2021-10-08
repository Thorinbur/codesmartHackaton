package pl.teamhandicap.but.network

object Repository {
    private val service: Service get() = ServiceProvider.service

    fun postNewOrder() = service.postNewOrder()
}