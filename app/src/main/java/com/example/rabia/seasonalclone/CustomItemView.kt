package com.example.rabia.seasonalclone

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.rabia.seasonalclone.butterknife.bindView
import com.example.rabia.seasonalclone.models.ProduceItem


class CustomItemView @JvmOverloads constructor(
        context: Context,
        attributes: AttributeSet? = null
) : RelativeLayout(context, attributes) {
    val ivItemImg: ImageView by bindView(R.id.ivItemImg)
    val tvItemName: TextView by bindView(R.id.tvItemName)
    val tvItemType: TextView by bindView(R.id.tvItemtype)

    companion object {
        fun inflate(parent: ViewGroup): CustomItemView = parent.context.inflate(
                R.layout.item_produce, parent, false) as CustomItemView
    }

    var produceItem: ProduceItem? = null
        set (value) {
            ivItemImg.setImageURI(Uri.parse(produceItem?.itemImgUrl))
            tvItemName?.text = produceItem?.itemName
            tvItemType?.text = produceItem?.itemType
        }
}