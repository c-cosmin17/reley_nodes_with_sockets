import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String ip1 = "127.0.0.15";
        String ip2 = "127.0.0.1";
        String ip3 = "127.0.0.2";
        String ip4 = "127.0.0.3";
        String ip5 = "127.0.0.4";
        int port1 = 1234;
        int port2 = 1234;
        int port3 = 1234;
        int port4 = 1234;
        int port5 = 1234;


        RelayNode sender = new RelayNode(ip1, port1, 100, ip2, port2);
        RelayNode d1 = new RelayNode(ip2, port2, 100, ip3, port3);
        RelayNode d2 = new RelayNode(ip3, port3, 100, ip4, port4);
        RelayNode d3 = new RelayNode(ip4, port4, 100, ip5, port5);

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int destinationIndex = random.nextInt(3) + 1;
            int randomNumber = random.nextInt(127) + 1;
            String destinationIp = "127.0.0." + destinationIndex;
            Packet packetData = new Packet(destinationIp, randomNumber);
            sender.sendPacket(packetData);
        }
    }
}
