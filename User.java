public class User { // class induk (parent) untuk Admin, Courier, Customer

    protected String id; // menyimpan ID user (bisa admin, kurir, atau customer)
    protected String nama; // menyimpan nama user
    protected String noHp; // menyimpan nomor HP user

    // constructor untuk mengisi data user saat object dibuat
    public User(String id, String nama, String noHp) {
        this.id = id; // set ID
        this.nama = nama; // set nama
        this.noHp = noHp; // set nomor HP
    }

    // method untuk menampilkan informasi user
    public void tampilInfo() {
        System.out.println(id + " | " + nama + " | " + noHp);
    }
}