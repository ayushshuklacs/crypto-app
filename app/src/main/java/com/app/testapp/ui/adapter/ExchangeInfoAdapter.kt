package com.app.testapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.R
import com.app.testapp.callback.RootCallback
import com.app.testapp.databinding.AdapterExchangeInfoBinding
import com.app.testapp.model.ExchangeInfoItem

class ExchangeInfoAdapter(private var list: MutableList<ExchangeInfoItem?>?) :
    RecyclerView.Adapter<ExchangeInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterExchangeInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exchangeInfo = list?.get(position)
        holder.bind(exchangeInfo)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    fun setData(infoList: MutableList<ExchangeInfoItem?>?) {
        list = infoList
    }

    inner class ViewHolder(private val ui: AdapterExchangeInfoBinding) :
        RecyclerView.ViewHolder(ui.root) {
        fun bind(exchangeInfo: ExchangeInfoItem?) {
            ui.apply {
                exchangeInfo.apply {
                    val context = itemView.context
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

                    itemView.setOnClickListener {
                        callback?.onRootCallback(adapterPosition, exchangeInfo, it)
                    }

                }
            }
        }

    }

    private var callback: RootCallback<ExchangeInfoItem?>? = null
    fun rootCallback(rootCallback: RootCallback<ExchangeInfoItem?>?) {
        callback = rootCallback
    }
}