//package com.example.responsi1mobileh1d023095.viewmodel
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.responsi1mobileh1d023095.data.model.SearchResponse
//import com.example.responsi1mobileh1d023095.data.remote.RetrofitInstance
//import kotlinx.coroutines.launch
//
//class MainViewModel : ViewModel() {
//
//    // LiveData untuk menyimpan hasil API
//    val teamData = MutableLiveData<SearchResponse?>()
//    val isLoading = MutableLiveData<Boolean>()
//    val errorMessage = MutableLiveData<String?>()
//
//    // Fungsi untuk mengambil data dari API
//    fun getTeamDetails(teamId: Int) {
//        viewModelScope.launch {
//            isLoading.value = true
//            try {
//                val response = RetrofitInstance.api.getTeamDetails(teamId)
//                if (response.isSuccessful) {
//                    teamData.value = response.body()
//                } else {
//                    errorMessage.value = "Gagal mengambil data: ${response.message()}"
//                }
//            } catch (e: Exception) {
//                errorMessage.value = "Terjadi kesalahan: ${e.localizedMessage}"
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
//}