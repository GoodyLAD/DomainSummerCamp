import java.util.*;

// Passenger class
class Passenger {

    private String passportNumber;
    private String fullName;
    private String nationality;

    public Passenger(String passportNumber,
                     String fullName,
                     String nationality) {

        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.nationality = nationality;
    }

    // Identity sirf passportNumber + nationality se decide hogi
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Passenger otherPassenger = (Passenger) obj;

        return Objects.equals(passportNumber,
                              otherPassenger.passportNumber)
                &&
                Objects.equals(nationality,
                              otherPassenger.nationality);
    }

    // Hash bhi same fields par based hoga
    @Override
    public int hashCode() {
        return Objects.hash(passportNumber, nationality);
    }

    @Override
    public String toString() {
        return fullName + " (" + passportNumber + ")";
    }
}

// Manager class
class ManifestManager {

    // O(1) average lookup ke liye HashSet
    private Set<Passenger> globalNoFlyList =
            new HashSet<>();

    // Flight wise passengers, order maintain karne ke liye List
    private Map<String, List<Passenger>> flightRosters =
            new HashMap<>();

    // Passenger -> Flight mapping
    private Map<Passenger, String> globalPassengerDirectory =
            new HashMap<>();

    // Passenger ko no-fly list me add karna
    public void addToNoFlyList(Passenger passenger) {
        globalNoFlyList.add(passenger);
    }

    // O(1) average lookup
    public boolean isBannedPassenger(Passenger passenger) {
        return globalNoFlyList.contains(passenger);
    }

    // Passenger check-in
    public void checkInPassenger(String flightNumber,
                                 Passenger passenger) {

        // Flight exist nahi karti to nayi list bana do
        flightRosters.putIfAbsent(
                flightNumber,
                new ArrayList<>()
        );

        // Check-in order preserve hoga
        flightRosters.get(flightNumber)
                     .add(passenger);

        // Global directory update
        globalPassengerDirectory.put(
                passenger,
                flightNumber
        );
    }

    // Passenger kis flight me hai
    public String findPassengerFlight(
            Passenger passenger) {

        return globalPassengerDirectory
                .get(passenger);
    }

    // Flight roster print karne ke liye
    public void displayFlightRoster(
            String flightNumber) {

        System.out.println(
                "\nPassengers for Flight "
                        + flightNumber + ":"
        );

        List<Passenger> roster =
                flightRosters.get(flightNumber);

        if (roster != null) {

            for (Passenger passenger : roster) {
                System.out.println(passenger);
            }
        }
    }
}

// Driver class
public class GlobalAirlineManifest {

    public static void main(String[] args) {

        ManifestManager manifestSystem =
                new ManifestManager();

        Passenger passengerOne =
                new Passenger(
                        "IN78231",
                        "Arjun Malhotra",
                        "Indian"
                );

        Passenger passengerTwo =
                new Passenger(
                        "US55678",
                        "Sophia Carter",
                        "American"
                );

        Passenger passengerThree =
                new Passenger(
                        "UK99221",
                        "Oliver Brown",
                        "British"
                );

        // No Fly List
        manifestSystem.addToNoFlyList(
                passengerThree
        );

        System.out.println(
                "Is Oliver banned? "
                + manifestSystem
                .isBannedPassenger(passengerThree)
        );

        // Flight check-ins
        manifestSystem.checkInPassenger(
                "SK101",
                passengerOne
        );

        manifestSystem.checkInPassenger(
                "SK101",
                passengerTwo
        );

        manifestSystem.checkInPassenger(
                "SK202",
                passengerThree
        );

        // Flight lookup O(1)
        System.out.println(
                "\nArjun's Flight: "
                + manifestSystem
                .findPassengerFlight(passengerOne)
        );

        System.out.println(
                "Sophia's Flight: "
                + manifestSystem
                .findPassengerFlight(passengerTwo)
        );

        // Roster order preserved
        manifestSystem.displayFlightRoster(
                "SK101"
        );
    }
}