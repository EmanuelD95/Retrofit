package com.emanuel.retrofit.ui.list_users.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.retrofit.R
import com.emanuel.retrofit.model.User

class UserAdapter(private val userList: List<User>, private val onClickListener:(User) -> Unit): RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.item_user,parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}