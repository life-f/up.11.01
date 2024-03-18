public class Lion extends Predator implements ISoundEntity{

    public Lion(int id, String name) {
        super(id, name);
    }

    @Override
    String getType() {
        return "Lion";
    }

    @Override
    public String getSound() {
        return "RRRRRRRRRR";
    }

    @Override
    public String toString() {
        return "Lion{" +
                "catPet=" + catPet +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
