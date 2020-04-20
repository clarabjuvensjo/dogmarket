
/**
 * Clara Bjuvensjö clbj3090
 */

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Dog> dogs;
    private String name;

    public User(String name) {
        this.name = name;
        dogs = new ArrayList<>();
    }

    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    public String getName() {
        return name;
    }

    public boolean isOwner(Dog dog) {
        return dogs.contains(dog);
    }

    public void removeDog(Dog dog) {
        dogs.remove(dog);
    }

    @Override
    public String toString() {
        List<String> dogNames = new ArrayList<>();
        for (Dog dog : dogs) {
            dogNames.add(dog.getName());
        }
        return name + " [" + String.join(", ", dogNames) + "]";
    }
}
