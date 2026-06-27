/**
 *
 * @author UZAIR ARSHAD
 */

import java.util.*;
public class Course {
    private String CourseCode;
    private String CourseName;
    private String CourseCreditHours;
    
    // Constructor
    public Course(String CourseCode, String CourseName, String CourseCreditHours) {
        this.CourseCode = CourseCode;
        this.CourseName = CourseName;
        this.CourseCreditHours = CourseCreditHours;
    }
    
    // Getter methods as per UML diagram
    public String getCourseCode() {
        return CourseCode;
    }
    
    public String getCourseName() {
        return CourseName;
    }
    
    public String getCourseCrediteHours() {
        return CourseCreditHours;
    }
    
    @Override
    public String toString() {
        return "Course{Code='" + CourseCode + "', Name='" + CourseName + 
               "', CreditHours='" + CourseCreditHours + "'}";
    }
}

class Faculty {
    private String FacultyName;
    private String FacultyDesignation;
    private String FacultyEmail;
    private String FacultyContact;
    private int FacultySalary;
    private int FacultyId;
    private ArrayList<Course> CourseList;
    
    // constructor
    public Faculty(String FacultyName, String FacultyDesignation, String FacultyEmail, 
                   String FacultyContact, int FacultySalary, int FacultyId) {
        this.FacultyName = FacultyName;
        this.FacultyDesignation = FacultyDesignation;
        this.FacultyEmail = FacultyEmail;
        this.FacultyContact = FacultyContact;
        this.FacultySalary = FacultySalary;
        this.FacultyId = FacultyId;
        this.CourseList = new ArrayList<>();
    }
    
    public void AssignCourse(Course course) {
        if (course != null && !CourseList.contains(course)) {
            CourseList.add(course);
            System.out.println("Course " + course.getCourseCode() + 
                             " assigned to " + FacultyName);
        } else if (course == null) {
            System.out.println("Cannot assign null course");
        } else {
            System.out.println("Course already assigned to this faculty");
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Faculty{Name='").append(FacultyName)
          .append("', Designation='").append(FacultyDesignation)
          .append("', Email='").append(FacultyEmail)
          .append("', Contact='").append(FacultyContact)
          .append("', Salary=").append(FacultySalary)
          .append(", ID=").append(FacultyId)
          .append(", Courses=[");
        
        for (int i = 0; i < CourseList.size(); i++) {
            sb.append(CourseList.get(i).getCourseCode());
            if (i < CourseList.size() - 1) sb.append(", ");
        }
        sb.append("]}");
        
        return sb.toString();
    }
    
    // Getter methods
    public String getFacultyName() {
        return FacultyName;
    }
    
    public int getFacultyId() {
        return FacultyId;
    }
    
    public ArrayList<Course> getCourseList() {
        return CourseList;
    }
}

class Department {
    private String DepartmentName;
    private ArrayList<Faculty> DepartmentFaculty;
    private ArrayList<Course> OfferedCourses;
    
    // Constructor as per UML
    public Department(String DepartmentName) {
        this.DepartmentName = DepartmentName;
        this.DepartmentFaculty = new ArrayList<>();
        this.OfferedCourses = new ArrayList<>();
    }
    
    // Method to add faculty to department
    public void FacultyList(Faculty faculty) {
        if (faculty != null && !DepartmentFaculty.contains(faculty)) {
            DepartmentFaculty.add(faculty);
            System.out.println("Faculty " + faculty.getFacultyName() + 
                             " added to " + DepartmentName + " department");
        } else if (faculty == null) {
            System.out.println("Cannot add null faculty");
        } else {
            System.out.println("Faculty already exists in department");
        }
    }
    
    // Method to add course to department's offered courses
    public void CourseList(Course course) {
        if (course != null && !OfferedCourses.contains(course)) {
            OfferedCourses.add(course);
            System.out.println("Course " + course.getCourseCode() + 
                             " added to " + DepartmentName + " department");
        } else if (course == null) {
            System.out.println("Cannot add null course");
        } else {
            System.out.println("Course already offered by department");
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Department: ").append(DepartmentName).append("\n");
        sb.append("Faculty Members (").append(DepartmentFaculty.size()).append("):\n");
        for (Faculty f : DepartmentFaculty) {
            sb.append("  - ").append(f.getFacultyName()).append("\n");
        }
        sb.append("Offered Courses (").append(OfferedCourses.size()).append("):\n");
        for (Course c : OfferedCourses) {
            sb.append("  - ").append(c.getCourseCode())
              .append(": ").append(c.getCourseName()).append("\n");
        }
        return sb.toString();
    }
    
    public String getDepartmentName() {
        return DepartmentName;
    }
    
    public ArrayList<Faculty> getDepartmentFaculty() {
        return DepartmentFaculty;
    }
    
    public ArrayList<Course> getOfferedCourses() {
        return OfferedCourses;
    }
    
    // Main method to demonstrate the system
    public static void main(String[] args) {
        System.out.println("=== University Management System ===\n");
        
        // Create courses
        Course course1 = new Course("CSC210", "Object Oriented Programming", "3");
        Course course2 = new Course("CS201", "Data Structures", "3");
        Course course3 = new Course("CS301", "Functional English", "3");
        Course course4 = new Course("MATH101", "Applied Calculus", "3");
        
        System.out.println("Courses created:");
        System.out.println(course1);
        System.out.println(course2);
        System.out.println(course3);
        System.out.println(course4);
        System.out.println();
        
        // Create faculty members
        Faculty faculty1 = new Faculty("Dr. Abdullah Ayub", "Sr. Assistant Professor", 
                                      "abdullah@bukc.edu.pk", "03XX-XXXXXXX", 
                                      200000, 1001);
        Faculty faculty2 = new Faculty("Miss Madiha Aslam", "Visiting Lecturer", 
                                      "madiha@bukc.edu.pk", "03XX-XXXXXXX", 
                                      100000, 1002);
        Faculty faculty3 = new Faculty("Dr. Oyoon Razzaq", "Sr. Associate Professor", 
                                      "oyoon@bukc.edu.pk", "03XX-XXXXXXX", 
                                      175000, 1003);
        
        System.out.println("Faculty members created\n");
        
        // Assign courses to faculty
        faculty1.AssignCourse(course1);
        faculty1.AssignCourse(course2);
        faculty2.AssignCourse(course3);
        faculty3.AssignCourse(course4);
        System.out.println();
        
        // Create departments
        Department csDepartment = new Department("Computer Science");
        Department mathDepartment = new Department("Mathematics");
        
        System.out.println("Departments created\n");
        
        // Add faculty to departments
        csDepartment.FacultyList(faculty1);
        csDepartment.FacultyList(faculty2);
        mathDepartment.FacultyList(faculty3);
        System.out.println();
        
        // Add courses to departments
        csDepartment.CourseList(course1);
        csDepartment.CourseList(course2);
        csDepartment.CourseList(course3);
        mathDepartment.CourseList(course4);
        System.out.println();
        
        // Display department information
        System.out.println("=== Department Details ===\n");
        System.out.println(csDepartment);
        System.out.println(mathDepartment);
        
        // Display faculty information
        System.out.println("=== Faculty Details ===\n");
        System.out.println(faculty1);
        System.out.println(faculty2);
        System.out.println(faculty3);
    }
}




