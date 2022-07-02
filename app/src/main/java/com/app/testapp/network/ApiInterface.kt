package com.app.testapp.network


import com.app.testapp.model.ExchangeInfo
import com.app.testapp.model.ExchangeInfoItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("sapi/v1/tickers/24hr")
    fun exchangeInfo(): Observable<ExchangeInfo>

    @GET("sapi/v1/ticker/24hr?")
    fun symbolView(@Query("symbol") symbol: String?): Observable<ExchangeInfoItem>
}