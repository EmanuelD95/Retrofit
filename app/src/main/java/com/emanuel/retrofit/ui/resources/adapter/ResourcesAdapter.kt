package com.emanuel.retrofit.ui.resources.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.retrofit.R
import com.emanuel.retrofit.model.Resource

class ResourcesAdapter(private val listResources: List<Resource>, private val onClickListener:(Resource) -> Unit): RecyclerView.Adapter<ResourcesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourcesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ResourcesViewHolder(layoutInflater.inflate(R.layout.item_list_resources,parent, false))
    }

    override fun onBindViewHolder(holder: ResourcesViewHolder, position: Int) {
        val item = listResources[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return listResources.size
    }
}