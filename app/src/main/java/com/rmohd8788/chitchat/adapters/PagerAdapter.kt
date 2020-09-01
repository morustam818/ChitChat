package com.rmohd8788.chitchat.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rmohd8788.chitchat.fragments.ChatFragment
import com.rmohd8788.chitchat.fragments.UserFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position==0){
            ChatFragment()
        }else{
            UserFragment()
        }
    }
}