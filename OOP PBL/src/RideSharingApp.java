/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DANISH LAPTOP
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
enum TripStatus {
 REQUESTED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED
}
enum PayType {
 CREDIT_CARD, DEBIT_CARD, DIGITAL_WALLET, CASH
}
class Location {
 private double latitude; // hidden
 private double longitude; // hidden
 private String label;
 public Location(double latitude, double longitude, String label) {
 this.latitude = latitude;
 this.longitude = longitude;
 this.label = label;
 }
 // Only the label is exposed publicly; raw coordinates stay private
 public String getLabel() { return label; }
 // Distance in km using Haversine formula (internal use only)
 double distanceTo(Location other) {
 final double R = 6371;
 double dLat = Math.toRadians(other.latitude - this.latitude);
 double dLon = Math.toRadians(other.longitude - this.longitude);
 double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
 + Math.cos(Math.toRadians(this.latitude))
 * Math.cos(Math.toRadians(other.latitude))
 * Math.sin(dLon / 2) * Math.sin(dLon / 2);
 return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
 }
}
interface Payable {
 boolean processPayment(double amount);
 String getReceipt();
}
abstract class RideOption {
 protected double baseFare; // per km rate
 protected String optionType;
public RideOption(double baseFare, String optionType) {
 this.baseFare = baseFare;
 this.optionType = optionType;
 }
 // Abstract: each subclass calculates fare differently
 public abstract double calculateFare(double distanceKm);
 public String getOptionType() { return optionType; }
}
class EconomyRide extends RideOption {
 private static final double MULTIPLIER = 1.0;
 public EconomyRide() {
 super(2.5, "Economy");
 }
 @Override
 public double calculateFare(double distanceKm) {
 return baseFare * distanceKm * MULTIPLIER;
 }
}
class PremiumRide extends RideOption {
 private static final double MULTIPLIER = 2.5;
 public PremiumRide() {
 super(2.5, "Premium");
 }
 @Override
 public double calculateFare(double distanceKm) {
 return baseFare * distanceKm * MULTIPLIER;
 }
}
class Vehicle {
 private String vehicleId;
 private String plateNo;
 private String model;
 private int year;
 public Vehicle(String plateNo, String model, int year) {
 this.vehicleId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
 this.plateNo = plateNo;
 this.model = model;
 this.year = year;
 }
 public String getDetails() {
 return model + " (" + year + ")";
 }
 public boolean isEligible() {
 return (LocalDateTime.now().getYear() - year) <= 10;
 }
 // plateNo not exposed publicly — only internal use
 String getPlateNo() { return plateNo; }
}
class Rating {
 private String ratingId;
 private int score; // 1–5
 private String comment;
 private LocalDateTime timestamp;
 private String driverName;
 public Rating(int score, String comment, String driverName) {
 if (score < 1 || score > 5)
 throw new IllegalArgumentException("Score must be between 1 and 5.");
 this.ratingId = UUID.randomUUID().toString().substring(0, 8);
 this.score = score;
 this.comment = comment;
 this.timestamp = LocalDateTime.now();
 this.driverName = driverName;
 }
 public void submit() {
 System.out.println(" ★ Rating submitted: " + score + "/5 for " + driverName
 + " — \"" + comment + "\"");
 }
 public int getScore() { return score; }
 public String getComment() { return comment; }
 @Override
 public String toString() {
 return score + "/5 (" + comment + ") on "
 + timestamp.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
 }
}
class Payment implements Payable {
 private String paymentId;
 private String cardToken; // tokenised — never exposed raw
 private PayType method;
 private double amount;
 private boolean paid;
 private String receiptNo;
 public Payment(String cardToken, PayType method) {
 this.paymentId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
 this.cardToken = cardToken;
 this.method = method;
 this.paid = false;
 }
 @Override
 public boolean processPayment(double amount) {
 this.amount = amount;
 this.paid = true;
 this.receiptNo = "RCP-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
 System.out.printf(" Payment of PKR %.2f processed via %s%n", amount, method);
 return true;
 }
 @Override
 public String getReceipt() {
 if (!paid) return "No payment made yet.";
 return String.format("Receipt #%s | PKR %.2f | Method: %s", receiptNo, amount, method);
 }
 public boolean isPaid() { return paid; }
}
class Driver {
 private String driverId;
 private String name;
 private String licenseNo; // hidden
 private Location currentLocation; // hidden
 private boolean isAvailable;
 private double totalRatingSum;
 private int ratingCount;
 private Vehicle vehicle; // Composition
 public Driver(String name, String licenseNo, Vehicle vehicle, Location startLocation) {
 this.driverId = "DRV-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
 this.name = name;
 this.licenseNo = licenseNo;
 this.vehicle = vehicle;
 this.currentLocation = startLocation;
 this.isAvailable = true;
 this.totalRatingSum = 0;
 this.ratingCount = 0;
 }
 public void acceptTrip(Trip trip) {
 if (!isAvailable) {
 System.out.println(" ✗ " + name + " is not available.");
 return;
 }
 isAvailable = false;
 trip.setStatus(TripStatus.ACCEPTED);
 System.out.println(" ✔ Driver " + name + " accepted the trip.");
 }
 public void updateLocation(Location newLocation) {
 this.currentLocation = newLocation; // internal update — raw GPS stays hidden
 }
 public void receiveRating(Rating rating) {
 totalRatingSum += rating.getScore();
 ratingCount++;
 }
 public double getAvgRating() {
 return ratingCount == 0 ? 0.0 : totalRatingSum / ratingCount;
 }
 public boolean isAvailable() { return isAvailable; }
 public void setAvailable(boolean v) { isAvailable = v; }
 public String getName() { return name; }
 public String getDriverId() { return driverId; }
 public Vehicle getVehicle() { return vehicle; }
 Location getCurrentLocation() { return currentLocation; } // package-visible
 @Override
 public String toString() {
 return String.format("Driver[%s | %s | Vehicle: %s | Rating: %.1f]",
 driverId, name, vehicle.getDetails(), getAvgRating());
 }
}
class Rider {
 private String riderId;
 private String name;
 private String email; // hidden
 private List<Location> savedLocations; // hidden
 private Payment paymentMethod; // hidden (Composition)
 private List<Trip> tripHistory;
 public Rider(String name, String email, Payment paymentMethod) {
 this.riderId = "RDR-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
 this.name = name;
 this.email = email;
 this.paymentMethod = paymentMethod;
 this.savedLocations = new ArrayList<>();
 this.tripHistory = new ArrayList<>();
 }
 public Trip requestRide(RideOption option, Location pickup, Location dropoff, Driver driver) {
 System.out.println("\n → " + name + " requesting a " + option.getOptionType() + " ride...");
 Trip trip = new Trip(this, driver, option, pickup, dropoff);
 tripHistory.add(trip);
 return trip;
 }
 public void rateDriver(Driver driver, int score, String comment) {
 Rating rating = new Rating(score, comment, driver.getName());
 rating.submit();
 driver.receiveRating(rating);
 }
 public void saveLocation(Location loc) { savedLocations.add(loc); }
 public List<Trip> getTripHistory() { return new ArrayList<>(tripHistory); } // defensive copy
 public String getName() { return name; }
 public String getRiderId() { return riderId; }
 Payment getPaymentMethod() { return paymentMethod; } // package-visible for Trip
 @Override
 public String toString() {
 return String.format("Rider[%s | %s | Trips: %d]",
 riderId, name, tripHistory.size());
 }
}
class Trip {
 private String tripId;
 private Rider rider;
 private Driver driver;
 private RideOption rideOption;
 private Location pickup; // hidden
 private Location dropoff; // hidden
 private TripStatus status;
 private double distanceKm;
 private double fare;
 private List<Rating> ratings;
 public Trip(Rider rider, Driver driver, RideOption rideOption,
 Location pickup, Location dropoff) {
 this.tripId = "TRIP-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
 this.rider = rider;
 this.driver = driver;
 this.rideOption = rideOption;
 this.pickup = pickup;
 this.dropoff = dropoff;
 this.status = TripStatus.REQUESTED;
 this.distanceKm = pickup.distanceTo(dropoff);
 this.fare = rideOption.calculateFare(distanceKm);
 this.ratings = new ArrayList<>();
 }
 public void startTrip() {
 if (status != TripStatus.ACCEPTED) {
 System.out.println(" ✗ Trip cannot start — not yet accepted.");
 return;
 }
 status = TripStatus.IN_PROGRESS;
 System.out.printf(" Trip %s started | %s → %s | %.1f km%n",
 tripId,
 pickup.getLabel(),
 dropoff.getLabel(),
 distanceKm);
 }
 public void endTrip() {
 if (status != TripStatus.IN_PROGRESS) {
 System.out.println(" ✗ Trip is not in progress.");
 return;
 }
 status = TripStatus.COMPLETED;
 driver.setAvailable(true);
 driver.updateLocation(dropoff);
 System.out.printf(" Trip %s completed. Fare: PKR %.2f%n", tripId, fare);
 // Auto-process payment
 Payment payment = rider.getPaymentMethod();
 if (payment != null) {
 payment.processPayment(fare);
 System.out.println(" " + payment.getReceipt());
 }
 }
 public void cancelTrip() {
 if (status == TripStatus.COMPLETED) {
 System.out.println(" ✗ Cannot cancel a completed trip.");
 return;
 }
 status = TripStatus.CANCELLED;
 driver.setAvailable(true);
 System.out.println(" Trip " + tripId + " cancelled.");
 }
 public double getFare() { return fare; }
 public TripStatus getStatus(){ return status; }
 public String getTripId() { return tripId; }
 public void setStatus(TripStatus s) { this.status = s; }
 @Override
 public String toString() {
 return String.format("Trip[%s | %s → %s | %.1f km | PKR %.2f | %s | %s]",
 tripId,
 pickup.getLabel(), dropoff.getLabel(),
 distanceKm, fare,
 rideOption.getOptionType(),
 status);
 }
}
public class RideSharingApp {
 public static void main(String[] args) {
 printBanner("RIDE-SHARING APP PROTOTYPE — OOP Demo");
 // ── 1. Create Vehicles ──────────────────────────────────
 printSection("1. Registering Vehicles");
 Vehicle v1 = new Vehicle("LEA-2345", "Toyota Corolla", 2020);
 Vehicle v2 = new Vehicle("LHR-9871", "Honda Civic", 2019);
 System.out.println(" Vehicle 1: " + v1.getDetails() + " | Eligible: " + v1.isEligible());
 System.out.println(" Vehicle 2: " + v2.getDetails() + " | Eligible: " + v2.isEligible());
 // ── 2. Create Drivers ───────────────────────────────────
 printSection("2. Registering Drivers");
 Location gulshan = new Location(24.9215, 67.0933, "Gulshan-e-Iqbal");
 Location clifton = new Location(24.8126, 67.0300, "Clifton");
 Location dha = new Location(24.8065, 67.0681, "DHA Phase 5");
 Location saddar = new Location(24.8607, 67.0099, "Saddar");
 Driver d1 = new Driver("Ali Hassan", "LIC-001-KHI", v1, gulshan);
 Driver d2 = new Driver("Zara Sheikh", "LIC-002-KHI", v2, clifton);
 System.out.println(" " + d1);
 System.out.println(" " + d2);
 // ── 3. Create Payments ──────────────────────────────────
 printSection("3. Setting Up Payments");
 Payment p1 = new Payment("tok_****_4242", PayType.CREDIT_CARD);
 Payment p2 = new Payment("tok_****_8888", PayType.DIGITAL_WALLET);
 // ── 4. Create Riders ────────────────────────────────────
 printSection("4. Registering Riders");
 Rider r1 = new Rider("Uzair Khan", "uzair@example.com", p1);
 Rider r2 = new Rider("Fatima Malik", "fatima@example.com", p2);
 System.out.println(" " + r1);
 System.out.println(" " + r2);
 // ── 5. Ride Options (Polymorphism) ──────────────────────
 printSection("5. Available Ride Options (Abstraction + Polymorphism)");
 RideOption economy = new EconomyRide();
 RideOption premium = new PremiumRide();
 double sampleDist = 10.0;
 System.out.printf(" Economy fare for %.0f km: PKR %.2f%n",
 sampleDist, economy.calculateFare(sampleDist));
 System.out.printf(" Premium fare for %.0f km: PKR %.2f%n",
 sampleDist, premium.calculateFare(sampleDist));
 // ── 6. Trip 1 — Economy ─────────────────────────────────
 printSection("6. Trip 1: Uzair books an Economy ride");
 Trip trip1 = r1.requestRide(economy, gulshan, dha, d1);
 d1.acceptTrip(trip1);
 trip1.startTrip();
 trip1.endTrip();
 System.out.println("\n Trip summary: " + trip1);
 // ── 7. Rating ───────────────────────────────────────────
 printSection("7. Uzair rates Driver Ali Hassan");
 r1.rateDriver(d1, 5, "Very smooth ride, punctual!");
 System.out.printf(" Ali Hassan avg rating: %.1f/5%n", d1.getAvgRating());
 // ── 8. Trip 2 — Premium ─────────────────────────────────
 printSection("8. Trip 2: Fatima books a Premium ride");
 Trip trip2 = r2.requestRide(premium, clifton, saddar, d2);
 d2.acceptTrip(trip2);
 trip2.startTrip();
 trip2.endTrip();
 System.out.println("\n Trip summary: " + trip2);
 // ── 9. Rating for Trip 2 ────────────────────────────────
 printSection("9. Fatima rates Driver Zara Sheikh");
 r2.rateDriver(d2, 4, "Good ride, car was clean.");
 System.out.printf(" Zara Sheikh avg rating: %.1f/5%n", d2.getAvgRating());
 // ── 10. Trip History ────────────────────────────────────
 printSection("10. Trip History");
 System.out.println(" Uzair's trips:");
 for (Trip t : r1.getTripHistory()) System.out.println(" • " + t);
 System.out.println(" Fatima's trips:");
 for (Trip t : r2.getTripHistory()) System.out.println(" • " + t);
 // ── 11. Cancellation Demo ───────────────────────────────
 printSection("11. Cancellation Demo");
 Trip trip3 = r1.requestRide(economy, dha, saddar, d1);
 d1.acceptTrip(trip3);
 trip3.cancelTrip();
 System.out.println(" Trip status after cancel: " + trip3.getStatus());
 System.out.println(" Driver available again: " + d1.isAvailable());
 // ── 12. Driver Dashboard ────────────────────────────────
 printSection("12. Driver Dashboard");
 System.out.println(" " + d1);
 System.out.println(" " + d2);
 printBanner("END OF DEMO");
 }
 // ── Utility helpers ─────────────────────────────────────────
 private static void printBanner(String text) {
 String line = "═".repeat(60);
 System.out.println("\n" + line);
 System.out.println(" " + text);
 System.out.println(line);
 }
 private static void printSection(String title) {
 System.out.println("\n── " + title + " " + "─".repeat(Math.max(0, 52 - title.length())));
 }
}
