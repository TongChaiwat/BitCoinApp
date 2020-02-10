package com.charmist.bitcoinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charmist.bitcoinapp.adapter.BitCoinAdapter
import com.charmist.bitcoinapp.api.ApiManager
import com.charmist.bitcoinapp.vo.BitCoinResult
import com.charmist.bitcoinapp.vo.Coin
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val adapter = BitCoinAdapter()
    private var isLoadMore = true
    private val paginationLimit = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter.setOnItemClickListener(onItemClick)
        rvBitCoin.adapter = adapter
        rvBitCoin.layoutManager = LinearLayoutManager(this)
        swipeRefreshLayout.setOnRefreshListener {
            et_search.text.clear()
            requestCoin(0, true)
        }
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotEmpty()) {
                    isLoadMore = false
                    requestCoinByPrefix(p0.toString())
                } else {
                    requestCoin(0, true)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        rvBitCoin.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = (rvBitCoin.layoutManager as LinearLayoutManager).childCount
                val pastVisibleItem =
                    (rvBitCoin.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount
                if (isLoadMore) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        progressBar.visibility = View.VISIBLE
                        isLoadMore = false
                        requestCoin(adapter.itemCount, false)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        requestCoin(0, true)
    }

    private fun requestCoin(offset: Int, isRefresh: Boolean) {
        ApiManager.get().getCoin(paginationLimit, offset).enqueue(object : Callback<BitCoinResult> {
            override fun onResponse(
                call: Call<BitCoinResult>?,
                response: Response<BitCoinResult>?
            ) {
                response?.body()?.data?.coins?.let {
                    if (isRefresh) {
                        adapter.setBitCoinList(it)
                    } else {
                        adapter.addBitCoinList(it)
                    }
                    isLoadMore = true
                    progressBar.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<BitCoinResult>?, t: Throwable) {
                isLoadMore = true
                progressBar.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun requestCoinByPrefix(prefix: String) {
        ApiManager.get().getCoinByPrefix(prefix).enqueue(object : Callback<BitCoinResult> {
            override fun onResponse(
                call: Call<BitCoinResult>?,
                response: Response<BitCoinResult>?
            ) {
                response?.body()?.data?.coins?.let {
                    adapter.setBitCoinList(it)
                }
            }

            override fun onFailure(call: Call<BitCoinResult>?, t: Throwable) {
            }
        })
    }

    private val onItemClick = { coin: Coin ->
        Toast.makeText(this, coin.name, Toast.LENGTH_SHORT).show()
    }

}
