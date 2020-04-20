
/**
 * Clara Bjuvensjö clbj3090
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Users {
    private List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String userName) {
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public boolean hasUsers() {
        return !users.isEmpty();
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void removeUserDog(Dog dog) {
        for (User user : users) {
            if (user.isOwner(dog)) {
                user.removeDog(dog);
                return;
            }
        }
    }

    @Override
    public String toString() {
        List<String> userStrings = new ArrayList<>();
        for (User user : users) {
            userStrings.add(user.toString());
        }
        userStrings.sort(Comparator.naturalOrder());
        return String.join("\n", userStrings);
    }
}
