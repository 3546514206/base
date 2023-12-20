package edu.zjnu.c8.bookpackext;

import edu.zjnu.c8.bookpack.Book2;

// Use the Book2 class from bookpack.
public class UseBook {
    public static void main(String[] args) {
        Book2[] books = new Book2[5];

        books[0] = new Book2("Java: A Beginner's Guide", "Schildt", 2018);
        books[1] = new Book2("Java: The Complete Reference", "Schildt", 2018);
        books[2] = new Book2("Introducing JavaFX 8 Programming", "Schildt", 2015);
        books[3] = new Book2("Red Storm Rising", "Clancy", 1986);
        books[4] = new Book2("On the Road", "Kerouac", 1955);

        for (Book2 x : books) {
            x.show();
        }
    }
}
