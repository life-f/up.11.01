public class Bee extends Animal implements IFlyEntity{
    public Bee(int id, String name) {
        super(id, name, true);
    }

    @Override
    String getType() {
        return "Bee";
    }

    @Override
    public double getMaxFlyHeight() {
        return 5.5;
    }

    @Override
    public int getMaxFlyTime() {
        return 5;
    }

    @Override
    public String toString() {
        return "Bee{" +
                "catPet=" + catPet +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
