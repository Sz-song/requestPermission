package com.song.permission

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PermissionAdapter(private val list: List<PermissionBean>,private val context: Context) :
    RecyclerView.Adapter<PermissionAdapter.ViewHolder>() {
    var onItemClickListener:OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PermissionAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_permission, parent, false)
        )

    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.item?.text=list[position].permissionName
        holder.item?.setOnClickListener {
            onItemClickListener?.onCallback(position)
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var item: TextView? = null
        init {
            item=view.findViewById(R.id.item)
        }
    }
    interface OnItemClickListener{
        fun onCallback(position:Int)
    }
}