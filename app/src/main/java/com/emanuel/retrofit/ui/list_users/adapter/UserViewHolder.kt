package com.emanuel.retrofit.ui.list_users.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emanuel.retrofit.databinding.ItemUserBinding
import com.emanuel.retrofit.model.User

class UserViewHolder(view: View):RecyclerView.ViewHolder(view){
    val binding = ItemUserBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(user: User, onClickListener:(User) -> Unit){
        binding.tvName.text = "${user.firstName} ${user.lastName}"
        binding.tvId.text = "ID: ${user.id.toString()}"

        Glide.with(binding.ivAvatar.context)
            .load(user.avatar)
            .circleCrop()
            .into(binding.ivAvatar)

        itemView.setOnClickListener { onClickListener(user) }
    }



}