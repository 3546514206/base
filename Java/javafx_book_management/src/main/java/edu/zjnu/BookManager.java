package edu.zjnu;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @Date 2024-09-29 22:49
 * @Author 杨海波
 **/
public class BookManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/book_management";
    private static final String DB_USER = "root"; // 根据需要修改
    private static final String DB_PASSWORD = "Zic200716"; // 根据需要修改

    public void addBook(String title, String author) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (title, author) VALUES (?, ?)")) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
