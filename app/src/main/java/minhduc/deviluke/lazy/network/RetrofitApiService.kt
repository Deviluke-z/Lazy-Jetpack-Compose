package minhduc.deviluke.lazy.network

import minhduc.deviluke.lazy.data.MarsPhoto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitApiService {
    @GET("characters")
    suspend fun getMarsPhoto(): List<MarsPhoto>

    companion object {
        var apiService: RetrofitApiService? = null

        fun getInstance(): RetrofitApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://hp-api.herokuapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitApiService::class.java)
            }
            return apiService!!
        }
    }
}