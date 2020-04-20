
/**
 * Clara Bjuvensjö clbj3090
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Bids {
    private List<Bid> bids;

    public Bids() {
        this.bids = new ArrayList<>();
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public Bid getTopBid() {
        return Collections.max(bids);
    }

    public boolean hasBid() {
        return !bids.isEmpty();
    }

    public void removeBid(User user) {
        Bid bid = null;
        for (int i = 0; i < bids.size() && bid == null; i++) {
            if (bids.get(i).getUser().equals(user)) {
                bid = bids.get(i);
            }
        }
        bids.remove(bid);
    }

    @Override
    public String toString() {
        return toString(bids.size(), "\n");
    }

    public String toString(int n, String delimiter) {
        List<Bid> values = new ArrayList<>(bids);
        values.sort(Comparator.reverseOrder());
        List<String> bidStrings = new ArrayList<>();
        for (Bid bid : values.subList(0, Math.min(n, bids.size()))) {
            bidStrings.add(bid.toString());
        }
        return String.join(delimiter, bidStrings);
    }
}
