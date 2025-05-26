package com.example.albalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albalog.R
import com.example.albalog.model.AlbaItem

class AlbaAdapter(private val items: List<AlbaItem>) :
    RecyclerView.Adapter<AlbaAdapter.AlbaViewHolder>() {

    inner class AlbaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtGoalName: TextView = itemView.findViewById(R.id.txtAlbaName)
        val txtAlbaTime: TextView = itemView.findViewById(R.id.txtAlbaTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alba, parent, false)
        return AlbaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbaViewHolder, position: Int) {
        val item = items[position]
        holder.txtGoalName.text = item.name
        holder.txtAlbaTime.text = item.time
    }

    override fun getItemCount() = items.size
}