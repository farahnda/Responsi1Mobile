package com.example.responsi1mobileh1d023095

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023095.viewmodel.CoachViewModel
import com.example.responsi1mobileh1d023095.databinding.ActivityHeadCoachBinding

class HeadCoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeadCoachBinding

    private val coachViewModel: CoachViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()

        Handler(Looper.getMainLooper()).postDelayed({
            binding.layoutInfo.animate()
                .alpha(1f)
                .setDuration(500)
                .start()
        }, 1000)
    }

    private fun observeViewModel() {
        coachViewModel.coach.observe(this) { coach ->

            Log.d("CoachActivity", "Observer coach DIPANGGIL. Mengatur nama: ${coach.name}")
            binding.tvName.text = coach.name
            binding.tvBirth.text = coachViewModel.formatBirthDate(coach.dateOfBirth)

            binding.tvCountry.text = coach.nationality
        }

        coachViewModel.error.observe(this) { errorMessage ->
            Log.e("CoachActivity", "Observer error DIPANGGIL: $errorMessage")
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}