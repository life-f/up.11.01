import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        School school = new School(
//                23,
//                "mathematic",
//                new Student[]{
//                        new Student("Petya", 17, 10),
//                        new Student("Vasya", 18, 11)
//                },
//                new Teacher[]{
//                        new Teacher("Ivanov", "math"),
//                        new Teacher("Petrov", "biology")
//                });
//        System.out.println(school);
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        for(int i = 0; i<3 ; i++){
            students.add(new Student("student"+i, 11+i, false, 5+i));
            teachers.add(new Teacher( "teacher"+i, 20+i*5, true, "subject"+1, i*5));
        }
        School school = new School(
                "Lenina 55",
                3,
                23,
                "mathematic",
                students,
                teachers
        );
        System.out.println(school);
        school.nextYear();
        System.out.println(school);

    }
}
