
public class LaundryService {

    String idService;
    String namaLayanan;
    double hargaPerKg;
    int estimasiHari;
    boolean butuhCuci;
    boolean butuhSetrika;

    // Constructor
    public LaundryService(String idService, String namaLayanan, double hargaPerKg, int estimasiHari,
            boolean butuhCuci, boolean butuhSetrika) {
        this.idService = idService;
        this.namaLayanan = namaLayanan;
        this.hargaPerKg = hargaPerKg;
        this.estimasiHari = estimasiHari;
        this.butuhCuci = butuhCuci;
        this.butuhSetrika = butuhSetrika;
    }

    // Method hitung harga
    public double hitungHarga(double berat) {
        return berat * hargaPerKg;
    }

    // Method tampilkan data
    public String toString() {
        return idService + " | " + namaLayanan
                + " | Rp" + hargaPerKg + "/kg"
                + " | Estimasi: " + estimasiHari + " hari";
    }
}
