import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public class CRUD_23090023_A_2025 {

    public static void main(String[] args) {
        createData();    // Menambahkan data
        updateData();    // Memperbarui data
        deleteData();    // Menghapus data
        searchData();    // Mencari data
        readData();      // Menampilkan seluruh data
        notes();         // Menampilkan catatan fungsi
    }

    // Fungsi untuk menambahkan data ke MongoDB
    public static void createData() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("contoh_database");
        MongoCollection<Document> collection = database.getCollection("contoh_koleksi");

        // Hapus data lama agar tidak duplikat saat dijalankan berkali-kali
        collection.drop();

        Document doc1 = new Document("nama", "Andi")
                .append("umur", 25)
                .append("pekerjaan", "Programmer");

        Document alamat = new Document("jalan", "Jl. Mawar")
                .append("kota", "Bandung");

        Document doc2 = new Document("nama", "Budi")
                .append("alamat", alamat)
                .append("hobi", List.of("membaca", "berenang"));

        Document spesifikasi = new Document("RAM", "16GB")
                .append("CPU", "Intel i7")
                .append("storage", List.of("SSD 512GB", "HDD 1TB"));

        Document doc3 = new Document("produk", "Laptop")
                .append("spesifikasi", spesifikasi)
                .append("stok", 12)
                .append("tersedia", true);

        collection.insertMany(List.of(doc1, doc2, doc3));
        System.out.println("✅ Data berhasil ditambahkan ke MongoDB.");
    }

    // Fungsi untuk memperbarui data
    public static void updateData() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("contoh_database");
        MongoCollection<Document> collection = database.getCollection("contoh_koleksi");

        Document filter = new Document("nama", "Andi");
        Document update = new Document("$set", new Document("umur", 30));

        collection.updateOne(filter, update);
        System.out.println("🔄 Data 'Andi' berhasil diperbarui (umur menjadi 30).");
    }

    // Fungsi untuk menghapus data berdasarkan nama
    public static void deleteData() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("contoh_database");
        MongoCollection<Document> collection = database.getCollection("contoh_koleksi");

        Bson filter = Filters.eq("nama", "Budi");
        collection.deleteOne(filter);
        System.out.println("🗑️ Data dengan nama 'Budi' berhasil dihapus.");
    }

    // Fungsi untuk mencari data berdasarkan nama
    public static void searchData() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("contoh_database");
        MongoCollection<Document> collection = database.getCollection("contoh_koleksi");

        Bson filter = Filters.eq("nama", "Andi");
        FindIterable<Document> results = collection.find(filter);

        System.out.println("\n🔍 Hasil pencarian untuk nama 'Andi':");
        for (Document doc : results) {
            System.out.println(doc.toJson());
        }
    }

    // Fungsi untuk menampilkan seluruh data
    public static void readData() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("contoh_database");
        MongoCollection<Document> collection = database.getCollection("contoh_koleksi");

        FindIterable<Document> documents = collection.find();

        System.out.println("\n📦 Data saat ini di koleksi MongoDB:");
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }
    }

    // Fungsi untuk menampilkan catatan tentang fungsi-fungsi
    public static void notes() {
        System.out.println("\n📘 Catatan Fungsi Program:");
        System.out.println("✅ createData() : Menambahkan dokumen ke koleksi MongoDB.");
        System.out.println("🔄 updateData() : Memperbarui field tertentu (contoh: umur Andi).");
        System.out.println("🗑️ deleteData() : Menghapus dokumen berdasarkan nama.");
        System.out.println("🔍 searchData() : Mencari dokumen tertentu (contoh: nama = Andi).");
        System.out.println("📦 readData()   : Menampilkan seluruh dokumen dari koleksi.");
        System.out.println("📘 notes()      : Menampilkan catatan fungsi program.");
    }
}
