/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Uzair Arshad
 */
public class Book {

    // Instance variables
    String title;
    String author;
    double price;

    // Parameterized Constructor
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    // toString method
    @Override
    public String toString() {
        return "Book Title: " + title +
               "\nAuthor: " + author +
               "\nPrice: $" + price;
    }
}

class BookDemo {

    public static void main(String[] args) {

        // Creating Book object
        Book book = new Book(
                "Developing Java Software",
                "Russel Winder",
                79.75
        );

        // Printing book details
        System.out.println(book);
    }
}