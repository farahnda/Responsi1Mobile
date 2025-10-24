package com.example.responsi1mobileh1d023095

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023095.data.model.Player
import com.example.responsi1mobileh1d023095.databinding.ActivityTeamSquadBinding
import com.example.responsi1mobileh1d023095.ui.adapter.TeamAdapter
import com.example.responsi1mobileh1d023095.ui.fragment.TeamDetailFragment
import com.example.responsi1mobileh1d023095.viewmodel.TeamViewModel

class TeamSquadActivity : AppCompatActivity(), TeamAdapter.OnItemClickListener {

    private lateinit var binding: ActivityTeamSquadBinding
    private val viewModel: TeamViewModel by viewModels()

    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamSquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TeamAdapter(emptyList())

        adapter.setOnItemClickListener(this) // 'this' merujuk ke Activity ini

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.teamSquad.observe(this) { squad ->
            adapter.updateData(squad)
        }
    }

    override fun onItemClick(
        player: Player,
        backgroundColor: Int
    ) {
        val fragment = TeamDetailFragment.newInstance(player, backgroundColor)
        fragment.show(supportFragmentManager, "TeamDetailFragment")    }
}
