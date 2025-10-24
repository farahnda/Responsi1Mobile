package com.example.responsi1mobileh1d023095

import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
import com.example.responsi1mobileh1d023095.databinding.ActivityClubHistoryBinding

class ClubHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClubHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvWelcome.text = getString(R.string.judul)
        binding.tvDescription.text = getString(R.string.deskripsi)
        binding.imgHistory.setImageResource(R.drawable.history)

//        binding.imgHistory.setOnClickListener {
            // Misal nanti mau tampilkan toast atau navigate
//        }
    }
}