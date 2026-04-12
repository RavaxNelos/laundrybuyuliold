
import java.util.ArrayList;
import java.util.Scanner;

public class loginDashboard {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();
        String regID = "", passLogin, confirmPass = "", nameLogin = "", noReg = "", nameReg = "", passReg = "",
                alamat = "";
        String namaUser = "", passUser = "";
        boolean login = false;
        boolean error = false;
        int choose;
        customerManage.loadCustomerDariFile(customers);
        do {
            System.out.println("=== Login Dashboard Laundry Bu Yuli ===");
            System.out.println("1. Register\n2. Login\n0. Keluar");
            System.out.print("Choose : ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                // case 0:
                // System.out.println("Keluar!");
                // break;
                case 1:
                    do {
                        System.out.print("Masukkan Nama       : ");
                        nameReg = sc.nextLine();
                        System.out.print("Masukkan Nomor HP   : ");
                        noReg = sc.nextLine();
                        System.out.print("Masukkan Alamat     : ");
                        alamat = sc.nextLine();
                        System.out.print("Masukkan Password   : ");
                        passReg = sc.nextLine();
                        System.out.print("Konfirmasi Password : ");
                        confirmPass = sc.nextLine();
                    } while (!passReg.equals(confirmPass));
                    // AUTO ID
                    int nextNum = customers.isEmpty() ? 1 : customers.size() + 1;
                    regID = "CUST" + nextNum;

                    Customer cust = new Customer(regID, nameReg, noReg, alamat, passReg);
                    customers.add(cust);
                    customerManage.simpanCustomer(cust);
                    System.out.println("Pendaftaran berhasil!");
                    break;
                case 2:
                    Customer loginUser = null;

                    do {
                        System.out.print("Masukkan Nama       : ");
                        nameLogin = sc.nextLine();
                        System.out.print("Masukkan Password   : ");
                        passLogin = sc.nextLine();
                        loginUser = customerManage.verifLogin(customers, nameLogin, passLogin);
                        if (loginUser != null) {
                            login = true;
                            System.out.println("Berhasil Login");
                            break;
                        } else {
                            System.out.println("Login gagal! Coba lagi.\n");
                            break;
                        }
                    } while (!login);
                    try {
                        
                    } catch (Exception e) {
                        // TODO: handle exception
                        

                    }
                    if (!error && login) {
                        // MASUK KE MENU PEMBELI
                        System.out.println("Login sebagai: " + loginUser.nama);
                        MenuPembeli.main(null, loginUser);
                        break;
                    }

            }

        } while (choose != 0);
        System.exit(choose);
        sc.close();
    }
}
