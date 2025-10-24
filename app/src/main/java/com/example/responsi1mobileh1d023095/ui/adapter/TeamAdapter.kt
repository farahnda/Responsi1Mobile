package com.example.responsi1mobileh1d023095.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023095.databinding.ListTeamSquadBinding
import com.example.responsi1mobileh1d023095.data.model.Player
import androidx.core.graphics.toColorInt

class TeamAdapter(private var squadList: List<Player>) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(val binding: ListTeamSquadBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ListTeamSquadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    interface OnItemClickListener {
        fun onItemClick(player: Player, backgroundColor: Int)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val player = squadList[position]
        val b = holder.binding

        b.tvName.text = player.name
        b.tvNationality.text = player.nationality

        val position = player.position?.lowercase() ?: ""

        val bgColor = when {
            position.contains("goalkeeper") -> "#FFD600".toColorInt() // Kuning

            position.contains("defender") || position.contains("defence") || position.contains("back") -> "#2196F3".toColorInt()   // Biru

            position.contains("midfield") -> "#4CAF50".toColorInt() // Hijau

            position.contains("forward") || position.contains("offence") || position.contains("winger") -> "#F44336".toColorInt() // Merah

            else -> "#E0E0E0".toColorInt()
        }

        b.itemCard.setBackgroundColor(bgColor)

        b.itemCard.setOnClickListener {
            listener?.onItemClick(player, bgColor)
        }
    }

    override fun getItemCount(): Int = squadList.size

    fun updateData(newList: List<Player>) {
        squadList = newList
        notifyDataSetChanged()
    }
}
