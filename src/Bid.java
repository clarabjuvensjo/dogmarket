/**
 * Clara Bjuvensjö clbj3090
 */

public class Bid implements Comparable<Bid> {
    private int amount;
    private User user;

    public Bid(User user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public int compareTo(Bid other) {
        return this.amount - other.amount;
    }

    public int getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.getName() + " " + amount + " kr";
    }
}
