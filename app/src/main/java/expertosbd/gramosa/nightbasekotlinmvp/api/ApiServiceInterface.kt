package expertosbd.gramosa.nightbasekotlinmvp.api

import expertosbd.gramosa.nightbasekotlinmvp.model.Event
import expertosbd.gramosa.nightbasekotlinmvp.utils.BASE_URL
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("event/allevents")
    fun getEvents(): Observable<List<Event>>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}