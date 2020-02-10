package com.charmist.bitcoinapp.viewholder

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.charmist.bitcoinapp.vo.Coin
import com.github.twocoffeesoneteam.glidetovector.GlideToVector
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_detail_bitcoin.*

class BitCoinDetailViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bindUI(it: Coin) {
        tvTitle.text = it.name
        tvDescription.text = it.description
        GlideToVector.justLoadImage(itemView.context, Uri.parse(it.iconUrl), ivBitCoin)
    }

    fun setOnClickListener(onClick: () -> Unit?) {
        itemView.setOnClickListener { onClick() }
    }
}