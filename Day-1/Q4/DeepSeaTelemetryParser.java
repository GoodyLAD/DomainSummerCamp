// Checked Exception
// Isko handle ya declare karna mandatory hai
class HardwareLockException extends Exception {

    public HardwareLockException(String message) {
        super(message);
    }
}

// Unchecked Exception
// Isko handle karna mandatory nahi hai
class SensorCorruptionException extends RuntimeException {

    public SensorCorruptionException(String message) {
        super(message);
    }
}

// Dummy resource class
// Try-With-Resources me use karne ke liye AutoCloseable implement kiya
class TelemetryStream implements AutoCloseable {

    public void readData() {
        System.out.println("Telemetry data read ho raha hai...");
    }

    @Override
    public void close() {
        System.out.println("TelemetryStream safely close ho gaya.");
    }
}

public class DeepSeaTelemetryParser {

    // Hardware lock checked exception throw kar sakta hai
    public static void parseTelemetry(boolean fileLocked,
                                      boolean sensorCorrupted)
            throws HardwareLockException {

        // Resource automatically close hoga
        try (TelemetryStream stream = new TelemetryStream()) {

            stream.readData();

            // Fatal file lock issue
            if (fileLocked) {
                throw new HardwareLockException(
                        "Telemetry file OS dwara lock ki gayi hai."
                );
            }

            // Recoverable sensor issue
            if (sensorCorrupted) {
                throw new SensorCorruptionException(
                        "Sensor impossible value report kar raha hai."
                );
            }

            System.out.println("Telemetry successfully parsed.");
        }
    }

    public static void main(String[] args) {

        try {

            parseTelemetry(false, true);

        } catch (HardwareLockException e) {

            System.out.println("Hardware Error: " + e.getMessage());

        } catch (SensorCorruptionException e) {

            System.out.println("Sensor Warning Logged: "
                    + e.getMessage());
        }
    }
}