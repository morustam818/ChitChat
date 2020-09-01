package com.rmohd8788.chitchat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rmohd8788.chitchat.adapters.PagerAdapter
import com.rmohd8788.chitchat.fragments.ChatFragment
import com.rmohd8788.chitchat.fragments.UserFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var currentUserId : FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout,viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getText(R.string.chats)
                }

                1 -> {
                    tab.text = resources.getText(R.string.users)
                }
            }
        }.attach()
    }
    private fun status(status : String){
        currentUserId = FirebaseAuth.getInstance().currentUser!!
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentUserId.uid)
        val updateStatus = HashMap<String,String>()
        updateStatus["status"] = status
        databaseReference.updateChildren(updateStatus as Map<String, Any>)

    }

    override fun onRestart() {
        super.onRestart()
        status("online")
    }

    override fun onPause() {
        super.onPause()
        status("offline")
    }
}
