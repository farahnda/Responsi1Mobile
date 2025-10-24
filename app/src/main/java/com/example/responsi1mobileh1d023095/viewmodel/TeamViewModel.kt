package com.example.responsi1mobileh1d023095.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023095.data.model.Player
import com.example.responsi1mobileh1d023095.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val _teamSquad = MutableLiveData<List<Player>>()
    val teamSquad: LiveData<List<Player>> = _teamSquad

    init {
        fetchTeamSquad()
    }

    fun fetchTeamSquad() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTeamData()
                _teamSquad.value = response.squad
            } catch (e: Exception) {
                _teamSquad.value = emptyList()
            }
        }
    }
}
