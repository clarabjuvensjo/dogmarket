/**
 * Clara Bjuvensjö clbj3090
 */

public class Auction implements Comparable<Auction> {
    private Bids bids;
    private Dog dog;
    private int number;

    public Auction(Dog dog, int number) {
        this.dog = dog;
        this.number = number;
        bids = new Bids();
    }

    public void addBid(Bid bid) {
        bids.addBid(bid);
    }

    @Override
    public int compareTo(Auction other) {
        return number - other.number;
    }

    public Bids getBids() {
        return bids;
    }

    public boolean hasBid() {
        return bids.hasBid();
    }

    public boolean isAboutDog(Dog dog) {
        return this.dog.equals(dog);
    }

    public void removeBid(User user) {
        bids.removeBid(user);
    }

    @Override
    public String toString() {
        return "Auction #" + number + ": " + dog.getName() + ". Top bids: [" + bids.toString(3, " ") + "]";
    }
}
