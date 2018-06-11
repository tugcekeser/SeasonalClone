package com.example.rabia.seasonalclone.views

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.rabia.seasonalclone.R
import com.example.rabia.seasonalclone.adapters.ViewPagerAdapter
import com.example.rabia.seasonalclone.butterknife.bindView
import com.example.rabia.seasonalclone.models.ProduceItem
import com.example.rabia.seasonalclone.models.SeasonalCalifornia
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_main.*
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    //val rvItemList: RecyclerView by bindView(R.id.rvItemList)

    val tabLayout: TabLayout by bindView(R.id.tabLayout)
    val viewPager: ViewPager by bindView(R.id.viewPager)

    var itemList: MutableList<ProduceItem> = mutableListOf<ProduceItem>()
    var newItemListFruit: List<ProduceItem> = ArrayList<ProduceItem>()
    var newItemListVeggie: List<ProduceItem> = ArrayList<ProduceItem>()
    var fruitList: MutableList<ProduceItem> = ArrayList<ProduceItem>()
    var vegetableList: MutableList<ProduceItem> = ArrayList<ProduceItem>()
    var seasonalCalItemList: List<SeasonalCalifornia> = ArrayList<SeasonalCalifornia>()

    var mFruitListFragment: ProductListFragment = ProductListFragment.newInstance()
    var mVeggieListFragment: ProductListFragment = ProductListFragment.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        getItems()
        initWidgets()
    }

    private fun initWidgets() {
        viewPager.setOffscreenPageLimit(1);

        var adapter: ViewPagerAdapter = ViewPagerAdapter(getSupportFragmentManager())
        adapter.addFrag(mFruitListFragment,
                "Fruits");
        adapter.addFrag(mVeggieListFragment,
                "Veggies");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }


    fun getItems() {

        getAllItems()
        getCaliforniaSeasonalItems()
        getCaliforniaItemsInJune()
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


    fun getCaliforniaItemsInJune() {

        val calIDs = seasonalCalItemList.filter { item -> item.months?.june == true }.map { item -> item.id }
        val newIds = seasonalCalItemList.filter { item -> item.months?.june == true && item.months?.may == false }.map { item -> item.id }

        newItemListFruit = itemList.filter { item -> item.id in newIds && item.itemType == "Fruit" }
        fruitList.addAll(newItemListFruit)
        fruitList.addAll(itemList.filter { item -> item.id in calIDs && item.itemType == "Fruit" && !newIds.contains(item.id) })
        mFruitListFragment.initFragment(fruitList, newItemListFruit.size, "Fruits")

        newItemListVeggie = itemList.filter { item -> item.id in newIds && item.itemType != "Fruit" }
        vegetableList.addAll(newItemListVeggie)
        vegetableList.addAll(itemList.filter { item -> item.id in calIDs && item.itemType != "Fruit" && !newIds.contains(item.id) })
        mVeggieListFragment.initFragment(vegetableList, newItemListVeggie.size, "Veggies")

        //itemList = ArrayList<ProduceItem>(newItemsList)
        //itemList.addAll(fruitList)
        //itemList.addAll(vegetableList)
    }
}

