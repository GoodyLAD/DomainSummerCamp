// Door ke possible states
enum DoorState {
    OPEN,
    CLOSED,
    LOCKED
}

// Custom unchecked exception
class IllegalStateTransitionException
        extends RuntimeException {

    public IllegalStateTransitionException(String message) {
        super(message);
    }
}

// Vault door class
class VaultDoor {

    // State ko strictly private rakha gaya hai
    private DoorState currentState;

    public VaultDoor() {

        // Initial state OPEN maan lete hain
        currentState = DoorState.OPEN;
    }

    // Door close karne ke liye
    public void closeDoor() {

        if (currentState == DoorState.OPEN) {

            currentState = DoorState.CLOSED;

            System.out.println(
                    "Vault door successfully closed."
            );
        } else {

            System.out.println(
                    "Door already closed or locked."
            );
        }
    }

    // Door lock karne ke liye
    public void lockDoor() {

        // Direct OPEN -> LOCKED allowed nahi hai
        if (currentState == DoorState.OPEN) {

            throw new IllegalStateTransitionException(
                    "Cannot lock vault door while it is OPEN. Close the door first."
            );
        }

        if (currentState == DoorState.CLOSED) {

            currentState = DoorState.LOCKED;

            System.out.println(
                    "Vault door successfully locked."
            );
        }
    }

    // Door unlock karne ke liye
    public void unlockDoor() {

        if (currentState == DoorState.LOCKED) {

            currentState = DoorState.CLOSED;

            System.out.println(
                    "Vault door successfully unlocked."
            );
        } else {

            System.out.println(
                    "Door is not locked."
            );
        }
    }

    // Sirf display ke liye state dekh sakte hain
    public void displayState() {

        System.out.println(
                "Current State : " + currentState
        );
    }
}

// Driver class
public class VaultGuardSystem {

    public static void main(String[] args) {

        VaultDoor mainVault = new VaultDoor();

        mainVault.displayState();

        try {

            // Illegal transition
            mainVault.lockDoor();

        } catch (IllegalStateTransitionException e) {

            System.out.println(
                    "Exception: " + e.getMessage()
            );
        }

        System.out.println();

        mainVault.closeDoor();
        mainVault.lockDoor();
        mainVault.displayState();

        System.out.println();

        mainVault.unlockDoor();
        mainVault.displayState();
    }
}