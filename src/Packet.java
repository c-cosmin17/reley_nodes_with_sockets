import java.io.Serializable;

public class Packet implements Serializable {
    private final String targetIP;
    private int value;

    public Packet(String targetIP, int value) {
        this.targetIP = targetIP;
        this.value = value;
    }

    public String getTargetIP() {
        return targetIP;
    }

    public int getValue() {
        return value;
    }

    public void incrementValue() {
        value++;
    }

    @Override
    public String toString() {
        return "Packet{destinationIP='" + targetIP + "', value=" + value + '}';
    }
}
