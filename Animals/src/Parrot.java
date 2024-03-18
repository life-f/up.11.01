public class Parrot extends Animal implements ISoundEntity, IFlyEntity{
    public Parrot(int id, String name) {
        super(id, name, true);
    }

    @Override
    String getType() {
        return "Parrot";
    }

    @Override
    public double getMaxFlyHeight() {
        return 20.5d;
    }

    @Override
    public int getMaxFlyTime() {
        return 15;
    }

    @Override
    public String getSound() {
        return "Karrr";
    }

    @Override
    public String toString() {
        return "Parrot{" +
                "catPet=" + catPet +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
