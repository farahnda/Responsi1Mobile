package com.example.responsi1mobileh1d023095

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.example.responsi1mobileh1d023095.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initLayout()
        initListener()
    }

    private fun initLayout() {
        binding.layoutClubHistory.let {
            it.imgIcon.setImageResource(R.drawable.ic_clubhistory)
            it.tvLayout.setText(R.string.clubhistory)
        }
        binding.layoutHeadCoach.let {
            it.imgIcon.setImageResource(R.drawable.ic_headcoach)
            it.tvLayout.setText(R.string.headcoach)
        }

        binding.layoutTeamSquad.let {
            it.imgIcon.setImageResource(R.drawable.ic_teamsquad)
            it.tvLayout.setText(R.string.teamsquad)
        }
    }

    private fun initListener() {
        binding.layoutClubHistory.root.setOnClickListener {
            val intent = Intent(this, ClubHistoryActivity::class.java)
            startActivity(intent)
        }
        binding.layoutHeadCoach.root.setOnClickListener {
            val intent = Intent(this, HeadCoachActivity::class.java)
            startActivity(intent)
        }
        binding.layoutTeamSquad.root.setOnClickListener {
            val intent = Intent(this, TeamSquadActivity::class.java)
            startActivity(intent)
        }
    }
}