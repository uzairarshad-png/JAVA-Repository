/**
 *
 * @author Uzair Arshad
 */
import java.util.*;

public class Association_aggregation_1 {
    
    public static void main(String[] args) {
        System.out.println("\t===UNIVERSITY MANAGEMENT SYSTEM - DRIVER CLASS===");
        System.out.println();
        
        //Creating 2 Course Objects 
        System.out.println("\t===CREATING 2 COURSE OBJECTS===");
        
        Course course1 = new Course("CS101", "Object Oriented Programming", "3");
        Course course2 = new Course("CS202", "Data Structures and Algorithms", "4");
        
        System.out.println(" Course 1: " + course1);
        System.out.println(" Course 2: " + course2);
        System.out.println();
        
        // Creating 3 Faculty Objects 
        System.out.println("\t===CREATING 3 FACULTY OBJECTS===");
        System.out.println();
        
        Faculty faculty1 = new Faculty(
            "Dr. Ahmed Khan",
            "Professor",
            "ahmed.khan@university.edu.pk",
            "+92-300-1234567",
            120000,
            2001
        );
        
        Faculty faculty2 = new Faculty(
            "Dr. Sarah Ali",
            "Associate Professor",
            "sarah.ali@university.edu.pk",
            "+92-300-2345678",
            95000,
            2002
        );
        
        Faculty faculty3 = new Faculty(
            "Dr. Hassan Raza",
            "Assistant Professor",
            "hassan.raza@university.edu.pk",
            "+92-300-3456789",
            75000,
            2003
        );
        
        System.out.println("✓ Faculty 1 Created: " + faculty1.getFacultyName());
        System.out.println("✓ Faculty 2 Created: " + faculty2.getFacultyName());
        System.out.println("✓ Faculty 3 Created: " + faculty3.getFacultyName());
        System.out.println();
        
        //Assigning Courses to Faculty
        System.out.println("\t===ASSIGNING COURSES TO FACULTY===");
        System.out.println();
        
        faculty1.AssignCourse(course1);
        faculty2.AssignCourse(course2);
        faculty3.AssignCourse(course1);
        faculty3.AssignCourse(course2);
        System.out.println();
        
        // Creating 3 Department Objects 
        System.out.println("\t===CREATING 3 DEPARTMENT OBJECTS===");
        System.out.println();
        
        Department dept1 = new Department("Computer Science");
        Department dept2 = new Department("Software Engineering");
        Department dept3 = new Department("Information Technology");
        
        System.out.println(" Department 1 Created: " + dept1.getDepartmentName());
        System.out.println(" Department 2 Created: " + dept2.getDepartmentName());
        System.out.println(" Department 3 Created: " + dept3.getDepartmentName());
        System.out.println();
        
        //Adding Faculty to Departments 
        System.out.println("\t===ADDING FACULTY TO DEPARTMENTS===");
        System.out.println();
        
        dept1.FacultyList(faculty1);
        dept1.FacultyList(faculty2);
        dept2.FacultyList(faculty3);
        dept3.FacultyList(faculty1);
        System.out.println();
        
        //Adding Courses to Departments 
        System.out.println("\t===ADDING COURSES TO DEPARTMENTS");
        System.out.println();
        
        dept1.CourseList(course1);
        dept1.CourseList(course2);
        dept2.CourseList(course1);
        dept3.CourseList(course2);
        System.out.println();
        
        // ========== DISPLAYING ALL INFORMATION ==========
        System.out.println("\n\n");;
        System.out.println("\t===COMPLETE SYSTEM INFORMATION DISPLAY===");
        System.out.println();
        
        // Display Course Information
        displayCourseInformation(course1, course2);
        
        // Display Faculty Information
        displayFacultyInformation(faculty1, faculty2, faculty3);
        
        // Display Department Information
        displayDepartmentInformation(dept1, dept2, dept3);
        
        // Display Summary Statistics
        displaySummaryStatistics(course1, course2, faculty1, faculty2, faculty3, 
                                dept1, dept2, dept3);
    }
    
    // Method to display Course Information
    private static void displayCourseInformation(Course course1, Course course2) {
        System.out.println("\t===COURSE INFORMATION===");
        System.out.println();
        
        System.out.println(" COURSE 1 ");
        System.out.println("   Course Code        : " + course1.getCourseCode());
        System.out.println("   Course Name        : " + course1.getCourseName());
        System.out.println("   Credit Hours       : " + course1.getCourseCrediteHours());
        System.out.println();
        
        System.out.println(" COURSE 2 ");
        System.out.println("   Course Code        : " + course2.getCourseCode());
        System.out.println("   Course Name        : " + course2.getCourseName());
        System.out.println("   Credit Hours       : " + course2.getCourseCrediteHours());
        System.out.println();
    }
    
    // Method to display Faculty Information
    private static void displayFacultyInformation(Faculty faculty1, Faculty faculty2, 
                                                  Faculty faculty3) {
        System.out.println("\t===FACULTY INFORMATION===");
        System.out.println();
        
        displaySingleFaculty(faculty1, 1);
        displaySingleFaculty(faculty2, 2);
        displaySingleFaculty(faculty3, 3);
        
        System.out.println();
    }
    
    // Helper method to display single faculty details
    private static void displaySingleFaculty(Faculty faculty, int number) {
        System.out.println(" FACULTY " + number );
        System.out.println("   Name               : " + faculty.getFacultyName());
        System.out.println("   Faculty ID         : " + faculty.getFacultyId());
        System.out.println("   Designation        : " + getDesignation(faculty));
        System.out.println("   Email              : " + getEmail(faculty));
        System.out.println("   Contact            : " + getContact(faculty));
        System.out.println("   Salary             : PKR " + getSalary(faculty));
        System.out.println("   Assigned Courses   : " + faculty.getCourseList().size() + " course(s)");
        
        if (faculty.getCourseList().size() > 0) {
            for (int i = 0; i < faculty.getCourseList().size(); i++) {
                Course c = faculty.getCourseList().get(i);
                System.out.println("      " + (i + 1) + ". " + c.getCourseCode() + 
                                 " - " + c.getCourseName());
            }
        }
        System.out.println();
    }
    
    // Method to display Department Information
    private static void displayDepartmentInformation(Department dept1, Department dept2, 
                                                    Department dept3) {
        System.out.println("\t===DEPARTMENT INFORMATION===");
        System.out.println();
        
        displaySingleDepartment(dept1, 1);
        displaySingleDepartment(dept2, 2);
        displaySingleDepartment(dept3, 3);
        
        System.out.println();
    }
    
    // Helper method to display single department details
    private static void displaySingleDepartment(Department dept, int number) {
        System.out.println(" DEPARTMENT " + number );
        System.out.println("  Department Name      : " + dept.getDepartmentName());
        System.out.println();
        
        System.out.println("  Faculty Members (" + dept.getDepartmentFaculty().size() + "):");
        if (dept.getDepartmentFaculty().size() > 0) {
            for (int i = 0; i < dept.getDepartmentFaculty().size(); i++) {
                Faculty f = dept.getDepartmentFaculty().get(i);
                System.out.println("  │   " + (i + 1) + ". " + f.getFacultyName() + 
                                 " (ID: " + f.getFacultyId() + ")");
            }
        } else {
            System.out.println("   No faculty assigned");
        }
        System.out.println();
        
        System.out.println("  Offered Courses (" + dept.getOfferedCourses().size() + "):");
        if (dept.getOfferedCourses().size() > 0) {
            for (int i = 0; i < dept.getOfferedCourses().size(); i++) {
                Course c = dept.getOfferedCourses().get(i);
                System.out.println("      " + (i + 1) + ". " + c.getCourseCode() + 
                                 " - " + c.getCourseName() + 
                                 " (" + c.getCourseCrediteHours() + " CH)");
            }
        } else {
            System.out.println("      No courses offered");
        }
        System.out.println();
    }
    
    // Method to display summary statistics
    private static void displaySummaryStatistics(Course course1, Course course2,
                                                Faculty faculty1, Faculty faculty2, 
                                                Faculty faculty3,
                                                Department dept1, Department dept2, 
                                                Department dept3) {
        System.out.println("\t===SUMMARY STATISTICS===");
        System.out.println();
        
        System.out.println("  Total Courses Created       : 2");
        System.out.println("  Total Faculty Members       : 3");
        System.out.println("  Total Departments           : 3");
        System.out.println();
        
        int totalCourseAssignments = faculty1.getCourseList().size() + 
                                    faculty2.getCourseList().size() + 
                                    faculty3.getCourseList().size();
        System.out.println("  Total Course Assignments    : " + totalCourseAssignments);
        
        int totalFacultyInDepts = dept1.getDepartmentFaculty().size() + 
                                 dept2.getDepartmentFaculty().size() + 
                                 dept3.getDepartmentFaculty().size();
        System.out.println("  Total Faculty-Dept Links    : " + totalFacultyInDepts);
        
        int totalCourseOfferings = dept1.getOfferedCourses().size() + 
                                  dept2.getOfferedCourses().size() + 
                                  dept3.getOfferedCourses().size();
        System.out.println("  Total Course Offerings      : " + totalCourseOfferings);
        System.out.println();
        
        System.out.println("\t===SYSTEM DISPLAY COMPLETED SUCCESSFULLY===");
    }
    private static String getDesignation(Faculty faculty) {
        String str = faculty.toString();
        int start = str.indexOf("Designation='") + 13;
        int end = str.indexOf("'", start);
        return str.substring(start, end);
    }
    
    private static String getEmail(Faculty faculty) {
        String str = faculty.toString();
        int start = str.indexOf("Email='") + 7;
        int end = str.indexOf("'", start);
        return str.substring(start, end);
    }
    
    private static String getContact(Faculty faculty) {
        String str = faculty.toString();
        int start = str.indexOf("Contact='") + 9;
        int end = str.indexOf("'", start);
        return str.substring(start, end);
    }
    
    private static int getSalary(Faculty faculty) {
        String str = faculty.toString();
        int start = str.indexOf("Salary=") + 7;
        int end = str.indexOf(",", start);
        return Integer.parseInt(str.substring(start, end));
    }
}