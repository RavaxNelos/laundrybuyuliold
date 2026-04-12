import java.util.ArrayList; // import class ArrayList untuk menyimpan banyak data
import java.util.Scanner; // import Scanner untuk input dari user

public class MainLaundry { // class utama program
    public static void main(String[] args) { // method utama (entry point program)

        Scanner sc = new Scanner(System.in); // membuat object Scanner untuk input
        ArrayList<Customer> customers = new ArrayList<>(); // list untuk menyimpan data customer
        ArrayList<Order> orders = new ArrayList<>(); // list untuk menyimpan data order
        ArrayList<LaundryService> services = new ArrayList<>(); // list menyimpan layanan laundry

        // menambahkan data layanan laundry
        services.add(new LaundryService("SRV1", "Cuci Basah", 5000, 2));
        services.add(new LaundryService("SRV2", "Cuci Kering", 6000, 2));
        services.add(new LaundryService("SRV3", "Setrika", 4000, 1));
        services.add(new LaundryService("SRV4", "Cuci + Setrika", 7000, 2));
        services.add(new LaundryService("SRV5", "Karpet", 8000, 3));
        services.add(new LaundryService("SRV6", "Bed Cover", 10000, 3));

        // membuat object Admin (inherit dari User)
        Admin admin = new Admin("ADM1", "Admin Utama", "08123456789", customers, orders);

        // membuat object Courier (inherit dari User)
        Courier kurir = new Courier("CR1", "Kurir 1", "08129876543", orders);

        // load data dari file .txt ke dalam ArrayList
        customerManage.loadDataDariFile(orders, services, customers);

        int menu = -1; // variabel untuk menyimpan pilihan menu

        do { // perulangan menu
            System.out.println("\n=== ADMIN LAUNDRY BU YULI ===");
            System.out.println("1. Lihat Order");
            System.out.println("2. Lihat Customer");
            System.out.println("3. Update Status");
            System.out.println("4. Kirim Laundry");
            System.out.println("0. Keluar");

            System.out.print("Pilih: ");

            // validasi input agar hanya angka
            if (!sc.hasNextInt()) {
                System.out.println("Input harus berupa angka!");
                sc.next(); // buang input yang salah
                continue; // ulangi menu
            }

            menu = sc.nextInt(); // ambil input menu
            sc.nextLine(); // buang newline

            switch (menu) { // percabangan menu

                case 1:
                    // cek list order
                    if (orders.isEmpty()) { // cek apakah order kosong
                        System.out.println("Belum ada order di sistem!");
                        break;
                    }
                    adminManage.lihatOrderDariFile(); // tampilkan data order dari file
                    break;

                case 2:
                    customerManage.loadCustomerDariFile(customers); // isi dulu list

                    // cek list customer
                    if (customers.isEmpty()) {
                        System.out.println("Belum ada customer di sistem!");
                        break;
                    }

                    // tampilkan dari ArrayList
                    for (Customer c : customers) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    // update status laundry
                    if (orders.isEmpty()) { // cek apakah order kosong
                        System.out.println("Belum ada order di sistem!");
                        break;
                    }

                    adminManage.lihatOrderDariFile(); // tampilkan data order
                    System.out.print("Masukkan ID Order: ");
                    String idUpdate = sc.nextLine().trim(); // input ID

                    Order orderUpdate = null; // variabel untuk menyimpan order

                    // mencari order berdasarkan ID
                    for (Order o : orders) {
                        if (o.idOrder.equalsIgnoreCase(idUpdate)) {
                            orderUpdate = o;
                            break;
                        }
                    }

                    if (orderUpdate != null) { // jika ditemukan
                        int idx = orders.indexOf(orderUpdate); // ambil index
                        admin.updateStatus(idx); // update status lewat admin

                        adminManage.updateFileOrder(orders); // simpan ke file
                        System.out.println("Status Berhasil Diperbarui!");
                    } else {
                        System.out.println("ID Order tidak ditemukan!");
                    }
                    break;

                case 4:
                    // kirim laundry
                    if (orders.isEmpty()) {
                        System.out.println("Belum ada order di sistem!");
                        break;
                    }

                    adminManage.lihatOrderDariFile(); // tampilkan data
                    System.out.print("Masukkan ID Order: ");
                    String idKirim = sc.nextLine().trim(); // input ID

                    Order orderKirim = null;

                    // cari order berdasarkan ID
                    for (Order o : orders) {
                        if (o.idOrder.equalsIgnoreCase(idKirim)) {
                            orderKirim = o;
                            break;
                        }
                    }

                    if (orderKirim != null) {
                        int idx = orders.indexOf(orderKirim); // ambil index

                        boolean berhasil = kurir.kirimOrder(idx); // proses kirim

                        if (berhasil) {
                            adminManage.updateFileOrder(orders); // simpan ke file
                            System.out.println("Konfirmasi Pengiriman Berhasil!");
                        }

                    } else {
                        System.out.println("ID Order tidak ditemukan!");
                    }
                    break;
            }

        } while (menu != 0); // ulang sampai pilih 0 (keluar)
        sc.close();
    }

    
}