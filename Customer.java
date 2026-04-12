import java.util.ArrayList; 

public class Customer extends User { // Customer mewarisi User
    String alamat, password; // menyimpan alamat customer
    
    ArrayList<Order> riwayat = new ArrayList<>(); // menyimpan riwayat order customer

    // constructor untuk inisialisasi object Customer
    public Customer(String id, String nama, String noHp, String alamat, String password) {
        super(id, nama, noHp); // memanggil constructor dari class User
        this.alamat = alamat; // mengisi alamat customer
        this.password = password;
    }

    // override method toString untuk menampilkan data customer
    @Override
    public String toString() {
        return id + " | " + nama + " | " + noHp + " | " + alamat + "|" + password; // format tampilan data
    }
}