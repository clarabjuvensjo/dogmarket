
/**
 * Clara Bjuvensjö clbj3090
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Auctions {
    private List<Auction> auctions;
    private int counter;

    public Auctions() {
        auctions = new ArrayList<>();
        counter = 1;
    }

    public Auction getAuction(Dog dog) {
        for (Auction auction : auctions) {
            if (auction.isAboutDog(dog)) {
                return auction;
            }
        }
        return null;
    }

    public boolean hasAuctions() {
        return !auctions.isEmpty();
    }

    public void removeAuction(Dog dog) {
        auctions.remove(getAuction(dog));
    }

    public void removeBidsOfUser(User user) {
        for (Auction auction : auctions) {
            auction.removeBid(user);
        }
    }

    public int startAuction(Dog dog) {
        int number = counter;
        auctions.add(new Auction(dog, counter++));
        return number;
    }

    @Override
    public String toString() {
        List<Auction> values = new ArrayList<>(auctions);
        values.sort(Comparator.naturalOrder());
        List<String> auctionStrings = new ArrayList<>();
        for (Auction auction : values) {
            auctionStrings.add(auction.toString());
        }
        return String.join("\n", auctionStrings);
    }
}
