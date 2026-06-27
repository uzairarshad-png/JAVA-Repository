/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DANISH LAPTOP
 */
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class SmartLibrarySystem_Combined {
 static void sep(String t) {
 System.out.println();
 System.out.println();
 System.out.println();
 }
 public static void main(String[] args) {
 System.out.println("SMART LIBRARY SYSTEM - DEMO");
 Library lib = new Library("Bahria University Library");
 Librarian librarian = new Librarian("L001","Ms. Ayesha Khan","ayesha@fast.edu.pk","STAFF-99");
 Student s1 = new Student ("S001","Uzair Arshad", "uzair@bu.edu.pk","IT", 2);
 Student s2 = new Student ("S002","Hamza", "Hamza@bu.edu.pk", "CS", 3);
 Faculty faculty = new Faculty ("F001","Dr. Abdullah", "abdullah@bahria.edu.pk","CS & IT","Sr. Ass Prof");
 sep("DASHBOARDS [Polymorphism]");
 LibraryUser[] users = { librarian, s1, s2, faculty };
 for (LibraryUser u : users) u.showDashboard();
 sep("LIBRARIAN ADDS BOOKS [Role-Specific]");
 Book b1 = new Book("978-001","Clean Code", "Robert C. Martin");
 Book b2 = new Book("978-002","Design Patterns", "GoF");
 Book b3 = new Book("978-003","The C Programming Lang","K & R");
 Book b4 = new Book("978-004","Head First Java", "Sierra & Bates");
 librarian.addBook(b1, lib); librarian.addBook(b2, lib);
 librarian.addBook(b3, lib); librarian.addBook(b4, lib);
 lib.printInventory();
 sep("BORROWING");
 s1.borrowBook(b1, lib); s1.borrowBook(b2, lib); s1.borrowBook(b3, lib);
 s1.borrowBook(b4, lib); // exceeds quota
 s2.borrowBook(b1, lib); // already taken
 s2.borrowBook(b4, lib);
 faculty.borrowBook(b3, lib);
 faculty.requestReservation(b1);
 sep("RETURNING");
 s1.returnBook(b1, lib);
 s1.returnBook(b2, lib);
 sep("BORROW HISTORIES [Tracking]");
 for (LibraryUser u : users) u.printBorrowHistory();
 sep("FULL LOG [Librarian Only]");
 librarian.viewAllBorrowRecords(lib);
 sep("REMOVE BOOK [Librarian Only]");
 librarian.removeBook("978-003", lib);
 lib.printInventory();
 }
}
class Book {
 private String isbn, title, author;
 private boolean isAvailable;
 public Book(String isbn, String title, String author) {
 this.isbn = isbn; this.title = title;
 this.author = author; this.isAvailable = true;
 }
 public String getIsbn() { return isbn; }
 public String getTitle() { return title; }
 public String getAuthor() { return author; }
 public boolean isAvailable() { return isAvailable; }
 public void setAvailable(boolean v) { isAvailable = v; }
 @Override
 public String toString() {
 return String.format("[%s] \"%s\" by %s — %s",
         isbn, title, author, isAvailable ? "Available" : "Borrowed");
 }
}
class BorrowRecord {
 private String bookIsbn, bookTitle, userId;
 private LocalDate borrowDate, returnDate;
 public BorrowRecord(String bookIsbn, String bookTitle, String userId) {
 this.bookIsbn = bookIsbn; this.bookTitle = bookTitle;
 this.userId = userId; this.borrowDate = LocalDate.now();
 }
 public void markReturned() { this.returnDate = LocalDate.now(); }
 public boolean isReturned() { return returnDate != null; }
 public String getBookIsbn() { return bookIsbn; }
 @Override
 public String toString() {
 String ret = isReturned() ? "Returned: " + returnDate : "Not yet returned";
 return String.format(" Book: %-30s | Borrowed: %s | %s",
 "\"" + bookTitle + "\"", borrowDate, ret);
 }
}
class Library {
 private String name;
 private List<Book> inventory = new ArrayList<>();
 private List<BorrowRecord> globalLog = new ArrayList<>();
 public Library(String name) { this.name = name; }
 public void addBook(Book b) { inventory.add(b); }
 public boolean removeBook(String isbn) { return inventory.removeIf(b ->
b.getIsbn().equals(isbn)); }
 public void addRecord(BorrowRecord r){ globalLog.add(r); }
 public Book findBook(String isbn) {
 return inventory.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
 }
 public void printInventory() {
 System.out.println("\n Inventory of " + name + ":");
 if (inventory.isEmpty()) System.out.println(" (empty)");
 else inventory.forEach(b -> System.out.println(" " + b));
 }
 public void printAllRecords() {
 if (globalLog.isEmpty()) System.out.println(" (no records)");
 else globalLog.forEach(System.out::println);
 }
}
abstract class LibraryUser {
    private String userId, name, email;
 private List<BorrowRecord> borrowHistory = new ArrayList<>();
 public LibraryUser(String userId, String name, String email) {
 this.userId = userId; this.name = name; this.email = email;
 }
 public String getUserId() { return userId; }
 public String getName() { return name; }
 public String getEmail() { return email; }
 public List<BorrowRecord> getBorrowHistory() { return borrowHistory; }
 public void borrowBook(Book book, Library library) {
 if (!book.isAvailable()) {
 System.out.println(" ✗ \"" + book.getTitle() + "\" is not available."); return;
 }
 book.setAvailable(false);
 BorrowRecord r = new BorrowRecord(book.getIsbn(), book.getTitle(), userId);
 borrowHistory.add(r);
 library.addRecord(r);
 System.out.println(" ✓ " + name + " borrowed \"" + book.getTitle() + "\"");
 }
 public void returnBook(Book book, Library library) {
 for (BorrowRecord r : borrowHistory) {
 if (r.getBookIsbn().equals(book.getIsbn()) && !r.isReturned()) {
 r.markReturned(); book.setAvailable(true);
 System.out.println(" ✓ " + name + " returned \"" + book.getTitle() + "\""); return;
 }
 }
 System.out.println(" ✗ No active borrow record for \"" + book.getTitle() + "\"");
 }
 public void printBorrowHistory() {
 System.out.println("\n Borrow History — " + name + " (" + getRole() + "):");
 if (borrowHistory.isEmpty()) System.out.println(" (none)");
 else borrowHistory.forEach(System.out::println);
 }
 public abstract String getRole();
 public abstract void showDashboard();
}
class Student extends LibraryUser {
 private String major;
 private int semester;
 public Student(String id, String name, String email, String major, int sem) {
 super(id, name, email); this.major = major; this.semester = sem;
 }
 @Override public String getRole() { return "Student"; }
 public int remainingQuota() {
 long active = getBorrowHistory().stream().filter(r -> !r.isReturned()).count();
 return (int)(3 - active);
 }
 @Override
 public void borrowBook(Book book, Library library) {
 if (remainingQuota() <= 0) {
 System.out.println(" ✗ " + getName() + " has reached the student limit (3 books)."); return;
 }
 super.borrowBook(book, library);
 }
 @Override
 public void showDashboard() {
 System.out.println("\nSTUDENT DASHBOARD");
 System.out.println(" Name : " + getName() + "\n Major : " + major +
 "\n Semester: " + semester + "\n Quota : " + remainingQuota() + " remaining");
 System.out.println();
 }
}
class Faculty extends LibraryUser {
 private String department, designation;
 public Faculty(String id, String name, String email, String dept, String desig) {
 super(id, name, email); this.department = dept; this.designation = desig;
 }
 @Override public String getRole() { return "Faculty"; }
 public void requestReservation(Book book) {
 if (book.isAvailable())
 System.out.println(" ✓ \"" + book.getTitle() + "\" is already available — no reservation needed.");
 else
 System.out.println(" ✓ Reservation placed for \"" + book.getTitle() + "\" by " + getName());
 }
 @Override
 public void showDashboard() {
 System.out.println("\nFACULTY DASHBOARD");
 System.out.println(" Name : " + getName() + "\n Dept : " + department +
 "\n Role : " + designation + "\n Limit : Unlimited");
 System.out.println();
 }
}
class Librarian extends LibraryUser {
 private String staffId;
 public Librarian(String id, String name, String email, String staffId) {
     super(id, name, email); this.staffId = staffId;
 }
 @Override public String getRole() { return "Librarian"; }
 public void addBook(Book book, Library library) {
 library.addBook(book);
 System.out.println(" ✓ Added: " + book);
 }
 public void removeBook(String isbn, Library library) {
 if (library.removeBook(isbn))
 System.out.println(" ✓ Removed book ISBN: " + isbn);
 else
 System.out.println(" ✗ ISBN not found: " + isbn);
 }
 public void viewAllBorrowRecords(Library library) {
 System.out.println("\n Full Library Borrow Log (Librarian: " + getName() + "):");
 library.printAllRecords();
 }
 @Override
 public void showDashboard() {
 System.out.println("\nLIBRARIAN DASHBOARD");
 System.out.println(" Name : " + getName() + "\n Staff ID : " + staffId +
 "\n Access : Full Admin Privileges");
 System.out.println();
 }
}
