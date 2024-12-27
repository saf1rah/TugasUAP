### BMI Calculator - Aplikasi Java Swing

**Deskripsi**:  
BMI Calculator adalah aplikasi berbasis GUI dengan Java Swing yang menghitung Body Mass Index (BMI) pengguna berdasarkan tinggi, berat, usia, dan jenis kelamin. Aplikasi juga memberikan saran kesehatan sesuai hasil BMI.

### Fitur Utama:
1. **Perhitungan BMI**:  
   Masukkan nama, tinggi, berat, usia, jenis kelamin, dan aktivitas. Hasil berupa nilai BMI, kategori (Kurus, Normal, Gemuk, Obesitas), serta saran kesehatan.

2. **Tabel Hasil**:  
   Menyimpan dan menampilkan semua hasil perhitungan BMI pengguna.

3. **Edit & Hapus Data**:  
   Memungkinkan pengguna memperbarui atau menghapus data yang telah dimasukkan.

4. **Navigasi Antar Halaman**:  
   Menggunakan **CardLayout** untuk berpindah antar halaman input dan hasil.

### Cara Kerja:
- **Perhitungan BMI**:   
  Kategori:
    - Kekurangan Berat: BMI < 18.5
    - Normal: 18.5 ≤ BMI < 25
    - Kelebihan Berat: 25 ≤ BMI < 30
    - Obesitas: BMI ≥ 30

- **Saran Kesehatan**:  
  Berdasarkan kategori BMI, aplikasi memberikan saran seperti meningkatkan kalori atau menjaga pola makan sehat.

### Teknologi:
- Java Swing: GUI
- JTable: Untuk tabel hasil
- JOptionPane: Menampilkan dialog pesan

### Pengembangan:
1. Validasi input lebih ketat.
2. Menambahkan penyimpanan data ke file atau database.
3. Visualisasi data BMI dalam bentuk grafik.

Aplikasi ini berguna untuk menghitung BMI secara cepat dan memberikan saran kesehatan langsung.