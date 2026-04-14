import java.util.ArrayList;

public class Admin extends User {// Admin mewarisi User
    ArrayList<Customer> customers;
    ArrayList<Order> orders;

    // constructor Admin
    public Admin(String id, String nama, String noHp,
            ArrayList<Customer> customers, ArrayList<Order> orders) {
        super(id, nama, noHp); // memanggil constructor User
        this.customers = customers; // inisialisasi data customer
        this.orders = orders; // inisialisasi data order
    }

    // method untuk melihat daftar order
    public void lihatOrder() {
        if (orders.isEmpty()) {
            System.out.println("Belum ada order!");
            return;
        }

        System.out.println("=== DAFTAR ORDER ===");

        for (int i = 0; i < orders.size(); i++) {
            System.out.println(i + ". " + orders.get(i));
        }
    }

    // method untuk update status laundry
    public void updateStatus(int index) {
        Order o = orders.get(index);

        switch (o.status) {
            case DITERIMA:
                if (o.service.butuhCuci) {
                    o.status = StatusLaundry.DICUCI;
                } else if (o.service.butuhSetrika) {
                    o.status = StatusLaundry.DISETRIKA;
                }
                break;

            case DICUCI:
                if (o.service.butuhSetrika) {
                    o.status = StatusLaundry.DISETRIKA;
                } else {
                    o.status = StatusLaundry.SELESAI;
                }
                break;

            case DISETRIKA:
                o.status = StatusLaundry.SELESAI;
                break;

            case SELESAI:
                System.out.println("Laundry sudah selesai!");
                return;
        }

        System.out.println("Status sekarang: " + o.status);
    }
}