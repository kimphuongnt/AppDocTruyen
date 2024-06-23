package com.example.kp_home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DBManager {
    private DBSQLite dbHelper;
    private SQLiteDatabase database;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public DBManager(Context context) {
        dbHelper = new DBSQLite(context);
        database = dbHelper.getWritableDatabase();
    }

    public List<Truyen> getAllTruyen() {
        List<Truyen> truyenList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM TRUYEN", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");
                int maTruyen = cursor.getInt(idIndex);
                String tacGia = cursor.getString(authorIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String anhBia = cursor.getString(anhIndex);

                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setAnhBia(anhBia);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }

    public List<Truyen> getTruyenTheoTL(String theloai) {
        List<Truyen> truyenList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Truyen JOIN TheLoai ON Truyen.maTheLoai = TheLoai.maTheLoai WHERE TheLoai.tenTheLoai LIKE ?";
        Cursor cursor = database.rawQuery(sqlQuery, new String[]{"%" + theloai + "%"});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");
                int maTruyen = cursor.getInt(idIndex);
                String tacGia = cursor.getString(authorIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String anhBia = cursor.getString(anhIndex);

                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setAnhBia(anhBia);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }

    public List<Truyen> getTruyenTheoTT(String tinhtrang) {
        List<Truyen> truyenList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Truyen WHERE tinhTrang LIKE ?";
        Cursor cursor = database.rawQuery(sqlQuery, new String[]{"%" + tinhtrang + "%"});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");
                int maTruyen = cursor.getInt(idIndex);
                String tacGia = cursor.getString(authorIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String anhBia = cursor.getString(anhIndex);

                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setAnhBia(anhBia);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }

    public List<Truyen> getTruyenNgan() {
        List<Truyen> truyenList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM TRUYEN where soChuong > 11", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");
                int maTruyen = cursor.getInt(idIndex);
                String tacGia = cursor.getString(authorIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String anhBia = cursor.getString(anhIndex);

                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setAnhBia(anhBia);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }

    public List<Truyen> getNewtruyen(int limit) {
        List<Truyen> truyenList = new ArrayList<>();
        String query = "SELECT * FROM TRUYEN ORDER BY ngayRaMat DESC LIMIT ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(limit)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
                int ngayRaMatIndex = cursor.getColumnIndexOrThrow("ngayRaMat");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");
                int maTruyen = cursor.getInt(idIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String anhBia = cursor.getString(anhIndex);
                String ngayRaMat = cursor.getString(ngayRaMatIndex);
                String tacGia = cursor.getString(authorIndex);
                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setAnhBia(anhBia);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }

    public List<Truyen> searchTruyen(String query) {
        String sqlQuery = null;
        Cursor cursor;
        List<Truyen> truyenList = new ArrayList<>();
        if (Objects.equals(query, "")) {
            sqlQuery = "SELECT * FROM TRUYEN";
            cursor = database.rawQuery("SELECT * FROM TRUYEN", null);
        } else {
            sqlQuery = "SELECT * FROM TRUYEN WHERE tenTruyen LIKE ?";
            cursor = database.rawQuery(sqlQuery, new String[]{"%" + query + "%"});
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");

                int maTruyen = cursor.getInt(idIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String anhBia = cursor.getString(anhIndex);
                String tacGia = cursor.getString(authorIndex);
                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setAnhBia(anhBia);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }

    public List<Truyen> searchTacGia(String query) {
        String sqlQuery = null;
        Cursor cursor;
        List<Truyen> truyenList = new ArrayList<>();
        if (Objects.equals(query, "")) {
            sqlQuery = "SELECT * FROM TRUYEN";
            cursor = database.rawQuery("SELECT * FROM TRUYEN", null);
        } else {
            sqlQuery = "SELECT * FROM TRUYEN WHERE tacGia LIKE ?";
            cursor = database.rawQuery(sqlQuery, new String[]{"%" + query + "%"});
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");

                int maTruyen = cursor.getInt(idIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String anhBia = cursor.getString(anhIndex);
                String tacGia = cursor.getString(authorIndex);
                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setAnhBia(anhBia);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }

    public Truyen getTruyenByMaTruyen(int maTruyen) {
        Truyen truyen = null;
        String sqlQuery = "SELECT * FROM TRUYEN WHERE maTruyen = ?";
        Cursor cursor = database.rawQuery(sqlQuery, new String[]{String.valueOf(maTruyen)});
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
            int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
            int anhIndex = cursor.getColumnIndexOrThrow("anhBia");
            int authorIndex = cursor.getColumnIndexOrThrow("tacGia");
            int ngayRaMatIndex = cursor.getColumnIndexOrThrow("ngayRaMat");
            int soChuongIndex = cursor.getColumnIndexOrThrow("soChuong");
            int tinhTrangIndex = cursor.getColumnIndexOrThrow("tinhTrang");
            int maTheLoaiIndex = cursor.getColumnIndexOrThrow("maTheLoai");

            truyen = new Truyen();
            truyen.setMaTruyen(cursor.getInt(idIndex));
            truyen.setTenTruyen(cursor.getString(nameIndex));
            truyen.setAnhBia(cursor.getString(anhIndex));
            truyen.setTacGia(cursor.getString(authorIndex));
            truyen.setNgayRaMat(cursor.getString(ngayRaMatIndex));
            truyen.setSoChuong(cursor.getInt(soChuongIndex));
            truyen.setTinhTrang(cursor.getString(tinhTrangIndex));
            truyen.setMaTheLoai(cursor.getInt(maTheLoaiIndex));

            cursor.close();
        }
        return truyen;
    }
    public int getMaTheLoaiByTen(String tenTheLoai) {
        int maTheLoai = -1;
        String sqlQuery = "SELECT maTheLoai FROM TheLoai WHERE tenTheLoai = ?";
        Cursor cursor = database.rawQuery(sqlQuery, new String[]{tenTheLoai});
        if (cursor != null && cursor.moveToFirst()) {
            maTheLoai = cursor.getInt(cursor.getColumnIndexOrThrow("maTheLoai"));
            cursor.close();
        }
        return maTheLoai;
    }

    public List<TheLoai> getAllCategoriesAdmin() {
        List<TheLoai> categoryList = new ArrayList<>();
        Cursor cursor = database.query("TheLoai", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int maTheLoai = cursor.getInt(cursor.getColumnIndexOrThrow("maTheLoai"));
                String tenTheLoai = cursor.getString(cursor.getColumnIndexOrThrow("tenTheLoai"));

                TheLoai category = new TheLoai();
                category.setMaTheLoai(String.valueOf(maTheLoai));
                category.setTenTheLoai(tenTheLoai);

                categoryList.add(category);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return categoryList;
    }
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT tenTheLoai FROM TheLoai", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                categories.add(cursor.getString(cursor.getColumnIndexOrThrow("tenTheLoai")));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return categories;
    }

    public User getUser(String username, String password) {
        User user = null;
        String sqlQuery = "SELECT * FROM User WHERE tenDN = ? AND matKhau = ?";
        Cursor cursor = database.rawQuery(sqlQuery, new String[]{username, password});
        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setTenDN(cursor.getString(cursor.getColumnIndexOrThrow("tenDN")));
            user.setMatKhau(cursor.getString(cursor.getColumnIndexOrThrow("matKhau")));
            user.setTenND(cursor.getString(cursor.getColumnIndexOrThrow("tenND")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            user.setDob(cursor.getString(cursor.getColumnIndexOrThrow("dob")));
            cursor.close();
        }
        return user;
    }

    public boolean addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("tenDN", user.getTenDN());
        values.put("matKhau", user.getMatKhau());
        values.put("tenND", user.getTenND());
        values.put("email", user.getEmail());
        values.put("dob", user.getDob());


        long result = database.insert("User", null, values);
        return result != -1;
    }

    public User getUserById(int userId) {
        User user = null;
        String sqlQuery = "SELECT * FROM User WHERE id = ?";
        Cursor cursor = database.rawQuery(sqlQuery, new String[]{String.valueOf(userId)});
        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            user.setTenDN(cursor.getString(cursor.getColumnIndexOrThrow("tenDN")));
            user.setMatKhau(cursor.getString(cursor.getColumnIndexOrThrow("matKhau")));
            user.setTenND(cursor.getString(cursor.getColumnIndexOrThrow("tenND")));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            user.setDob(cursor.getString(cursor.getColumnIndexOrThrow("dob")));

            cursor.close();
        }
        return user;
    }

    public List<ChiTietTruyen> getDanhSachChuong(int maTruyen) {
        List<ChiTietTruyen> chapters = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ChiTietTruyen WHERE matruyen = ?", new String[]{String.valueOf(maTruyen)});
        if (cursor.moveToFirst()) {
            do {
                ChiTietTruyen chapter = new ChiTietTruyen();
                chapter.setMaTruyen(cursor.getString(cursor.getColumnIndexOrThrow("maTruyen")));
                chapter.setMaChuong(cursor.getString(cursor.getColumnIndexOrThrow("maChuong")));
                chapter.setTenChuong(cursor.getString(cursor.getColumnIndexOrThrow("tenChuong")));
                chapter.setNoiDung(cursor.getString(cursor.getColumnIndexOrThrow("noiDung")));
                chapters.add(chapter);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return chapters;
    }

    public ChiTietTruyen getNoiDungChuong(int maTruyen, int maChuong) {
        ChiTietTruyen chapter = null;
        String query = "SELECT * FROM ChiTietTruyen WHERE maTruyen = ? AND maChuong = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maTruyen), String.valueOf(maChuong)});

        if (cursor != null && cursor.moveToFirst()) {
            chapter = new ChiTietTruyen();
            chapter.setMaTruyen(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("maTruyen"))));
            chapter.setMaChuong(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("maChuong"))));
            chapter.setTenChuong(cursor.getString(cursor.getColumnIndexOrThrow("tenChuong")));
            chapter.setNoiDung(cursor.getString(cursor.getColumnIndexOrThrow("noiDung")));
            cursor.close();
        }

        return chapter;
    }

    public boolean addBinhLuan(int maTruyen, int userId, String noiDung) {
        ContentValues values = new ContentValues();
        values.put("maTruyen", maTruyen);
        values.put("userId", userId);
        values.put("noiDung", noiDung);
        values.put("ngayBinhLuan", dateFormat.format(System.currentTimeMillis()));

        long result = database.insert("BinhLuan", null, values);
        return result != -1;
    }

    public List<BinhLuan> getBinhLuan(int maTruyen) {
        List<BinhLuan> binhLuanList = new ArrayList<>();
        String query = "SELECT BinhLuan.*, User.tenND FROM BinhLuan JOIN User ON BinhLuan.userID = User.id WHERE BinhLuan.maTruyen = ? ORDER BY BinhLuan.ngayBinhLuan DESC";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maTruyen)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                BinhLuan binhLuan = new BinhLuan();
                binhLuan.setMaTruyen(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("maTruyen"))));
                binhLuan.setUserID(cursor.getInt(cursor.getColumnIndexOrThrow("userID")));
                binhLuan.setTenND(cursor.getString(cursor.getColumnIndexOrThrow("tenND"))); // Set the user name
                binhLuan.setNoiDung(cursor.getString(cursor.getColumnIndexOrThrow("noiDung")));
                binhLuan.setNgayBinhLuan(cursor.getString(cursor.getColumnIndexOrThrow("ngayBinhLuan")));

                binhLuanList.add(binhLuan);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return binhLuanList;
    }


    public boolean addLike(int userId, int maTruyen, boolean isLike) {
        ContentValues values = new ContentValues();
        values.put("userID", userId);
        values.put("maTruyen", maTruyen);
        values.put("isLike", isLike);

        Cursor cursor = database.query("Like", null, "userID = ? AND maTruyen = ?", new String[]{String.valueOf(userId), String.valueOf(maTruyen)}, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();

        if (exists) {
            int rowsAffected = database.update("Like", values, "userID = ? AND maTruyen = ?", new String[]{String.valueOf(userId), String.valueOf(maTruyen)});
            return rowsAffected > 0;
        } else {
            long result = database.insert("Like", null, values);
            return result != -1;
        }
    }

    public int getSoLike(int maTruyen) {
        String query = "SELECT COUNT(*) FROM `Like` WHERE maTruyen = ? AND isLike = 1";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maTruyen)});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getSoDislike(int maTruyen) {
        String query = "SELECT COUNT(*) FROM `Like` WHERE maTruyen = ? AND isLike = 0";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maTruyen)});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public boolean addTruyenDaLuu(int userId, int maTruyen) {
        ContentValues values = new ContentValues();
        values.put("userID", userId);
        values.put("maTruyen", maTruyen);

        long result = database.insertWithOnConflict("TruyenDaLuu", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        return result != -1;
    }

    public boolean checkTruyenDaLuu(int userId, int maTruyen) {
        String query = "SELECT 1 FROM TruyenDaLuu WHERE userID = ? AND maTruyen = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(maTruyen)});
        boolean isSaved = cursor.moveToFirst();
        cursor.close();
        return isSaved;
    }

    public List<TruyenDaLuu> getTruyenDaLuu(int userId) {
        List<TruyenDaLuu> savedStories = new ArrayList<>();
        String query = "SELECT tdl.id, tdl.userID, tdl.maTruyen, t.tenTruyen " +
                "FROM TruyenDaLuu tdl " +
                "JOIN Truyen t ON tdl.maTruyen = t.maTruyen " +
                "WHERE tdl.userID = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int maTruyen = cursor.getInt(cursor.getColumnIndexOrThrow("maTruyen"));
                String tenTruyen = cursor.getString(cursor.getColumnIndexOrThrow("tenTruyen"));
                savedStories.add(new TruyenDaLuu(id, userId, maTruyen, tenTruyen));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return savedStories;
    }

    public boolean deleteTruyenDaLuu(TruyenDaLuu truyenDaLuu) {
        int result = database.delete("TruyenDaLuu", "id = ?", new String[]{String.valueOf(truyenDaLuu.getId())});
        return result > 0;
    }

    public void updateComment(int maTruyen, int userID, String ngayBinhLuan, String newNoiDung) {
        String updateQuery = "UPDATE BinhLuan SET noiDung = ? WHERE maTruyen = ? AND userID = ? AND ngayBinhLuan = ?";
        database.execSQL(updateQuery, new Object[]{newNoiDung, maTruyen, userID, ngayBinhLuan});
    }


    public List<Truyen> getBangXepHangtruyen() {
        List<Truyen> truyenList = new ArrayList<>();
        String query = "SELECT truyen.*, COUNT(`Like`.isLike) as likeCount " +
                "FROM truyen " +
                "LEFT JOIN `Like` ON truyen.maTruyen = `Like`.maTruyen AND `Like`.isLike = 1 " +
                "GROUP BY truyen.maTruyen " +
                "ORDER BY likeCount DESC";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("maTruyen");
                int nameIndex = cursor.getColumnIndexOrThrow("tenTruyen");
                int authorIndex = cursor.getColumnIndexOrThrow("tacGia");
                int maTruyen = cursor.getInt(idIndex);
                String tenTruyen = cursor.getString(nameIndex);
                String tacGia = cursor.getString(authorIndex);

                Truyen truyen = new Truyen();
                truyen.setMaTruyen(maTruyen);
                truyen.setTenTruyen(tenTruyen);
                truyen.setTacGia(tacGia);

                truyenList.add(truyen);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return truyenList;
    }


    public boolean updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("tenDN", user.getTenDN());
        values.put("tenND", user.getTenND());
        values.put("email", user.getEmail());
        values.put("dob", user.getDob());

        int rowsAffected = database.update("User", values, "id = ?", new String[]{String.valueOf(user.getId())});
        return rowsAffected > 0;
    }

    public boolean addTruyen(Truyen truyen) {
        ContentValues values = new ContentValues();
        values.put("tenTruyen", truyen.getTenTruyen());
        values.put("anhBia", truyen.getAnhBia());
        values.put("tacGia", truyen.getTacGia());
        values.put("ngayRaMat", truyen.getNgayRaMat());
        values.put("soChuong", truyen.getSoChuong());
        values.put("tinhTrang", truyen.getTinhTrang());
        values.put("maTheLoai", truyen.getMaTheLoai());

        long result = database.insert("Truyen", null, values);
        return result != -1;
    }

    public boolean updateTruyen(Truyen truyen) {
        ContentValues values = new ContentValues();
        values.put("tenTruyen", truyen.getTenTruyen());
        values.put("anhBia", truyen.getAnhBia());
        values.put("tacGia", truyen.getTacGia());
        values.put("ngayRaMat", truyen.getNgayRaMat());
        values.put("soChuong", truyen.getSoChuong());
        values.put("tinhTrang", truyen.getTinhTrang());
        values.put("maTheLoai", truyen.getMaTheLoai());

        int rowsAffected = database.update("Truyen", values, "maTruyen = ?", new String[]{String.valueOf(truyen.getMaTruyen())});
        return rowsAffected > 0;
    }

    public boolean deleteTruyen(int maTruyen) {
        int rowsAffected = database.delete("Truyen", "maTruyen = ?", new String[]{String.valueOf(maTruyen)});
        return rowsAffected > 0;
    }

    public boolean addTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("tenTheLoai", theLoai.getTenTheLoai());

        long result = database.insert("TheLoai", null, values);
        return result != -1;
    }

    public boolean updateTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("tenTheLoai", theLoai.getTenTheLoai());

        int rowsAffected = database.update("TheLoai", values, "maTheLoai = ?", new String[]{String.valueOf(theLoai.getMaTheLoai())});
        return rowsAffected > 0;
    }

    public boolean deleteTheLoai(int maTheLoai) {
        int rowsAffected = database.delete("TheLoai", "maTheLoai = ?", new String[]{String.valueOf(maTheLoai)});
        return rowsAffected > 0;
    }

    public boolean addChiTietTruyen(ChiTietTruyen chiTietTruyen) {
        ContentValues values = new ContentValues();
        values.put("maTruyen", chiTietTruyen.getMaTruyen());
        values.put("maChuong", chiTietTruyen.getMaChuong());
        values.put("tenChuong", chiTietTruyen.getTenChuong());
        values.put("noiDung", chiTietTruyen.getNoiDung());

        long result = database.insert("ChiTietTruyen", null, values);
        return result != -1;
    }

    public boolean updateChiTietTruyen(ChiTietTruyen chiTietTruyen) {
        ContentValues values = new ContentValues();
        values.put("maTruyen", chiTietTruyen.getMaTruyen());
        values.put("maChuong", chiTietTruyen.getMaChuong());
        values.put("tenChuong", chiTietTruyen.getTenChuong());
        values.put("noiDung", chiTietTruyen.getNoiDung());

        int rowsAffected = database.update("ChiTietTruyen", values, "maTruyen = ? AND maChuong = ?", new String[]{String.valueOf(chiTietTruyen.getMaTruyen()), String.valueOf(chiTietTruyen.getMaChuong())});
        return rowsAffected > 0;
    }

    public boolean deleteChiTietTruyen(int maTruyen, int maChuong) {
        int rowsAffected = database.delete("ChiTietTruyen", "maTruyen = ? AND maChuong = ?", new String[]{String.valueOf(maTruyen), String.valueOf(maChuong)});
        return rowsAffected > 0;
    }


    public boolean deleteBinhLuan(int maTruyen, int userID, String ngayBinhLuan) {
        int rowsAffected = database.delete("BinhLuan", "maTruyen = ? AND userID = ? AND ngayBinhLuan = ?", new String[]{String.valueOf(maTruyen), String.valueOf(userID), ngayBinhLuan});
        return rowsAffected > 0;
    }


}
