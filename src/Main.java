import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {

            System.out.println("\n   STUDENT RECORD SYSTEM   ");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Generate Report");
            System.out.println("7. Save Data");
            System.out.println("8. Load Data");
            System.out.println("9. Exit");

            System.out.print("Choose: ");
            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Name: ");
                    String name = input.nextLine();

                    System.out.print("Department: ");
                    String dept = input.nextLine();

                    System.out.print("GPA: ");
                    double gpa = input.nextDouble();

                    manager.addStudent(
                            new Student(id, name, dept, gpa));
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    Student s =
                            manager.searchStudent(input.nextInt());

                    if (s != null)
                        System.out.println(s);
                    else
                        System.out.println("Not found.");
                    break;

                case 3:
                    System.out.print("ID: ");
                    int uid = input.nextInt();
                    input.nextLine();

                    System.out.print("New Name: ");
                    String uname = input.nextLine();

                    System.out.print("Department: ");
                    String udept = input.nextLine();

                    System.out.print("GPA: ");
                    double ugpa = input.nextDouble();

                    if (manager.updateStudent(
                            uid, uname, udept, ugpa))
                        System.out.println("Updated.");
                    else
                        System.out.println("Student not found.");
                    break;

                case 4:
                    System.out.print("ID: ");
                    int did = input.nextInt();

                    if (manager.deleteStudent(did))
                        System.out.println("Deleted.");
                    else
                        System.out.println("Student not found.");
                    break;

                case 5:
                    manager.displayAllStudents();
                    break;

                case 6:
                    manager.generateReport();
                    break;

                case 7:
                    try {
                        manager.saveObjectFile("students.dat");
                        System.out.println("Saved.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    try {
                        manager.loadObjectFile("students.dat");
                        System.out.println("Loaded.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    input.close();
                    System.exit(0);
            }
        }
    }
}