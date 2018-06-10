package com.example.rabia.seasonalclone.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.rabia.seasonalclone.GlideApp
import com.example.rabia.seasonalclone.R
import com.example.rabia.seasonalclone.inflate
import com.example.rabia.seasonalclone.models.ProduceItem
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_produce.view.*

class ItemListAdapter(val items: List<ProduceItem>, val newItemsCount: Int, val fruitsCount: Int, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_TITLE = 0
    val VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 0) {
            return ViewHolderHeader(context.inflate(R.layout.item_header, parent, false)) //Extention method was used

        } else {
            return ViewHolderItem(context.inflate(R.layout.item_produce, parent, false)) //Extention method was used
            //LayoutInflater.from(context).inflate(R.layout.item_produce, parent, false))
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size + 2
    }


    override fun getItemViewType(position: Int): Int {

        if (position == 0 || position == newItemsCount + 1 || position == (newItemsCount + fruitsCount + 2)) {
            return VIEW_TYPE_TITLE;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //GlideApp or Glide
        when (holder.getItemViewType()) {
            VIEW_TYPE_ITEM -> {

                val viewHolderItem: ViewHolderItem = holder as ViewHolderItem

                GlideApp.with(context)
                        .load(items.get(position - 1).itemImgUrl)
                        .into(viewHolderItem?.ivItemImg)

                viewHolderItem?.tvItemName?.text = items.get(position - 1).itemName
                viewHolderItem?.tvItemType?.text = items.get(position - 1).itemType
            }
            else -> {
                val viewHolderHeader: ViewHolderHeader = holder as ViewHolderHeader
                if (position == 0) viewHolderHeader.tvTitle?.text = "New Season";
                else if (position == newItemsCount + 1) viewHolderHeader.tvTitle?.text = "Fruits"
                else viewHolderHeader.tvTitle?.text = "Vegetables";
            }
        }
    }

}

class ViewHolderHeader(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle = view.tvTitle
}

class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
    val tvItemName = view.tvItemName
    val tvItemType = view.tvItemtype
    val ivItemImg = view.ivItemImg
}