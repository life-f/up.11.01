public class Teacher extends Human {
    private String subject;
    private int exp;

    public Teacher(String name, int age, boolean isWoman, String subject, int exp) {
        super(name, age, isWoman);
        this.subject = subject;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject='" + subject + '\'' +
                ", exp=" + exp +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isWoman=" + isWoman +
                '}';
    }

    @Override
    public void nextYear() {
        super.nextYear();
        exp++;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
