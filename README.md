Nama: Farah Tsani Maulida  
NIM: H1D023095  
Shift Lama: D  
Shift Baru: F  

# Responsi 1 Pemrograman Mobile
Demo Aplikasi:  
![Responsi_1](https://github.com/user-attachments/assets/f00188e1-9541-4856-a631-e4c8c4bace68)

# Penjelasan Alur Data
Alur data dimulai saat membuka TeamSquadActivity. Saat Activity dibuat, ia langsung menginisialisasi TeamViewModel menggunakan by viewModels(). Begitu TeamViewModel dibuat untuk pertama kalinya, blok init-nya segera berjalan dan memanggil fungsi fetchTeamSquad(). Fungsi ini kemudian beralih ke thread latar belakang (viewModelScope) untuk menjalankan panggilan jaringan melalui RetrofitInstance.api.getTeamData(). Retrofit mengambil alih, mengirimkan permintaan HTTP GET ke endpoint statis "teams/524" di base URL football-data.org, sambil menyertakan header "X-Auth-Token".

Setelah server merespons dengan data JSON, Retrofit (melalui GsonConverterFactory) secara otomatis mem-parsing JSON tersebut ke dalam data class SearchResponse yang telah ditentukan. Di dalam blok try, TeamViewModel menerima objek SearchResponse ini, mengekstrak daftar pemain (response.squad), dan segera mem-posting-nya ke MutableLiveData (_teamSquad). Di sisi lain, TeamSquadActivity sudah "mengamati" (observe) viewModel.teamSquad. Begitu LiveData ini diperbarui, kode di dalam observer Activity langsung terpicu, yang kemudian memanggil adapter.updateData(squad).

TeamAdapter sekarang memiliki data baru dan memberi tahu RecyclerView untuk me-refresh tampilannya. RecyclerView meminta adapter untuk membuat atau memperbarui setiap item di layar melalui onBindViewHolder. Di sinilah data player (seperti player.name dan player.nationality) diatur ke TextView yang sesuai. Selain itu, logika when { position.contains(...) } berjalan untuk menentukan bgColor berdasarkan posisi pemain, yang kemudian diterapkan sebagai warna latar belakang card item tersebut. Terakhir, onBindViewHolder juga memasang setOnClickListener pada setiap card.

Saat user mengeklik salah satu card, listener di dalam adapter akan memanggil fungsi interface onItemClick. Karena TeamSquadActivity meng-implementasi interface ini (TeamAdapter.OnItemClickListener), fungsi onItemClick di dalam Activity-lah yang dieksekusi. Fungsi ini mengambil data player dan bgColor dari item yang diklik, lalu membuat instance baru dari TeamDetailFragment menggunakan newInstance, membungkus kedua data tersebut ke dalam arguments Fragment. fragment.show() kemudian dipanggil, dan TeamDetailFragment muncul sebagai bottom sheet (sesuai pengaturan onStart() di Fragment). Di dalam onViewCreated-nya TeamDetailFragment, ia membongkar arguments tersebut untuk mengambil data player (untuk mengisi tvName, tvDateOfBirth, dll.) dan bgColor (untuk mengatur warna border/stroke), sementara warna background utamanya diatur ke putih.
