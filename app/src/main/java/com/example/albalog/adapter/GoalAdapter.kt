package com.example.albalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albalog.R
import com.example.albalog.model.GoalItem

class GoalAdapter(private val items: List<GoalItem>) :
    RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    inner class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtGoalName: TextView = itemView.findViewById(R.id.txtGoalName)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_goal, parent, false)
        return GoalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val item = items[position]
        holder.txtGoalName.text = item.name
        holder.progressBar.progress = item.progress
    }

    override fun getItemCount() = items.size
}