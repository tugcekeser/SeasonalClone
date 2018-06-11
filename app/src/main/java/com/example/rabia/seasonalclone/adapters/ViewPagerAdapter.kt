package com.example.rabia.seasonalclone.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {

    var mFragmentList: MutableList<Fragment> = mutableListOf<Fragment>()
    var mFragmentTitleList: MutableList<String> = mutableListOf<String>()


    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position);
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }
}