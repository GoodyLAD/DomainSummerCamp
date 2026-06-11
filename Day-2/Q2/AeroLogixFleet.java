// Base abstract class
abstract class DeliveryDrone {

    protected String droneId;

    public DeliveryDrone(String droneId) {
        this.droneId = droneId;
    }

    // Har drone apne tareeke se package deliver karega
    public abstract void deliverPackage();
}

// Flying drones ke liye interface
interface Airborne {

    void flyToDestination();

    // Java 8+ default method
    default void requestAirTrafficClearance() {
        System.out.println(
                "Air Traffic Control Clearance Granted."
        );
    }
}

// Ground drones ke liye interface
interface GroundBased {

    void navigateSidewalks();
}

// Quadcopter sirf fly karta hai
class Quadcopter extends DeliveryDrone
        implements Airborne {

    public Quadcopter(String droneId) {
        super(droneId);
    }

    @Override
    public void flyToDestination() {
        System.out.println(
                "Quadcopter flying towards destination."
        );
    }

    @Override
    public void deliverPackage() {

        requestAirTrafficClearance();
        flyToDestination();

        System.out.println(
                "Quadcopter delivered package."
        );
    }
}

// City Rover sirf ground par move karta hai
class CityRover extends DeliveryDrone
        implements GroundBased {

    public CityRover(String droneId) {
        super(droneId);
    }

    @Override
    public void navigateSidewalks() {
        System.out.println(
                "City Rover navigating sidewalks."
        );
    }

    @Override
    public void deliverPackage() {

        navigateSidewalks();

        System.out.println(
                "City Rover delivered package."
        );
    }
}

// Hybrid VTOL dono capabilities rakhta hai
class HybridVTOL extends DeliveryDrone
        implements Airborne, GroundBased {

    public HybridVTOL(String droneId) {
        super(droneId);
    }

    @Override
    public void flyToDestination() {
        System.out.println(
                "Hybrid VTOL flying in air."
        );
    }

    @Override
    public void navigateSidewalks() {
        System.out.println(
                "Hybrid VTOL moving on ground."
        );
    }

    @Override
    public void deliverPackage() {

        requestAirTrafficClearance();

        flyToDestination();
        navigateSidewalks();

        System.out.println(
                "Hybrid VTOL delivered package."
        );
    }
}

// Driver class
public class AeroLogixFleet {

    public static void main(String[] args) {

        DeliveryDrone quadcopter =
                new Quadcopter("QD-101");

        DeliveryDrone cityRover =
                new CityRover("CR-205");

        DeliveryDrone hybridVtol =
                new HybridVTOL("HV-999");

        System.out.println("=== Quadcopter ===");
        quadcopter.deliverPackage();

        System.out.println("\n=== City Rover ===");
        cityRover.deliverPackage();

        System.out.println("\n=== Hybrid VTOL ===");
        hybridVtol.deliverPackage();
    }
}