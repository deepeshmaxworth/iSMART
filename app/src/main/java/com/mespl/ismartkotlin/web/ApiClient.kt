
package com.mespl.ismartkotlin.web



import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private var retrofit: Retrofit? = null

    var BASE_URL = ""
    var URL = "http://MWAPPPRD.ds.indianoil.in/IOCLServiceDev/Service1.svc/json/"
    private val httpClient = OkHttpClient.Builder()
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)


    val retrofitBuilder: Retrofit?
        get() {

//            val httpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
            if (BASE_URL.equals("", ignoreCase = true)) {
                BASE_URL = URL
            }
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit
        }

}