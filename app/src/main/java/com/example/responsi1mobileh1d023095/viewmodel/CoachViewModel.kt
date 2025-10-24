package com.example.responsi1mobileh1d023095.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023095.data.model.Coach
import com.example.responsi1mobileh1d023095.data.network.RetrofitInstance
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class CoachViewModel : ViewModel() {

    private val _coach = MutableLiveData<Coach>()
    val coach: LiveData<Coach> = _coach

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchCoachData()
    }

    private fun fetchCoachData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTeamData()
                _coach.postValue(response.coach)
                Log.d("CoachVM", "Sukses: Data coach ${response.coach.name} berhasil dimuat.")
            } catch (e: Exception) {
                _error.postValue("Gagal memuat data: ${e.message}")
                Log.e("CoachVM", "Error API: Gagal memuat data!", e)
            }
        }
    }

    fun formatBirthDate(dateStr: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateStr)
            date?.let { outputFormat.format(it) } ?: dateStr
        } catch (e: Exception) {
            dateStr
        }
    }
}