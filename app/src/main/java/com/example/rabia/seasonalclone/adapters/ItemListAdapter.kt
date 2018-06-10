package com.example.rabia.seasonalclone.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.rabia.seasonalclone.*
import com.example.rabia.seasonalclone.models.ProduceItem

class ItemListAdapter(val items: List<ProduceItem>, val newItemsCount: Int, val fruitsCount: Int, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_TITLE = 0
    val VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 0) {
            val view = CustomHeaderView.inflate(parent)
            return SimpleViewHolder(view)
            //return ViewHolderHeader(context.inflate(R.layout.item_header, parent, false)) //Extention method was used

        } else {
            val view = CustomItemView.inflate(parent)  //With custom view
            return SimpleViewHolder(view)
            //return ViewHolderItem(context.inflate(R.layout.item_produce, parent, false)) //Extention method was used
            //LayoutInflater.from(context).inflate(R.layout.item_produce, parent, false)) // Simple implementation
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

                val viewHolderItem: SimpleViewHolder<CustomItemView> = holder as SimpleViewHolder<CustomItemView>

                GlideApp.with(context)
                        .load(items.get(position - 1).itemImgUrl)
                        .into(viewHolderItem?.view.ivItemImg)

                viewHolderItem?.view.tvItemName?.text = items.get(position - 1).itemName
                viewHolderItem?.view.tvItemType?.text = items.get(position - 1).itemType
                //viewHolderItem?.tvItemDesc?.text = items.get(position - 1).itemDesc
            }
            else -> {
                val viewHolderHeader: SimpleViewHolder<CustomHeaderView> = holder as SimpleViewHolder<CustomHeaderView>
                if (position == 0) viewHolderHeader.view.tvHeader?.text = "New Season";
                else if (position == newItemsCount + 1) viewHolderHeader.view.tvHeader?.text = "Fruits"
                else viewHolderHeader.view.tvHeader?.text = "Veggies";
            }
        }
    }

}

/*class ViewHolderHeader(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle = view.tvTitle
}

class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
    val tvItemName = view.tvItemName
    val tvItemType = view.tvItemtype
    val ivItemImg = view.ivItemImg
    val tvItemDesc = view.tvItemDesc
}*/

/* Instead of calling different custom viewholders, we are calling generic SimpleViewHolder */
class SimpleViewHolder<out T : View>(itemView: T)
    : RecyclerView.ViewHolder(itemView) {
    val view: T
        get() = itemView as T
}