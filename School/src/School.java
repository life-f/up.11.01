import java.util.Arrays;
import java.util.List;

public class School extends Building {
    private int index;
    private String name;
    private List<Student> students;
    private List<Teacher> teachers;

    public School(String address, int floorCount, int index, String name, List<Student> students, List<Teacher> teachers) {
        super(address, floorCount);
        this.index = index;
        this.name = name;
        this.students = students;
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "School{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", \nstudents=" + students +
                ", \nteachers=" + teachers +
                ", \naddress='" + address + '\'' +
                ", \nfloorCount=" + floorCount +
                '}';
    }

    public void nextYear() {
        for (Student student : students) {
            student.nextYear();
        }
        for (Teacher teacher : teachers) {
            teacher.nextYear();
        }
    }

    public void addEntity(Human human) {
        if (human instanceof Student) {
            students.add((Student) human);
        } else if (human instanceof Teacher) {
            teachers.add((Teacher) human);
        } else System.out.println("This is Human");
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
