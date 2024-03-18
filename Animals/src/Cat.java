public class Cat extends Animal implements ISoundEntity{

    public Cat(int id, String name) {
        super(id, name, true);
    }

    @Override
    String getType() {
        return "Cat";
    }

    @Override
    public String getSound() {
        return "Moore";
    }

    @Override
    public String toString() {
        return "Cat{" +
                "catPet=" + catPet +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
