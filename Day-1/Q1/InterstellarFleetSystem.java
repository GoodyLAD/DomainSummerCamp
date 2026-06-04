// Base class jo common spaceship details rakhegi
abstract class SpaceVessel {

    // Ship ki unique id (30000 se kam)
    protected short vesselId;

    // Ship active hai ya nahi
    protected boolean miningActive;

    // Fleet category A/B/C
    protected char classCode;

    public SpaceVessel(short vesselId, boolean miningActive, char classCode) {
        this.vesselId = vesselId;
        this.miningActive = miningActive;
        this.classCode = classCode;
    }
}

// Mining ship class
class MiningShip extends SpaceVessel {

    // 2D cargo matrix
    private float[][] oreStorage;

    public MiningShip(short vesselId, boolean miningActive,
                      char classCode, byte bayCount,
                      byte containerCount) {

        super(vesselId, miningActive, classCode);
        oreStorage = new float[bayCount][containerCount];
    }

    // Particular container me ore weight store karna
    public void loadOre(int bayIndex, int containerIndex, float oreWeight) {
        oreStorage[bayIndex][containerIndex] = oreWeight;
    }

    // Puri ship ka total ore weight
    public float calculateTotalOreWeight() {
        float totalWeight = 0.0f;

        for (int row = 0; row < oreStorage.length; row++) {
            for (int col = 0; col < oreStorage[row].length; col++) {
                totalWeight += oreStorage[row][col];
            }
        }

        return totalWeight;
    }

    // Sabse heavy container ka weight
    public float findHeaviestContainer() {
        float heaviestWeight = 0.0f;

        for (int row = 0; row < oreStorage.length; row++) {
            for (int col = 0; col < oreStorage[row].length; col++) {
                if (oreStorage[row][col] > heaviestWeight) {
                    heaviestWeight = oreStorage[row][col];
                }
            }
        }

        return heaviestWeight;
    }
}

// Driver class
public class InterstellarFleetSystem {

    public static void main(String[] args) {

        // Fleet ko 1D array of SpaceVessel me maintain karenge
        SpaceVessel[] fleetRegistry = new SpaceVessel[3];

        // Random ships create kiye
        MiningShip titanDrill = new MiningShip(
                (short) 28451,
                true,
                'C',
                (byte) 3,
                (byte) 4
        );

        MiningShip nebulaExtractor = new MiningShip(
                (short) 19387,
                false,
                'A',
                (byte) 2,
                (byte) 5
        );

        MiningShip cosmicHarvester = new MiningShip(
                (short) 27654,
                true,
                'B',
                (byte) 4,
                (byte) 3
        );

        // Random ore values
        titanDrill.loadOre(0, 0, 8421.75f);
        titanDrill.loadOre(0, 1, 16750.25f);
        titanDrill.loadOre(1, 2, 29890.50f);
        titanDrill.loadOre(2, 3, 12400.00f);

        nebulaExtractor.loadOre(0, 0, 4500.40f);
        nebulaExtractor.loadOre(1, 3, 11220.90f);

        cosmicHarvester.loadOre(0, 1, 32000.75f);
        cosmicHarvester.loadOre(2, 2, 18750.30f);
        cosmicHarvester.loadOre(3, 0, 41500.80f);

        // Fleet array me add kiya
        fleetRegistry[0] = titanDrill;
        fleetRegistry[1] = nebulaExtractor;
        fleetRegistry[2] = cosmicHarvester;

        // Output
        System.out.println("Titan Drill Total Ore = "
                + titanDrill.calculateTotalOreWeight());

        System.out.println("Titan Drill Heaviest Container = "
                + titanDrill.findHeaviestContainer());

        System.out.println("Nebula Extractor Total Ore = "
                + nebulaExtractor.calculateTotalOreWeight());

        System.out.println("Nebula Extractor Heaviest Container = "
                + nebulaExtractor.findHeaviestContainer());

        System.out.println("Cosmic Harvester Total Ore = "
                + cosmicHarvester.calculateTotalOreWeight());

        System.out.println("Cosmic Harvester Heaviest Container = "
                + cosmicHarvester.findHeaviestContainer());
    }
}