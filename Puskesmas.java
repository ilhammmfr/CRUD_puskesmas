public class Puskesmas {
    private int id;
    private String nama;
    private String alamat;
    private String telepon;

    public Puskesmas(int id, String nama, String alamat, String telepon) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public Puskesmas(String nama, String alamat, String telepon) {
        this(0, nama, alamat, telepon);
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelepon() {
        return telepon;
    }
}

