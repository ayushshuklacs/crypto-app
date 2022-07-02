package com.app.testapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.testapp.R
import com.app.testapp.databinding.ActivitySymbolViewBinding
import com.app.testapp.model.ExchangeInfoItem
import com.app.testapp.network.RetrofitClient
import com.app.testapp.utils.AppConstant
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TAG = "SymbolViewActivity"

class SymbolViewActivity : AppCompatActivity() {
    private lateinit var ui: ActivitySymbolViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivitySymbolViewBinding.inflate(layoutInflater)
        setContentView(ui.root)

        getSymbolFromIntent()
    }

    private fun getSymbolFromIntent() {
        val symbol = intent.getStringExtra(AppConstant.SYMBOL)

        hitApi(symbol)
    }

    private fun hitApi(symbol: String?) {
        try {
            RetrofitClient.apiInterface()?.symbolView(symbol)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnNext {
                    Log.d(TAG, "response: ${Gson().toJson(it)}")

                    showSymbolView(it)
                }
                ?.doOnError {
                    Log.d(TAG, "error: ${Gson().toJson(it)}")
                }
                ?.subscribe()
        } catch (e: Exception) {
            Log.d(TAG, "exception: ${e.localizedMessage}")
        }
    }

    private fun showSymbolView(exchangeInfo: ExchangeInfoItem?) {
        ui.apply {
            exchangeInfo.apply {
                val context = this@SymbolViewActivity
                layoutSymbol.tvTitle.text = context.getString(R.string.symbol)
                layoutSymbol.tvValue.text = exchangeInfo?.symbol

                layoutBaseAsset.tvTitle.text = context.getString(R.string.base_asset)
                layoutBaseAsset.tvValue.text = exchangeInfo?.baseAsset

                layoutQuoteAsset.tvTitle.text = context.getString(R.string.quote_asset)
                layoutQuoteAsset.tvValue.text = exchangeInfo?.quoteAsset

                layoutOpenPrice.tvTitle.text = context.getString(R.string.open_price)
                layoutOpenPrice.tvValue.text = exchangeInfo?.openPrice

                layoutLowPrice.tvTitle.text = context.getString(R.string.low_price)
                layoutLowPrice.tvValue.text = exchangeInfo?.lowPrice

                layoutHighPrice.tvTitle.text = context.getString(R.string.high_price)
                layoutHighPrice.tvValue.text = exchangeInfo?.highPrice

                layoutVolume.tvTitle.text = context.getString(R.string.volume)
                layoutVolume.tvValue.text = exchangeInfo?.volume

                layoutBidPrice.tvTitle.text = context.getString(R.string.bid_price)
                layoutBidPrice.tvValue.text = exchangeInfo?.bidPrice

                layoutAskPrice.tvTitle.text = context.getString(R.string.symbol)
                layoutAskPrice.tvValue.text = exchangeInfo?.symbol

                layoutAt.tvTitle.text = context.getString(R.string.at)
                layoutAt.tvValue.text = exchangeInfo?.at?.toString()

            }
        }
    }
}