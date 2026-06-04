public class ColonyGridPowerManager {

    // Sirf ek byte me 8 sectors ki state store hogi
    private byte sectorStates = 0;

    // Particular sector ON karne ke liye
    public void turnOnSector(int sectorIndex) {

        // OR aur Left Shift use karke bit ko 1 set kar diya
        sectorStates = (byte) (sectorStates | (1 << sectorIndex));
    }

    // Particular sector OFF karne ke liye
    public void turnOffSector(int sectorIndex) {

        // AND + NOT + Left Shift use karke bit ko 0 set kiya
        sectorStates = (byte) (sectorStates & ~(1 << sectorIndex));
    }

    // Check karega sector ON hai ya nahi
    public boolean isSectorOn(int sectorIndex) {

        // Agar result 0 nahi hai to sector ON hai
        return (sectorStates & (1 << sectorIndex)) != 0;
    }

    // Current binary state print karne ke liye
    public void displaySectorStates() {

        String binaryState = String.format("%8s",
                Integer.toBinaryString(sectorStates & 0xFF))
                .replace(' ', '0');

        System.out.println("Sector States: " + binaryState);
    }

    public static void main(String[] args) {

        ColonyGridPowerManager powerNode =
                new ColonyGridPowerManager();

        // Kuch sectors ON kiye
        powerNode.turnOnSector(0);
        powerNode.turnOnSector(3);
        powerNode.turnOnSector(6);

        powerNode.displaySectorStates();

        System.out.println("Sector 3 ON? "
                + powerNode.isSectorOn(3));

        System.out.println("Sector 5 ON? "
                + powerNode.isSectorOn(5));

        // Sector 3 OFF kiya
        powerNode.turnOffSector(3);

        powerNode.displaySectorStates();

        System.out.println("Sector 3 ON? "
                + powerNode.isSectorOn(3));
    }
}