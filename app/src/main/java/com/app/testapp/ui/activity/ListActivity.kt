package com.app.testapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.callback.RootCallback
import com.app.testapp.databinding.ActivityListBinding
import com.app.testapp.model.ExchangeInfoItem
import com.app.testapp.network.RetrofitClient
import com.app.testapp.ui.adapter.ExchangeInfoAdapter
import com.app.testapp.utils.AppConstant
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TAG = "ListActivity"

class ListActivity : AppCompatActivity(), RootCallback<ExchangeInfoItem?> {
    private lateinit var ui: ActivityListBinding
    private var adapter: ExchangeInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityListBinding.inflate(layoutInflater)
        setContentView(ui.root)

        hitApi()
    }

    private fun hitApi() {
        try {
            RetrofitClient.apiInterface()?.exchangeInfo()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnNext {
                    Log.d(TAG, "response: ${Gson().toJson(it)}")

                    showExchangeInfoList(it.toMutableList())
                }
                ?.doOnError {
                    Log.d(TAG, "error: ${Gson().toJson(it)}")
                }
                ?.subscribe()
        } catch (e: Exception) {
            Log.d(TAG, "exception: ${e.localizedMessage}")
        }
    }

    private fun showExchangeInfoList(exchangeInfoList: MutableList<ExchangeInfoItem?>?) {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        ui.rcvSymbol.layoutManager = layoutManager
        ui.rcvSymbol.hasFixedSize()
        if (adapter == null) {
            adapter = ExchangeInfoAdapter(exchangeInfoList)
            ui.rcvSymbol.adapter = adapter
        } else
            adapter?.setData(exchangeInfoList)

        adapter?.rootCallback(this)
    }

    override fun onRootCallback(index: Int, data: ExchangeInfoItem?, view: View?) {
        startActivity(Intent(this, SymbolViewActivity::class.java).apply {
            putExtra(AppConstant.SYMBOL, data?.symbol)
        })
    }
}