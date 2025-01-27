package com.example.task3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

data class ActivityType(val iconResId: Int, val name: String)

class StartActivityAdapter(private val items: List<ActivityType>,
private val onClick: (ActivityType) -> Unit
) : RecyclerView.Adapter<StartActivityAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.activityIcon)
        val name: TextView = view.findViewById(R.id.activityName)

        init {
            view.setOnClickListener {
                if (selectedPosition == adapterPosition) {
                    lastSelectedPosition = selectedPosition
                    notifyItemChanged(lastSelectedPosition)
                    selectedPosition = -1
                    lastSelectedPosition = -1

                } else {
                    lastSelectedPosition = selectedPosition
                    selectedPosition = adapterPosition
                    notifyItemChanged(lastSelectedPosition)
                    notifyItemChanged(selectedPosition)
                    onClick(items[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.start_activity_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.icon.setImageResource(item.iconResId)
        holder.name.text = item.name
        holder.itemView.isSelected = position == selectedPosition

        if (position == selectedPosition) {
            holder.itemView.background = ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.ic_app_background_border
            )
        } else {
            holder.itemView.background = ContextCompat.getDrawable(
                holder.itemView.context,
                R.drawable.ic_app_background_border
            )

        }
    }
    override fun getItemCount() = items.size

}