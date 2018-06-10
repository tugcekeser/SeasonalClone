package com.example.rabia.seasonalclone.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.rabia.seasonalclone.R
import com.example.rabia.seasonalclone.adapters.ItemListAdapter
import com.example.rabia.seasonalclone.butterknife.bindView
import com.example.rabia.seasonalclone.models.ProduceItem
import com.example.rabia.seasonalclone.models.SeasonalCalifornia
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_main.*
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    val rvItemList: RecyclerView by bindView(R.id.rvItemList)
    var itemList: MutableList<ProduceItem> = mutableListOf<ProduceItem>()
    var newItemsList: List<ProduceItem> = ArrayList<ProduceItem>()
    var fruitList: List<ProduceItem> = ArrayList<ProduceItem>()
    var vegetableList: List<ProduceItem> = ArrayList<ProduceItem>()
    var seasonalCalItemList: List<SeasonalCalifornia> = ArrayList<SeasonalCalifornia>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addItems()

        rvItemList.layoutManager = LinearLayoutManager(this)
        val itemListAdapter = ItemListAdapter(itemList,newItemsList.size, fruitList.size, this)
        rvItemList.adapter = itemListAdapter
    }


    fun addItems() {

        getAllItems()
        getCaliforniaSeasonalItems()
        getCaliforniaItemInJune()
    }


    fun getAllItems() {

        val assetManager = this.assets
        val metadataFile = assetManager.open("seasonal_metadata.json")
        val metadata = metadataFile.readBytes().toString(Charset.defaultCharset())

        val gsonBuilder = GsonBuilder().setPrettyPrinting().create()

        itemList = gsonBuilder.fromJson(metadata, object : TypeToken<List<ProduceItem>>() {}.type)
    }


    fun getCaliforniaSeasonalItems() {

        val assetManager = this.assets
        val metadataFile = assetManager.open("seasonal_california.json")
        val metadata = metadataFile.readBytes().toString(Charset.defaultCharset())

        val gsonBuilder = GsonBuilder().setPrettyPrinting().create()

        seasonalCalItemList =
                gsonBuilder.fromJson(metadata, object : TypeToken<List<SeasonalCalifornia>>() {}.type)
    }


    fun getCaliforniaItemInJune() {

        val calIDs = seasonalCalItemList.filter { item -> item.months?.june == true }.map { item -> item.id }
        val newIds = seasonalCalItemList.filter { item -> item.months?.june == true && item.months?.may == false }.map { item -> item.id }

        newItemsList = itemList.filter { item -> item.id in newIds }
        fruitList = itemList.filter { item -> item.id in calIDs && item.itemType == "Fruit" && !newIds.contains(item.id) }
        vegetableList = itemList.filter { item -> item.id in calIDs && item.itemType != "Fruit" && !newIds.contains(item.id) }

        itemList = ArrayList<ProduceItem>(newItemsList)
        itemList.addAll(fruitList)
        itemList.addAll(vegetableList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

