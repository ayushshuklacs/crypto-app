package com.app.testapp.model
import com.google.gson.annotations.SerializedName


class ExchangeInfo : ArrayList<ExchangeInfoItem>()

data class ExchangeInfoItem(
    @SerializedName("askPrice")
    var askPrice: String?,
    @SerializedName("at")
    var at: Long?,
    @SerializedName("baseAsset")
    var baseAsset: String?,
    @SerializedName("bidPrice")
    var bidPrice: String?,
    @SerializedName("highPrice")
    var highPrice: String?,
    @SerializedName("lastPrice")
    var lastPrice: String?,
    @SerializedName("lowPrice")
    var lowPrice: String?,
    @SerializedName("openPrice")
    var openPrice: String?,
    @SerializedName("quoteAsset")
    var quoteAsset: String?,
    @SerializedName("symbol")
    var symbol: String?,
    @SerializedName("volume")
    var volume: String?
)