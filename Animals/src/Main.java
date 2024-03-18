import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Animal> animals = new ArrayList<>();
        Bee bee = new Bee(1, "Ben");
        Parrot parrot = new Parrot(2, "Archi");
        Cat cat1 = new Cat(3, "Murka");
        Cat cat2 = new Cat(4, "Murzik");
        Lion lion = new Lion(5, "Genry");
        animals.add(bee);
        animals.add(parrot);
        animals.add(cat1);
        animals.add(cat2);
        animals.add(lion);
        Zoo zoo = new Zoo("ZOOpark", animals);

        System.out.println(zoo);
        System.out.println(zoo.getAllPredators());
        System.out.println(zoo.getAllIFlyEntity());
        zoo.playAllAnimalSounds();
    }
}
