package com.utkusarican.moviesapplication.features.details.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.utkusarican.moviesapplication.features.details.ui.model.ViewPagerItem


class ViewPagerAdapter(fa : FragmentActivity, private val viewPagerItemList : List<ViewPagerItem>) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return viewPagerItemList.size
    }

    override fun createFragment(position: Int): Fragment {
        return viewPagerItemList[position].fragment
    }


}