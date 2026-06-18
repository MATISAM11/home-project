import java.io.*;
import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student searchStudent(int id) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudent(int id, String name,
                                 String department, double gpa) {
        Student s = searchStudent(id);

        if (s != null) {
            s.setName(name);
            s.setDepartment(department);
            s.setGpa(gpa);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Student s = searchStudent(id);

        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }

    public void displayAllStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Report
    public void generateReport() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double highest = students.get(0).getGpa();
        double lowest = students.get(0).getGpa();
        double total = 0;

        for (Student s : students) {
            double gpa = s.getGpa();

            if (gpa > highest)
                highest = gpa;

            if (gpa < lowest)
                lowest = gpa;

            total += gpa;
        }

        double average = total / students.size();

        System.out.println("Total Students: " + students.size());
        System.out.println("Highest GPA: " + highest);
        System.out.println("Lowest GPA: " + lowest);
        System.out.println("Average GPA: " + average);
    }

    // Serialization
    public void saveObjectFile(String fileName) throws Exception {
        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(students);
        out.close();
    }

    public void loadObjectFile(String fileName) throws Exception {
        ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(fileName));
        Object obj = in.readObject();
        in.close();

        if (obj instanceof ArrayList<?>) {
            ArrayList<?> temp = (ArrayList<?>) obj;
            ArrayList<Student> loadedStudents = new ArrayList<>();
            for (Object element : temp) {
                if (!(element instanceof Student)) {
                    throw new ClassCastException("File does not contain a valid ArrayList<Student>");
                }
                loadedStudents.add((Student) element);
            }
            students = loadedStudents;
        } else {
            throw new ClassCastException("File does not contain a valid ArrayList<Student>");
        }
    }
}
