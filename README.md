# Temfore: Mobile Development

Temfore is a mobile application developed to provide food recommendations based on the air temperature at the user's location. The app suggests recipes with ingredients and cooking instructions tailored to the current weather, making it easier for users to decide what to cook based on the temperature. The application integrates weather data and machine learning to generate personalized recommendations.

[![Flow Chart](https://img.shields.io/badge/Figma-Flow%20Chart-F24E1E?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/board/kk1GoJZcXCeFLDyCRNRiCg/Project-Plan?node-id=1-357&t=FSVm0qZaa2Je8hZn-1)  
[![Desain UI/Wireframe](https://img.shields.io/badge/Figma-Desain%20UI%2FWireframe-F24E1E?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/design/ZN2GS2mXkTM5ssUM3Kr4IW/Project-Capstone?node-id=0-1&t=YvxR49v6gj2VTm4m-1)

## C242-PS547

### Team Members

| Status   | Path | Name                         | Bangkit ID     | University                    | LinkedIn                                                                        | GitHub                                        |
| -------- | ---- | ---------------------------- | -------------- | ----------------------------- | ------------------------------------------------------------------------------- | --------------------------------------------- |
| `Active` | (CC) | Nurminati Hasnatul Khatimah  | `C247B4KX2427` | Universitas Lambung Mangkurat | [LinkedIn](https://www.linkedin.com/in/nurminati-hasnatul-khatimah-704b69244/)  | [GitHub](https://github.com/minacloe)         |
| `Active` | (CC) | Muhammad Adji Maulana Putera | `C247B4KY2686` | Universitas Lambung Mangkurat | [LinkedIn](https://www.linkedin.com/in/muhammad-adji-maulana-putera-514066252/) | [GitHub](https://github.com/adjimaulanap)     |
| `Active` | (ML) | Balladiva Mahesi             | `M008B4KX0804` | Universitas Gadjah Mada       | [LinkedIn](https://www.linkedin.com/in/balladiva-mahesi-428a16256/)             | [GitHub](https://github.com/bldv)             |
| `Active` | (ML) | Alif Rahmatullah Lesmana     | `M668B4KY0386` | STMIK Palangka Raya           | [LinkedIn](https://www.linkedin.com/in/alif-rahmatullah-lesmana-565028311/)     | [GitHub](https://github.com/Peparrepair)      |
| `Active` | (ML) | Muhammad Raffa Saputra       | `M668B4KY2987` | STMIK Palangka Raya           | [LinkedIn](https://www.linkedin.com/in/muhammad-raffa-saputra21/)               | [GitHub](https://github.com/21YeetYa)         |
| `Active` | (MD) | Riyan Fazri Rahman           | `A668B4KY3894` | STMIK Palangka Raya           | [LinkedIn](https://www.linkedin.com/in/riyan-fazri-rahman/)                     | [GitHub](https://github.com/riyanfazrirahman) |

---

## Features

- **Weather-based food recommendations**: The app suggests food recipes based on the temperature of the user's current location.
- **User authentication**: Users can log in using Firebase Authentication.
- **Profile management**: Users can manage their profiles, which include saved favorite recipes.
- **Recipe search**: Users can search for recipes based on ingredients or recipe names.
- **Favorite recipes**: Users can mark their favorite recipes to view later.
- **Machine Learning-based Recommendations**: The app uses machine learning models to generate accurate food suggestions.
- **Meal Reminder Notifications**: The app provides timely reminders for meals using WorkManager:

| Time Range         | Meal         | Description             |
|---------------------|--------------|-------------------------|
| **6:00 AM - 9:00 AM** | Breakfast    | Reminder to have breakfast. |
| **11:00 AM - 1:00 PM**| Lunch        | Reminder to have lunch.     |
| **6:00 PM - 8:00 PM** | Dinner       | Reminder to have dinner.    |

![image](img/ss_app.png)

## Kode Penting

### `getLastLocation()`

```kotlin
fusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task: Task<Location?> ->
    if (task.isSuccessful) {
        val location = task.result
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            fetchWeatherByCoordinates(latitude, longitude) 
        } 
    }
}
```
Functionality:
- Retrieves the user's last known location using fusedLocationClient.
- Passes the coordinates (latitude, longitude) to fetchWeatherByCoordinates() if the location is successfully fetched.
---
### `fetchWeatherByCoordinates(latitude: Double, longitude: Double)`

```kotlin
private fun fetchWeatherByCoordinates(latitude: Double, longitude: Double) {
    Log.d(TAG, "API request Weather......................")
    weatherViewModel.fetchWeatherByCoordinates(latitude, longitude)
    fetchFoodRecommendations()
}
```
Functionality:
- Logs the start of the weather API request.
- Fetches weather data using weatherViewModel.
- Calls fetchFoodRecommendations() to generate food recommendations based on weather data.
---
### `fetchFoodRecommendations()`

```kotlin
private fun fetchFoodRecommendations() {
    Log.d(TAG, "API request Recommend......................")
    recommendationViewModel.fetchRecommendations(categoryUser, tempUser, timeUser)
}
```
Functionality:

- Logs the start of the recommendation API request.
- Fetches food recommendations using user preferences (`categoryUser`) and weather data (`tempUser`, `timeUser`).
---
### `scheduleNotification()` & `showNotification()`

```kotlin
private fun scheduleNotification(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES).build()
    WorkManager.getInstance(context).enqueueUniquePeriodicWork("NotificationWork1", ExistingPeriodicWorkPolicy.KEEP, workRequest)
}
```
```kotlin
private fun showNotification(context: Context) {
    val calendar = Calendar.getInstance()
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    val message = when (currentHour) {
        in 6..9 -> "Sarapan Pagi!🥪"
        in 11..13 -> "Makan Siang!🧺"
        in 18..20 -> "Makan Malam!🍴"
        else -> { return }
    }
}
```
Functionality:
- Schedules periodic notifications using WorkManager.
- Ensures the notification is triggered every 15 minutes (or at specified intervals).
- Determines the current time and displays a notification with an appropriate message.
---

# Perbandingan Kelebihan Temfore

| **Fitur Utama**                         | **Temfore** | **Aplikasi Serupa** | **Keterangan**                                                                                                   |
|-----------------------------------------|-------------|----------------------|-------------------------------------------------------------------------------------------------------------------|
| **Rekomendasi Berdasarkan Cuaca**       | ✔           | ✘                    | Temfore menggunakan cuaca real-time, sedangkan aplikasi serupa tidak mempertimbangkan kondisi cuaca.             |
| **Integrasi Machine Learning**          | ✔           | ✘                    | Temfore memanfaatkan ML untuk rekomendasi yang personal, sementara aplikasi lain sering hanya menggunakan logika. |
| **Fitur Notifikasi Pengingat Makan**    | ✔           | ✘                    | Pengingat makan (sarapan, makan siang, makan malam) pada waktu tertentu tidak tersedia di aplikasi serupa.        |
| **Pencarian Resep**                     | ✔           | ✔                    | Kedua aplikasi mendukung pencarian resep, tetapi Temfore lebih fleksibel karena memungkinkan pencarian bahan.     |
| **Favorit Resep**                       | ✔           | ✘                    | Resep favorit di Temfore dapat diakses offline, sedangkan aplikasi lain sering membutuhkan koneksi internet.     |
| **Antarmuka Pengguna (UI/UX)**          | ✔           | ✘                    | Temfore memiliki desain modern dan intuitif, sedangkan banyak aplikasi serupa memiliki antarmuka yang kompleks.   |
| **Efisiensi API**                       | ✔           | ✘                    | Temfore menggabungkan pemanggilan API cuaca dan rekomendasi, sedangkan aplikasi lain memisahkan proses tersebut.  |
| **Fitur Login dan Profil Pengguna**     | ✔           | ✘                    | Temfore mendukung login dengan Firebase dan pengelolaan profil, sementara banyak aplikasi lain berbasis lokal.    |

## Kesimpulan
Temfore unggul dalam relevansi rekomendasi berbasis cuaca, efisiensi API, serta fitur tambahan seperti notifikasi dan personalisasi melalui profil pengguna.
