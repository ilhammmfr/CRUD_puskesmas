import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistem CRUD Puskesmas ===");
            System.out.println("1. Tambah Data Puskesmas");
            System.out.println("2. Lihat Semua Data Puskesmas");
            System.out.println("3. Perbarui Data Puskesmas");
            System.out.println("4. Hapus Data Puskesmas");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (pilihan) {
                case 1 -> tambahPuskesmas(scanner);
                case 2 -> tampilkanPuskesmas();
                case 3 -> perbaruiPuskesmas(scanner);
                case 4 -> hapusPuskesmas(scanner);
                case 5 -> {
                    System.out.println("Keluar dari program. Terima kasih!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    private static void tambahPuskesmas(Scanner scanner) {
        System.out.print("Masukkan nama puskesmas: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat puskesmas: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan nomor telepon: ");
        String telepon = scanner.nextLine();

        String query = "INSERT INTO puskesmas (nama, alamat, telepon) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.setString(3, telepon);
            stmt.executeUpdate();
            System.out.println("Data berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void tampilkanPuskesmas() {
        String query = "SELECT * FROM puskesmas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n=== Daftar Puskesmas ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nama: " + rs.getString("nama"));
                System.out.println("Alamat: " + rs.getString("alamat"));
                System.out.println("Telepon: " + rs.getString("telepon"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void perbaruiPuskesmas(Scanner scanner) {
        System.out.print("Masukkan ID puskesmas yang akan diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Membersihkan newline

        System.out.print("Masukkan nama baru: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat baru: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan nomor telepon baru: ");
        String telepon = scanner.nextLine();

        String query = "UPDATE puskesmas SET nama = ?, alamat = ?, telepon = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.setString(3, telepon);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Data berhasil diperbarui!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void hapusPuskesmas(Scanner scanner) {
        System.out.print("Masukkan ID puskesmas yang akan dihapus: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM puskesmas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Data berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
