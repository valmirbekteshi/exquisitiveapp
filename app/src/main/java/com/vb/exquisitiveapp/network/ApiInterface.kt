package com.vb.exquisitiveapp.network

import com.vb.exquisitiveapp.model.ChallengeListResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("challenges.json")
    fun getChallanges() : Observable<ChallengeListResponse>

    companion object {

        var BASE_URL = "https://dev.exquisitive.co/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}