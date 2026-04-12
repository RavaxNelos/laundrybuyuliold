
import java.util.ArrayList;

public class adminManage {

    // method untuk membaca data customer dari file
    

    // method untuk membaca data order dari file
    static void lihatOrderDariFile() {
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.FileReader("orders.txt"));

            String line;
            System.out.println("\n=== DATA ORDER ===");

            // header tabel
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-7s | %-12s | %-15s | %-7s | %-10s | %-12s | %-12s | %-10s\n",
                    "ID", "Customer", "Layanan", "Berat", "Harga", "Status", "Pembayaran", "Antar");
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------");

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|"); // split data

                // tampilkan data order dalam bentuk tabel
                System.out.printf("%-7s | %-12s | %-15s | %-5skg | Rp%-8s | %-12s | %-12s | %-10s\n",
                        data[0], data[1], data[2], data[3],
                        data[4], data[5], data[6], data[7]);
            }

            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------");

            br.close();

        } catch (Exception e) {
            System.out.println("Gagal membaca file order!");
        }
    }

    // method untuk update file order
    static void updateFileOrder(ArrayList<Order> orders) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("orders.txt", false); // overwrite file

            for (Order o : orders) { // loop semua order
                String statusBayarTeks = o.sudahBayar ? "Sudah Bayar" : "Belum Bayar";
                String statusAntarTeks = o.antarJemput ? "Diantar" : "Diambil";

                // simpan ke file dengan format tertentu
                fw.write(o.idOrder + "|"
                        + o.customer.nama + "|"
                        + o.service.namaLayanan + "|"
                        + o.berat + "|"
                        + o.harga + "|"
                        + o.status + "|"
                        + statusBayarTeks + "|"
                        + statusAntarTeks + "\n");
            }

            fw.close(); // tutup file

        } catch (Exception e) {
            System.out.println("Gagal memperbarui file database!");
        }
    }
}
