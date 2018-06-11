package com.example.rabia.seasonalclone.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rabia.seasonalclone.R
import com.example.rabia.seasonalclone.adapters.ItemListAdapter
import com.example.rabia.seasonalclone.models.ProduceItem


class ProductListFragment : Fragment() {

    var itemList: List<ProduceItem> = ArrayList<ProduceItem>()
    var newItemsCount = 0
    var header: String = ""

    companion object {

        @JvmStatic
        fun newInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_product_list, container, false)

        val rvItemList = view.findViewById<RecyclerView>(R.id.rvItemList)
        rvItemList.layoutManager = LinearLayoutManager(activity)

        rvItemList.adapter = ItemListAdapter(itemList, newItemsCount, header, context!!)
        return view
    }


    fun initFragment(itemList: List<ProduceItem>, newItemsListCount: Int, header: String) {
        this.itemList = itemList
        this.newItemsCount = newItemsListCount
        this.header = header
    }
}