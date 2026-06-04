// Base abstract class
abstract class SmartDevice {

    protected String deviceId;
    protected String deviceName;

    public SmartDevice(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    // Har device apna diagnostic khud implement karega
    public abstract void runDiagnostic();
}

// Battery operated devices ke liye interface
interface BatteryOperated {

    int getBatteryLevel();

    void triggerRechargeAlert();
}

// Smart Light (battery use nahi karti)
class SmartLight extends SmartDevice {

    public SmartLight(String deviceId, String deviceName) {
        super(deviceId, deviceName);
    }

    @Override
    public void runDiagnostic() {
        System.out.println(deviceName
                + " : Light diagnostic completed.");
    }
}

// Smart Camera (battery operated)
class SmartCamera extends SmartDevice
        implements BatteryOperated {

    private int batteryLevel;

    public SmartCamera(String deviceId,
                       String deviceName,
                       int batteryLevel) {

        super(deviceId, deviceName);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void runDiagnostic() {
        System.out.println(deviceName
                + " : Camera recording test successful.");
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void triggerRechargeAlert() {
        System.out.println("Recharge Alert -> "
                + deviceName
                + " battery is low!");
    }
}

// Smart Lock (battery operated)
class SmartLock extends SmartDevice
        implements BatteryOperated {

    private int batteryLevel;

    public SmartLock(String deviceId,
                     String deviceName,
                     int batteryLevel) {

        super(deviceId, deviceName);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void runDiagnostic() {
        System.out.println(deviceName
                + " : Lock/Unlock test successful.");
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void triggerRechargeAlert() {
        System.out.println("Recharge Alert -> "
                + deviceName
                + " battery is low!");
    }
}

// Hub manager class
class HomeHub {

    public void executeNightlyRoutine(
            SmartDevice[] devices) {

        for (SmartDevice device : devices) {

            // Polymorphic method call
            device.runDiagnostic();

            // Safe type checking + downcasting
            if (device instanceof BatteryOperated) {

                BatteryOperated batteryDevice =
                        (BatteryOperated) device;

                if (batteryDevice.getBatteryLevel() < 20) {
                    batteryDevice.triggerRechargeAlert();
                }
            }

            System.out.println();
        }
    }
}

// Driver class
public class EcoSmartController {

    public static void main(String[] args) {

        SmartDevice[] smartDevices = {

                new SmartLight(
                        "L101",
                        "Living Room Light"
                ),

                new SmartCamera(
                        "C205",
                        "Front Door Camera",
                        15
                ),

                new SmartLock(
                        "S309",
                        "Main Entrance Lock",
                        45
                ),

                new SmartLock(
                        "S412",
                        "Garage Lock",
                        10
                )
        };

        HomeHub centralHub = new HomeHub();

        centralHub.executeNightlyRoutine(
                smartDevices
        );
    }
}