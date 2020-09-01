package com.rmohd8788.chitchat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rmohd8788.chitchat.R
import com.rmohd8788.chitchat.model.Users
import kotlinx.android.synthetic.main.user_list.view.*

class UsersAdapter(private val context: Context,private val userList: List<Users>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list,parent,
        false))
    }

    override fun onBindViewHolder(holder: UsersAdapter.UserViewHolder, position: Int) {
        val listPosition = userList[position]
        if (listPosition.imageUrl == "default"){
            holder.itemView.iv_userProfile.resources.getResourceName(R.mipmap.ic_launcher_round)
        }else{
            Glide.with(context).load(listPosition.imageUrl).into(holder.itemView.iv_userProfile)
        }

        holder.itemView.tv_username.text = listPosition.username

    }

    override fun getItemCount() = userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

}