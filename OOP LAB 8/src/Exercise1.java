/**
 *
 * @author Uzair Arshad
 */
class Employee {

    private String firstName;
    private String lastName;
    private String CNIC;
    public Employee() {
        this.firstName = "";
        this.lastName  = "";
        this.CNIC      = "";
    }
    public Employee(String firstName, String lastName, String CNIC) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.CNIC      = CNIC;
    }

    // --- Getters ---
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName;  }
    public String getCNIC()      { return CNIC;      }

    // --- Setters ---
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName)   { this.lastName  = lastName;  }
    public void setCNIC(String CNIC)           { this.CNIC      = CNIC;      }

    // Overridden in every subclass to return actual weekly pay
    public double earnings() {
        return 0.00;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "  |  CNIC: " + CNIC;
    }
}
class SalariedEmployee extends Employee {

    private double weeklySalary;

    public SalariedEmployee() {
        super();
        this.weeklySalary = 0.0;
    }

    public SalariedEmployee(String firstName, String lastName,
                            String CNIC, double weeklySalary) {
        super(firstName, lastName, CNIC);
        setWeeklySalary(weeklySalary);
    }

    public double getWeeklySalary() { return weeklySalary; }

    // Validation: salary must never be stored as a negative value
    public void setWeeklySalary(double weeklySalary) {
        if (weeklySalary >= 0)
            this.weeklySalary = weeklySalary;
        else
            this.weeklySalary = 0.0;
    }

    @Override
    public double earnings() {
        return weeklySalary;
    }

    @Override
    public String toString() {
        return "\nSalaried Employee     : " + super.toString();
    }
}

class HourlyEmployee extends Employee {

    private double wage;
    private double hours;

    public HourlyEmployee() {
        super();
        this.wage  = 0.0;
        this.hours = 0.0;
    }

     public HourlyEmployee(String firstName, String lastName,
                          String CNIC, double wage, double hours) {
        super(firstName, lastName, CNIC);
        setWage(wage);
        setHours(hours);
    }

    public double getWage()  { return wage;  }
    public double getHours() { return hours; }

    // Validation: wage and hours must be zero or positive
    public void setWage(double wage) {
        if (wage >= 0)
            this.wage = wage;
        else
            this.wage = 0.0;
    }

    public void setHours(double hours) {
        if (hours >= 0)
            this.hours = hours;
        else
            this.hours = 0.0;
    }

    @Override
    public double earnings() {
        if (hours <= 40)
            return wage * hours;                               // standard pay
        else
            return (40 * wage) + ((hours - 40) * wage * 1.5); // overtime pay
    }

    @Override
    public String toString() {
        return "\nHourly Employee       : " + super.toString();
    }
}

class CommissionEmployee extends Employee {

    private double grossSales;
    private double commissionRate;

    public CommissionEmployee() {
        super();
        this.grossSales     = 0.0;
        this.commissionRate = 0.0;
    }

    public CommissionEmployee(String firstName, String lastName,
                              String CNIC, double grossSales,
                              double commissionRate) {
        super(firstName, lastName, CNIC);
        setGrossSales(grossSales);
        setCommissionRate(commissionRate);
    }

    public double getGrossSales()     { return grossSales;     }
    public double getCommissionRate() { return commissionRate; }

    // Validation: sales and commission rate must be zero or positive
    public void setGrossSales(double grossSales) {
        if (grossSales >= 0)
            this.grossSales = grossSales;
        else
            this.grossSales = 0.0;
    }

    public void setCommissionRate(double commissionRate) {
        if (commissionRate >= 0)
            this.commissionRate = commissionRate;
        else
            this.commissionRate = 0.0;
    }

    @Override
    public double earnings() {
        return grossSales * commissionRate;
    }

    @Override
    public String toString() {
        return "\nCommission Employee   : " + super.toString();
    }
}

class BasePlusCommissionEmployee extends CommissionEmployee {

    private double baseSalary;

     public BasePlusCommissionEmployee() {
        super();
        this.baseSalary = 0.0;
    }

    public BasePlusCommissionEmployee(String firstName, String lastName,
                                      String CNIC, double grossSales,
                                      double commissionRate, double baseSalary) {
        super(firstName, lastName, CNIC, grossSales, commissionRate);
        setBaseSalary(baseSalary);
    }

    public double getBaseSalary() { return baseSalary; }

    // Validation: base salary cannot be negative
    public void setBaseSalary(double baseSalary) {
        if (baseSalary >= 0)
            this.baseSalary = baseSalary;
        else
            this.baseSalary = 0.0;
    }

    @Override
    public double earnings() {
        return baseSalary + super.earnings();  // base pay + commission
    }

    @Override
    public String toString() {
        return "\nBase+Commission Emp   : " + super.toString();
    }
}

public class Exercise1 {

    static void runPart_a() {
        System.out.println("        PART (a)  -  Employee Class Definitions       ");

        // -- Test SalariedEmployee --
        SalariedEmployee e1 = new SalariedEmployee(
                "Uzair", "Arshad", "101-23-4567", 950.00);
        System.out.println("Created :" + e1);
        System.out.printf("Weekly Earnings          : $%.2f%n", e1.earnings());

        // Verify negative guard on setter
        e1.setWeeklySalary(-200);
        System.out.println("Set negative salary -200 (should reset to 0.0): $" + e1.getWeeklySalary());

        // -- Test HourlyEmployee: exactly 40 hours, no overtime --
        HourlyEmployee e2 = new HourlyEmployee(
                "M", "Ayan", "202-34-5678", 20.00, 40);
        System.out.println("\nCreated :" + e2);
        System.out.printf("Earnings (40 hrs, no overtime)     : $%.2f%n", e2.earnings());

        // -- Test HourlyEmployee: 50 hours, 10 hours overtime --
        HourlyEmployee e3 = new HourlyEmployee(
                "Altahan", "Rauf", "303-45-6789", 20.00, 50);
        System.out.println("\nCreated :" + e3);
        System.out.printf("Earnings (50 hrs, 10 hrs overtime) : $%.2f%n", e3.earnings());

        // -- Test CommissionEmployee --
        CommissionEmployee e4 = new CommissionEmployee(
                "M", "Jahanzaib", "404-56-7890", 15000, 0.08);
        System.out.println("\nCreated :" + e4);
        System.out.printf("Earnings (8%% of Rs.15000 sales)  : $%.2f%n", e4.earnings());

        // -- Test BasePlusCommissionEmployee --
        BasePlusCommissionEmployee e5 = new BasePlusCommissionEmployee(
                "Anas", "Tauqeer", "505-67-8901", 9000, 0.05, 500);
        System.out.println("\nCreated :" + e5);
        System.out.printf("Earnings (base + 5%% commission)  : $%.2f%n", e5.earnings());

        System.out.println("\n===== Part (a) Complete =====\n");
    }

    static void runPart_b() {
        System.out.println("   PART (b)  -  Upcasting, Polymorphism, Downcasting  ");
        // ── UPCASTING ─────────────────────────────────────────────
        // Subclass objects are stored in Employee (base type) references.
        // Java performs this implicit conversion automatically.
        Employee firstEmployee = new SalariedEmployee(
                "Uzair", "Arshad", "101-23-4567", 950.00);

        Employee secondEmployee = new CommissionEmployee(
                "M", "Jahanzaib", "404-56-7890", 15000, 0.08);

        Employee thirdEmployee = new BasePlusCommissionEmployee(
                "Anas", "Tauqeer", "505-67-8901", 9000, 0.05, 500);

        Employee fourthEmployee = new HourlyEmployee(
                "Altahan", "Rauf", "303-45-6789", 20.00, 50);

        // ── POLYMORPHISM ──────────────────────────────────────────
        // toString() and earnings() are invoked via Employee references.
        // Java resolves each call to the correct subclass version at runtime.
        System.out.println(firstEmployee);
        System.out.printf("Earnings : $%.2f%n", firstEmployee.earnings());

        System.out.println(secondEmployee);
        System.out.printf("Earnings : $%.2f%n", secondEmployee.earnings());

        System.out.println(thirdEmployee);

        // ── DOWNCASTING ───────────────────────────────────────────
        // thirdEmployee is declared as Employee but actually holds a
        // BasePlusCommissionEmployee object. We explicitly downcast it
        // to call getBaseSalary() / setBaseSalary() — methods that only
        // exist in BasePlusCommissionEmployee, not in Employee.
        BasePlusCommissionEmployee currentEmployee =
                (BasePlusCommissionEmployee) thirdEmployee;

        double oldBaseSalary = currentEmployee.getBaseSalary();
        System.out.println("Old base salary            : $" + oldBaseSalary);

        currentEmployee.setBaseSalary(oldBaseSalary * 1.10);   // 10% increment
        System.out.printf("New base salary (+10%%)     : $%.2f%n",
                currentEmployee.getBaseSalary());

        System.out.printf("Total earnings after raise : $%.2f%n", thirdEmployee.earnings());

        System.out.println(fourthEmployee);
        System.out.printf("Earnings : $%.2f%n", fourthEmployee.earnings());

        System.out.println("\n===== Part (b) Complete =====\n");
    }

    // ── main : runs Part (a) then Part (b) sequentially ────────
    public static void main(String[] args) {
        runPart_a();
        runPart_b();
    }
}