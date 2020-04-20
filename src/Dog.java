/**
 * Clara Bjuvensjö clbj3090
 */

public class Dog {
    private int age;
    private String breed;
    private String name;
    private User owner;
    private int weight;

    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getTailLength() {
        if (breed.equals("Tax") || breed.equals("Dachshund")) {
            return 3.7;
        } else {
            return age * weight / 10.0;
        }
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public void increaseAge() {
        age++;
    }

    public boolean isOwner(User user) {
        return owner != null && owner.equals(user);
    }

    public void setOwner(User user) {
        owner = user;
    }

    @Override
    public String toString() {
        String ownerString = owner != null ? ", owned by " + owner.getName() : "";
        return name + " " + breed + " " + age + " years " + weight + " kg tail=" + getTailLength() + ownerString;
    }
}
