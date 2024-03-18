public class Student extends Human {
    private int level;

    public Student(String name, int age, boolean isWoman, int level) {
        super(name, age, isWoman);
        this.level = level;
    }

    @Override
    public String toString() {
        return "Student{" +
                "level=" + level +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isWoman=" + isWoman +
                '}';
    }

    @Override
    public void nextYear(){
        super.nextYear();
        level++;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
