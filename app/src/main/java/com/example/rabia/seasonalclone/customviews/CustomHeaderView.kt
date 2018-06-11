package com.example.rabia.seasonalclone.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.rabia.seasonalclone.R
import com.example.rabia.seasonalclone.butterknife.bindView
import com.example.rabia.seasonalclone.extensions.inflate

class CustomHeaderView @JvmOverloads constructor(
        context: Context,
        attributes: AttributeSet? = null
) : LinearLayout(context, attributes) {

    val tvHeader: TextView by bindView(R.id.tvHeader)

    companion object {
        fun inflate(parent: ViewGroup): CustomHeaderView = parent.context.inflate(
                R.layout.item_header, parent, false) as CustomHeaderView
    }

    var header: String? = null
        set (value) {
            tvHeader?.text = header
        }
}