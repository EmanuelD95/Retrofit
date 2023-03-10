package com.emanuel.retrofit.ui.list_resources.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.retrofit.databinding.ItemListResourcesBinding
import com.emanuel.retrofit.model.Resource

class ResourcesViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemListResourcesBinding.bind(view)

    @SuppressLint("SetTextI18n", "ResourceType")
    fun render(context: Context, resource: Resource, onClickListener:(Resource) -> Unit){
        binding.tvName.text = resource.name
        binding.tvId.text = "ID: ${resource.id}"
        binding.vCircle.setBackgroundColor(Color.parseColor(resource.color)) //= ContextCompat.getDrawable(context, Color.parseColor("#bdbdbd")) //Color.parseColor("#bdbdbd") //"${resource.color}"
        //binding.vCircle.setBackgroundResource(Color.parseColor(resource.color))

        itemView.setOnClickListener { onClickListener(resource) }
    }
}