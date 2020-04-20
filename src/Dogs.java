
/**
 * Clara Bjuvensjö clbj3090
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dogs {
    private List<Dog> dogs;

    public Dogs() {
        dogs = new ArrayList<>();
    }

    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    public Dog getDog(String dogName) {
        for (Dog dog : dogs) {
            if (dog.getName().equals(dogName)) {
                return dog;
            }
        }
        return null;
    }

    public boolean hasDogs() {
        return !dogs.isEmpty();
    }

    public void removeDog(Dog dog) {
        dogs.remove(dog);
    }

    public void removeDogsOwnedByUser(User user) {
        Dog dog = null;
        for (int i = 0; i < dogs.size() && dog == null; i++) {
            if (dogs.get(i).isOwner(user)) {
                dog = dogs.get(i);
            }
        }
        dogs.remove(dog);
    }

    public String toString(double minimumTailLength) {
        List<Dog> foundDogs = new ArrayList<>();
        for (Dog dog : dogs) {
            if (dog.getTailLength() >= minimumTailLength) {
                foundDogs.add(dog);
            }
        }
        List<String> dogStrings = new ArrayList<>();
        for (Dog dog : sort(foundDogs)) {
            dogStrings.add(dog.toString());
        }
        return String.join("\n", dogStrings);
    }

    private boolean isSmaller(Dog oneDog, Dog anotherDog) {
        if (oneDog.getTailLength() < anotherDog.getTailLength()) {
            return true;
        }
        if (oneDog.getTailLength() == anotherDog.getTailLength()
                && oneDog.getName().compareToIgnoreCase(anotherDog.getName()) < 0) {
            return true;
        }
        return false;
    }

    private List<Dog> sort(List<Dog> dogs) {
        Dog[] dogArray = new Dog[dogs.size()];
        for (int i = 0; i < dogs.size(); i++) {
            dogArray[i] = dogs.get(i);
        }

        Dog dog;
        for (int i = 1; i < dogArray.length; i++) {
            for (int j = i; j > 0; j--) {
                if (isSmaller(dogArray[j], dogArray[j - 1])) {
                    dog = dogArray[j];
                    dogArray[j] = dogArray[j - 1];
                    dogArray[j - 1] = dog;
                }
            }
        }
        return Arrays.asList(dogArray);
    }
}
