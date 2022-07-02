package com.app.testapp.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://api.wazirx.com/"
        private fun getClient(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(Interceptor { chain ->
                val original: Request = chain.request()
                val request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            })
            val client: OkHttpClient = httpClient
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }


        fun apiInterface(): ApiInterface? {
            return getClient().create(ApiInterface::class.java)
        }
    }
}