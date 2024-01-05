import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolledStudents;
    String schedule;

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code=code;
        this.title=title;
        this.description=description;
        this.capacity=capacity;
        this.enrolledStudents=0;
        this.schedule=schedule;
    }

    public String getCode() {
        return code;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getEnrolledStudents() {
        return enrolledStudents;
    }
    public String getSchedule() {
        return schedule;
    }
    public void enroll() {
        enrolledStudents++;
    }
    public void remove() {
        enrolledStudents--;
    }
}
class Student {
    int id;
    String name;
    List<Course> courses;

    Student(int id, String name) {
        this.id=id;
        this.name=name;
        this.courses=new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void register(Course course) {
        courses.add(course);
        course.enroll();
    }
    public void drop(Course course) {
        courses.remove(course);
        course.remove();
    }
}
class Task4 {
    List<Course> courses;
    List<Student> students;

    Task4() {
        this.courses=new ArrayList<>();
        this.students=new ArrayList<>();
    }
    public void addCourse(Course course) {
        courses.add(course);
    }
    public void registerStudent(Student student) {
        students.add(student);
    }
    public void displayCourses() {
        System.out.println("Course Listing:");
        for (Course course : courses) {
            System.out.println("Code: "+course.getCode());
            System.out.println("Title: "+course.getTitle());
            System.out.println("Description: "+course.getDescription());
            System.out.println("Capacity: "+course.getCapacity());
            System.out.println("Enrolled Students: "+course.getEnrolledStudents());
            System.out.println("Schedule: "+course.getSchedule());
            System.out.println("Available Slots: "+(course.getCapacity() - course.getEnrolledStudents()));
            System.out.println("------------------------------");
        }
    }
    public void displayStudentReg(int id) {
        Student student=findStudentById(id);
        if (student!=null) {
            System.out.println("Courses for "+student.getName()+" (ID: "+student.getId()+"):");
            List<Course> registeredCourses=student.getCourses();
            for (Course course:registeredCourses) {
                System.out.println("- "+course.getTitle());
            }
            System.out.println("------------------------------");
        } else {
            System.out.println("Student with ID "+id+" not found.");
        }
    }
    public Student findStudentById(int id) {
        for (Student student:students) {
            if (student.getId()==id) {
                return student;
            }
        }
        return null;
    }
    public void studentReg(int id,String code) {
        Student student=findStudentById(id);
        Course course=findCourseByCode(code);

        if (student!=null&&course!=null&&course.getEnrolledStudents()<course.getCapacity()) {
            student.register(course);
            System.out.println("Registration successful for student "+student.getName()+" in course "+course.getTitle());
        } else {
            System.out.println("Registration failed. Please check student ID and course availability.");
        }
    }
    public void courseRemoval(int id,String code) {
        Student student=findStudentById(id);
        Course course=findCourseByCode(code);

        if (student!=null&&course!=null&&student.getCourses().contains(course)) {
            student.drop(course);
            System.out.println("Course dropped successfully for student " + student.getName() + " in course " + course.getTitle());
        } else {
            System.out.println("Drop failed. Please check student ID and course registration.");
        }
    }
    public Course findCourseByCode(String code) {
        for (Course course:courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Task4 obj=new Task4();
        Course course1=new Course("JVA", "Java Programming", "Introduction to Java", 50, "Mon/Wed/Fri 10:00 AM");
        Course course2=new Course("PYN", "Python Programming", "Introduction to Python", 40, "Tue/Thu 2:00 PM");
        obj.addCourse(course1);
        obj.addCourse(course2);
        Student student1 = new Student(1001, "Mohanadithya");
        obj.registerStudent(student1);
        obj.displayCourses();
        obj.studentReg(1001, "JVA");
        obj.displayStudentReg(1001);
    }
}
