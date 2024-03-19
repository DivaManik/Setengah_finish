# SistemManajemenKehadiranSiswaMiniProject
Sistem Manajemen Kehadiran Siswa adalah sistem untuk membantu sekolah mengelola catatan kehadiran mereka secara efisien. Sistem ini menyediakan antarmuka sederhana dengan menggunakan console untuk navigasi menu dan interaksi. Sistem ini menggunakan SQLite sebagai database  untuk menyimpan org.kehadiransiswa.data kehadiran dengan aman. Sistem ini menekankan prinsip-prinsip Pemrograman Berorientasi Objek (OOP) untuk memastikan modularitas kode, reuseability, dan pemeliharaan.

## Requirement
1. **Otentikasi & otorisasi Pengguna**:  Menerapkan mekanisme otentikasi (username & password) dan otorisasi dasar sehingga untuk dapat menggunakan sistem, user harus menggunakan kredensial mereka (username dan password). Sistem juga harus memiliki Role yang berbeda, yaitu ADMIN, STUDENT, dan TEACHER. Role ADMIN dapat menggunakan semua fitur sistem, TEACHER tidak dapat menggunakan fitur manajemen pengguna, dan STUDENT hanya dapat melakukan Pencatatan Kehadiran. 
2. **Laporan Kehadiran**: Menghasilkan laporan kehadiran untuk masing-masing siswa atau seluruh kelas. Laporan harus mencakup persentase kehadiran, jumlah kelas yang dihadiri, dan jumlah kelas yang diselenggarakan. 
3. **Persistensi Data course, user, class room dan attendance record**: Gunakan SQLite sebagai database backend untuk menyimpan akun pengguna, kursus, kelas, siswa, dan catatan kehadiran. Pastikan desain database dan integritas org.kehadiransiswa.data yang tepat. 
4. **Validasi**: Menerapkan validasi input untuk memastikan integritas semua data dan mencegah kesalahan. Validasi dilakukan saat menginput data seperti email, format tanggal, waktu dan sebagainya. 
5. **Penanganan Kesalahan**: Tangani pengecualian dengan baik untuk memberikan pengalaman pengguna yang baik. Menampilkan pesan kesalahan yang berarti untuk skenario umum (misalnya, kredensial masuk tidak valid, kesalahan saat memasukan email saat registrasi).

## Desain
Berikut adalah desain class diagram aplikasi yang akan dibuat.  
![plot](/img/classDiagram.png)