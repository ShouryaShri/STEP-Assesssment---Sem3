class Student{
    String name;
    int attendance;
    static String CollegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;
    Student(String name, int attendance){
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }
    static void printCollegeInfo(){
        System.out.println(CollegeName);
        System.out.println("Student created: "+studentCount);
    }
}
public class M5 {
    public static void main(String[] args){
        Student s1 = new Student("Stud1", 75);
        Student s2 = new Student("Stud2",85);
        Student.printCollegeInfo();
    }
}
