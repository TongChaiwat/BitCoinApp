package com.charmist.bitcoinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.charmist.bitcoinapp.R
import com.charmist.bitcoinapp.vo.Coin
import com.charmist.bitcoinapp.viewholder.BitCoinDetailViewHolder
import com.charmist.bitcoinapp.viewholder.BitCoinHeaderViewHolder

class BitCoinAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var coinList: MutableList<Coin> = mutableListOf()
    private var onItemClick: ((Coin) -> Unit)? = null
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                BitCoinHeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_header_bitcoin,
                        parent,
                        false
                    )
                )
            }
            else -> {
                BitCoinDetailViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_detail_bitcoin,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int = (position + 1) % 5

    override fun getItemCount(): Int = coinList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        coinList[position].let {
            when (holder.itemViewType) {
                0 -> {
                    (holder as BitCoinHeaderViewHolder).bindUI(it)
                }
                else -> {
                    (holder as BitCoinDetailViewHolder).bindUI(it)
                    holder.setOnClickListener { onItemClick?.invoke(it) }
                }
            }
            if (position > lastPosition) {
                holder.itemView.startAnimation(
                    AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_up)
                )
                lastPosition = position
            }
        }

    }

    fun setOnItemClickListener(onItemClick: ((Coin) -> Unit)?) {
        this.onItemClick = onItemClick
    }

    fun setBitCoinList(itemList: MutableList<Coin>) {
        lastPosition = -1
        coinList = itemList
        notifyDataSetChanged()
    }

    fun addBitCoinList(itemList: MutableList<Coin>) {
        coinList.addAll(coinList.size, itemList)
        notifyDataSetChanged()
    }
}