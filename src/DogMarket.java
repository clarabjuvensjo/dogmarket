/**
 * Clara Bjuvensjö clbj3090
 */

public class DogMarket {
    private Auctions auctions;
    private Dogs dogs;
    private Users users;
    private UserInterface ui;

    public DogMarket(UserInterface ui) {
        dogs = new Dogs();
        auctions = new Auctions();
        users = new Users();
        this.ui = ui;
    }

    public void run() {
        String command;
        do {
            command = ui.readCommand();
            switch (command) {
            case "close auction":
                closeAuction();
                break;
            case "exit":
                exit();
                break;
            case "increase age":
                increaseAge();
                break;
            case "list auctions":
                listAuctions();
                break;
            case "list bids":
                listBids();
                break;
            case "list dogs":
                listDogs();
                break;
            case "list users":
                listUsers();
                break;
            case "make bid":
                makeBid();
                break;
            case "register new dog":
                registerNewDog();
                break;
            case "register new user":
                registerNewUser();
                break;
            case "remove dog":
                removeDog();
                break;
            case "remove user":
                removeUser();
                break;
            case "start auction":
                startAuction();
                break;
            default:
                ui.writeError("Unknown command");
                break;
            }
        } while (!"exit".equals(command));
    }

    private void closeAuction() {
        String dogName = ui.readDogName();
        if (dogIsRegistered(dogName) && auctionIsStarted(dogName)) {
            String message;
            Dog dog = dogs.getDog(dogName);
            if (!auctions.getAuction(dog).hasBid()) {
                message = "The auction is closed. No bids where made for " + dogName;
            } else {
                Bid topBid = auctions.getAuction(dog).getBids().getTopBid();
                User user = topBid.getUser();
                dog.setOwner(user);
                user.addDog(dog);
                message = "The auction for " + dogName + " is closed. The winning bid was " + topBid.getAmount()
                        + " kr and was made by " + user.getName();
            }
            auctions.removeAuction(dog);
            ui.write(message);
        }
    }

    private void exit() {
        ui.close();
        ui.write("Goodbye!");
    }

    private void increaseAge() {
        String dogName = ui.readDogName();
        if (dogIsRegistered(dogName)) {
            dogs.getDog(dogName).increaseAge();
        }
    }

    private void listAuctions() {
        if (anyAuctionIsStarted()) {
            ui.write(auctions);
        }
    }

    private void listBids() {
        String dogName = ui.readDogName();
        if (dogIsRegistered(dogName) && dogHasBid(dogName)) {
            ui.write("Here are the bids for this auction\n" + auctions.getAuction(dogs.getDog(dogName)).getBids());
        }
    }

    private void listDogs() {
        if (anyDogIsRegistered()) {
            ui.write(dogs.toString(ui.readMinimumTailLength()));
        }
    }

    private void listUsers() {
        if (anyUserIsRegistered()) {
            ui.write(users);
        }
    }

    private void makeBid() {
        String userName = ui.readUserName();
        if (userIsRegistered(userName)) {
            String dogName = ui.readDogName();
            if (dogIsRegistered(dogName) && auctionIsStarted(dogName)) {
                Auction auction = auctions.getAuction(dogs.getDog(dogName));
                User user = users.getUser(userName);
                auction.removeBid(user);

                int minimunBidAmount = 1;
                if (auction.hasBid()) {
                    minimunBidAmount = auction.getBids().getTopBid().getAmount() + 1;
                }

                auction.addBid(new Bid(user, ui.readBidAmount(minimunBidAmount)));
                ui.write("Bid made");
            }
        }
    }

    private void registerNewDog() {
        String dogName = ui.readDogName();
        if (dogIsNotRegistered(dogName)) {
            dogs.addDog(new Dog(dogName, ui.readBreed(), ui.readAge(), ui.readWeight()));
            ui.write(dogName + " is added to the register");
        }
    }

    private void registerNewUser() {
        String userName = ui.readUserName();
        if (userIsNotRegistered(userName)) {
            users.addUser(new User(userName));
            ui.write(userName + " is added to the register");
        }
    }

    private void removeDog() {
        String dogName = ui.readDogName();
        if (dogIsRegistered(dogName)) {
            Dog dog = dogs.getDog(dogName);
            auctions.removeAuction(dog);
            users.removeUserDog(dog);
            dogs.removeDog(dog);
            ui.write(dogName + " is removed from the register");
        }
    }

    private void removeUser() {
        String userName = ui.readUserName();
        if (userIsRegistered(userName)) {
            User user = users.getUser(userName);
            auctions.removeBidsOfUser(user);
            dogs.removeDogsOwnedByUser(user);
            users.removeUser(user);
            ui.write(userName + " is removed from the register");
        }
    }

    private void startAuction() {
        String dogName = ui.readDogName();
        if (dogIsRegistered(dogName) && auctionIsNotStarted(dogName) && dogHasNoOwner(dogName)) {
            int number = auctions.startAuction(dogs.getDog(dogName));
            ui.write(dogName + " has been put up for auction in auction #" + number);
        }
    }

    private boolean isTrue(boolean reallyTrue, String falseMessage) {
        if (!reallyTrue) {
            ui.writeError(falseMessage);
        }
        return reallyTrue;
    }

    private boolean anyAuctionIsStarted() {
        return isTrue(auctions.hasAuctions(), "No auctions in register");
    }

    private boolean auctionIsNotStarted(String dogName) {
        return isTrue(auctions.getAuction(dogs.getDog(dogName)) == null, dogName + " is already up for auction");
    }

    private boolean auctionIsStarted(String dogName) {
        return isTrue(auctions.getAuction(dogs.getDog(dogName)) != null, dogName + " is not up for auction");
    }

    private boolean anyDogIsRegistered() {
        return isTrue(dogs.hasDogs(), "No dogs in register");
    }

    private boolean dogHasBid(String dogName) {
        Auction auction = auctions.getAuction(dogs.getDog(dogName));
        return isTrue(auction != null && auction.getBids().hasBid(), "No bids registered yet for " + dogName);
    }

    private boolean dogHasNoOwner(String dogName) {
        return isTrue(!dogs.getDog(dogName).hasOwner(), dogName + " already has an owner");
    }

    private boolean dogIsNotRegistered(String dogName) {
        return isTrue(dogs.getDog(dogName) == null, dogName + " is already in register");
    }

    private boolean dogIsRegistered(String dogName) {
        return isTrue(dogs.getDog(dogName) != null, dogName + " is not in register");
    }

    private boolean anyUserIsRegistered() {
        return isTrue(users.hasUsers(), "No users in register");
    }

    private boolean userIsNotRegistered(String userName) {
        return isTrue(users.getUser(userName) == null, userName + " is already in register");
    }

    private boolean userIsRegistered(String userName) {
        return isTrue(users.getUser(userName) != null, userName + " is not in register");
    }

    public static void main(String[] args) {
        new DogMarket(new UserInterface()).run();
    }
}
