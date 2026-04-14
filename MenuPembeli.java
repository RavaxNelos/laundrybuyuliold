
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPembeli {

    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    // pengecekan akun customer setelah login
    public static void main(String[] args, Customer userLogin) {

        Scanner sc = new Scanner(System.in);
        String password = "";
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<LaundryService> services = new ArrayList<>();

        // isi layanan
        services.add(new LaundryService("SRV1", "Cuci Basah", 5000, 2, true, false));
        services.add(new LaundryService("SRV2", "Cuci Kering", 6000, 2, true, false));
        services.add(new LaundryService("SRV3", "Setrika", 4000, 1, false, true));
        services.add(new LaundryService("SRV4", "Cuci + Setrika", 7000, 2, true, true));
        services.add(new LaundryService("SRV5", "Cuci Karpet", 8000, 3, true, false));
        services.add(new LaundryService("SRV6", "Cuci Bed Cover", 10000, 3, true, false));

        // jalankan menu pembeli
        customerManage.loadCustomerDariFile(customers);
        customerManage.loadDataDariFile(orders, services, customers);
        int pilih = -1;

        do {
            // System.out.println("Login sebagai: " + userLogin.nama);
            System.out.println("\n=== MENU PEMBELI ===");
            System.out.println("1. Pesan Laundry");
            System.out.println("2. Konfirmasi Pembayaran");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            if (!sc.hasNextInt()) {
                System.out.println("Harus angka!");
                sc.next();
                continue;
            }

            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {

                case 1:
                    // PILIH LAYANAN
                    System.out.println("\n--- DAFTAR LAYANAN ---");
                    for (LaundryService ls : services) {
                        System.out.println(ls.idService + " - " + ls.namaLayanan + " (Rp" + ls.hargaPerKg + "/kg)");
                    }
                    LaundryService layananDipilih = null;
                    do {
                        System.out.print("Masukkan Kode Layanan (contoh: SRV1): ");
                        String kodeInput = sc.nextLine().trim();

                        for (LaundryService ls : services) {
                            // equalsIgnoreCase agar tidak peduli besar/kecil huruf
                            if (ls.idService.equalsIgnoreCase(kodeInput)) {
                                layananDipilih = ls;
                                break;
                            }
                        }

                        if (layananDipilih == null) {
                            System.out.println("Kode layanan tidak ditemukan! Silakan coba lagi.");
                        }
                    } while (layananDipilih == null);
                    // BERAT
                    double berat = -1;
                    do {
                        System.out.print("Berat (kg): ");

                        if (!sc.hasNextDouble()) {
                            System.out.println("Harus angka!");
                            sc.next();
                            continue;
                        }

                        berat = sc.nextDouble();
                        sc.nextLine();

                        if (berat <= 0) {
                            System.out.println("Harus > 0!");
                        }

                    } while (berat <= 0);

                    // ANTAR JEMPUT
                    String input;
                    boolean antar;

                    do {
                        System.out.print("Antar Jemput (y/n): ");
                        input = sc.nextLine();

                        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("ya")) {
                            antar = true;
                            break;
                        } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                            antar = false;
                            break;
                        } else {
                            System.out.println("Input salah!");
                        }

                    } while (true);

                    if (antar) {
                        System.out.println("Akan dikirim kurir");
                    } else {
                        System.out.println("Aja sendiri");
                    }

                    // BUAT ORDER
                    Order o = new Order("ORD" + (orders.size() + 1),
                            userLogin,
                            layananDipilih,
                            berat,
                            antar);

                    orders.add(o);
                    customerManage.simpanOrder(o); // simpan ke file

                    System.out.println("Order berhasil dibuat!");
                    break;
                case 2:
                    if (orders.isEmpty()) {
                        System.out.println("Tidak ada data order ditemukan di file!");
                        break;
                    }

                    System.out.println("\n--- DAFTAR SEMUA ORDER ---");
                    for (Order ord : orders) {
                        if (ord.customer.nama.equalsIgnoreCase(userLogin.nama)) {
                            System.out.printf("ID: %-7s | Nama: %-10s | Total: Rp%-8s | [%s]\n",
                                    ord.idOrder, ord.customer.nama, ord.harga,
                                    (ord.sudahBayar ? "Sudah Bayar" : "Belum Bayar"));
                        }

                    }

                    Order orderKetemu = null;
                    do {
                        System.out.print("\nMasukkan ID Order yang ingin dibayar (atau ketik 'batal'): ");
                        String idInput = sc.nextLine().trim();

                        if (idInput.equalsIgnoreCase("batal")) {
                            break;
                        }

                        for (Order ord : orders) {
                            if (ord.idOrder.equalsIgnoreCase(idInput)) {
                                orderKetemu = ord;
                                break;
                            }
                        }

                        if (orderKetemu == null) {
                            System.out.println("ID Order tidak ditemukan!");
                        } else if (orderKetemu.sudahBayar) {
                            System.out.println("Order ini sudah lunas.");
                            orderKetemu = null; // reset agar loop lagi atau keluar
                        }
                    } while (orderKetemu == null);

                    if (orderKetemu != null) {
                        orderKetemu.sudahBayar = true;
                        customerManage.updateFileOrder(orders); // Simpan ke orders.txt
                        System.out.println("Pembayaran ID " + orderKetemu.idOrder + " Berhasil Dikonfirmasi!");
                    }
                    break;
            }

        } while (pilih != 0);
        loginDashboard.main(null);

    }
}
