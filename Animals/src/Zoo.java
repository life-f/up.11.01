import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private String title;
    List<Animal> animals;

    public Zoo(String title, List<Animal> animals) {
        this.title = title;
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "title='" + title + '\'' +
                ", \nanimals=" + animals +
                '}';
    }

    List<Predator> getAllPredators() {
        List<Predator> predators = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Predator) {
                predators.add((Predator) animal);
            }
        }
        return predators;
    }

    List<IFlyEntity> getAllIFlyEntity() {
        List<IFlyEntity> flying = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof IFlyEntity) {
                flying.add((IFlyEntity) animal);
            }
        }
        return flying;
    }

    void playAllAnimalSounds() {
        List<String> sounds = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof ISoundEntity) {
                if (!sounds.contains(((ISoundEntity) animal).getSound())) {
                    sounds.add(((ISoundEntity) animal).getSound());
                    System.out.println(((ISoundEntity) animal).getSound());
                }
            }
        }
    }
}
