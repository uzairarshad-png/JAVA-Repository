/**
 *
 * @author Uzair Arshad
 */
import java.util.*;

// ===================== Driver Class =====================
public class Inheritance_1 {
    public static void main(String[] args) {

        // ---------- Create 3 Course Objects ----------
        Course c1 = new Course("GSC 122", "Probability and Statistics ", "3");
        Course c2 = new Course("GSC 110", "Applied Calculus and Analytical Geomatery","3");
        Course c3 = new Course("CSC 210", "Object Oriented Programming", "3");

        // ---------- Create 2 Staff Objects ----------
        Staff s1 = new Staff("Capt(Retd) Majid TI(M)",  "DDA",   "NA",
                             "DDA@bahria.edu.pk",  "0300-1111111",     100000, 101, "8AM - 4PM");

        Staff s2 = new Staff("Daniyal",   "Director SSC",    "Admin Dept",
                             "DSSC@bahria.edu.pk", "0300-2222222",     60000, 102, "9AM - 5PM");

        // ---------- Create 2 Faculty Objects ----------
        Faculty f1 = new Faculty("Dr. Abdullah",   "Sr. Asst. Professor",         "CS Dept",
                                 "abdullah@bahria.edu.pk", "0300-3333333",     120000, 201, "8AM - 2PM");

        Faculty f2 = new Faculty("M Marouf",  "Sr Lecturer",  "IT Dept",
                                 "mmarouf@bahria.edu","0300-4444444",     100000, 202, "10AM - 4PM");

        // ---------- Assign Courses to Faculty ----------
        f1.AssignCourse(c1);
        f1.AssignCourse(c2);

        f2.AssignCourse(c2);
        f2.AssignCourse(c3);

        // ==================== Display All Info ====================

        System.out.println();
        System.out.println("           FACULTY INFORMATION          ");
        System.out.println();

        System.out.println("\n>>> Faculty Member 1:");
        System.out.println(f1.toString());
        f1.PrintCourseList();

        System.out.println("\n>>> Faculty Member 2:");
        System.out.println(f2.toString());
        f2.PrintCourseList();

        System.out.println();
        System.out.println("            STAFF INFORMATION           ");
        System.out.println();

        System.out.println("\n>>> Staff Member 1:");
        System.out.println(s1.toString());

        System.out.println("\n>>> Staff Member 2:");
        System.out.println(s2.toString());

        System.out.println("\n========================================");
    }
}
// ===================== Employee Class (Parent) =====================
class Employee {
    private String EmployeeName;
    private String EmployeeDesignation;
    private String EmployeeDepartment;
    private String EmployeeEmail;
    private String EmployeeContact;
    private int EmployeeSalary;
    private int EmployeeNumber;

    // Constructor
    public Employee(String EmployeeName, String EmployeeDesignation, String EmployeeDepartment,
                    String EmployeeEmail, String EmployeeContact, int EmployeeSalary, int EmployeeNumber) {
        this.EmployeeName        = EmployeeName;
        this.EmployeeDesignation = EmployeeDesignation;
        this.EmployeeDepartment  = EmployeeDepartment;
        this.EmployeeEmail       = EmployeeEmail;
        this.EmployeeContact     = EmployeeContact;
        this.EmployeeSalary      = EmployeeSalary;
        this.EmployeeNumber      = EmployeeNumber;
    }

    @Override
    public String toString() {
        return "Name: "         + EmployeeName        + "\n" +
               "Designation: "  + EmployeeDesignation + "\n" +
               "Department: "   + EmployeeDepartment  + "\n" +
               "Email: "        + EmployeeEmail       + "\n" +
               "Contact: "      + EmployeeContact     + "\n" +
               "Salary: "       + EmployeeSalary      + "\n" +
               "Emp Number: "   + EmployeeNumber;
    }
}

// ===================== Staff Class (Child of Employee) =====================
class Staff extends Employee {
    private String WorkingHours;

    // Constructor
    public Staff(String EmployeeName, String EmployeeDesignation, String EmployeeDepartment,
                 String EmployeeEmail, String EmployeeContact, int EmployeeSalary,
                 int EmployeeNumber, String WorkingHours) {
        super(EmployeeName, EmployeeDesignation, EmployeeDepartment,
              EmployeeEmail, EmployeeContact, EmployeeSalary, EmployeeNumber);
        this.WorkingHours = WorkingHours;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Working Hours: " + WorkingHours;
    }
}

// ===================== Course Class =====================
class Course {
    private String CourseCode;
    private String CourseName;
    private String CourseCreditHours;

    // Constructor
    public Course(String CourseCode, String CourseName, String CourseCreditHours) {
        this.CourseCode        = CourseCode;
        this.CourseName        = CourseName;
        this.CourseCreditHours = CourseCreditHours;
    }

    public String GetCourseCode() {
        return CourseCode;
    }

    public String GetCourseName() {
        return CourseName;
    }

    public String GetCourseCreditHours() {
        return CourseCreditHours;
    }
}

// ===================== Faculty Class (Child of Employee) =====================
class Faculty extends Employee {
    private String WorkingHours;
    private ArrayList<Course> CourseList;

    // Constructor
    public Faculty(String EmployeeName, String EmployeeDesignation, String EmployeeDepartment,
                   String EmployeeEmail, String EmployeeContact, int EmployeeSalary,
                   int EmployeeNumber, String WorkingHours) {
        super(EmployeeName, EmployeeDesignation, EmployeeDepartment,
              EmployeeEmail, EmployeeContact, EmployeeSalary, EmployeeNumber);
        this.WorkingHours = WorkingHours;
        this.CourseList   = new ArrayList<Course>();
    }

    public void AssignCourse(Course course) {
        CourseList.add(course);
    }

    public void PrintCourseList() {
        System.out.println("  Assigned Courses:");
        if (CourseList.isEmpty()) {
            System.out.println("  No courses assigned.");
        } else {
            for (Course c : CourseList) {
                System.out.println("  ---------------------------");
                System.out.println("  Code: "         + c.GetCourseCode());
                System.out.println("  Name: "         + c.GetCourseName());
                System.out.println("  Credit Hours: " + c.GetCourseCreditHours());
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Working Hours: " + WorkingHours;
    }
}
