public enum StatusLaundry { // enum untuk menyimpan daftar status proses laundry

    DITERIMA, // status awal saat order baru dibuat / diterima admin
    DICUCI, // status ketika laundry sedang dicuci
    DISETRIKA, // status ketika laundry sedang disetrika
    SELESAI, // status ketika laundry sudah selesai diproses
    DIANTAR // status ketika laundry sedang atau sudah diantar ke customer

}