public class Order { // class untuk merepresentasikan data pesanan laundry

    String idOrder; // menyimpan ID unik order
    Customer customer; // menyimpan object customer yang melakukan order
    LaundryService service; // menyimpan layanan yang dipilih
    double berat; // menyimpan berat laundry (kg)
    double harga; // menyimpan total harga
    StatusLaundry status; // menyimpan status laundry (enum)
    boolean sudahBayar; // status apakah sudah dibayar atau belum
    boolean antarJemput; // status apakah menggunakan layanan antar jemput

    // constructor untuk membuat object Order
    public Order(String idOrder, Customer customer, LaundryService service, double berat, boolean antarJemput) {
        this.idOrder = idOrder; // set ID order
        this.customer = customer; // set customer
        this.service = service; // set layanan
        this.berat = berat; // set berat laundry
        this.antarJemput = antarJemput; // set status antar jemput

        this.status = StatusLaundry.DITERIMA; // default status awal = DITERIMA
        this.sudahBayar = false; // default belum bayar

        // ambil harga dari service berdasarkan berat
        this.harga = service.hitungHarga(berat);
    }

    @Override
    public String toString() { // method untuk menampilkan data order dalam bentuk string
        return idOrder + " | " + customer.nama + " | " + service.namaLayanan +
                " | " + berat + "kg | Rp" + harga +
                " | " + status +
                " | Antar: " + (antarJemput ? "Diantar" : "Diambil") + // ternary operator
                " | Bayar: " + (sudahBayar ? "Sudah Bayar" : "Belum Bayar");
    }
}