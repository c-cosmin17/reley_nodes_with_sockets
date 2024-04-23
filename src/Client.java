import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
    private final String ipAddress;
    private final int port;

    public Client(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void sendPacket(Packet packet) {
        try (Socket socket = new Socket(ipAddress, port);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            packet.incrementValue();
            objectOutputStream.writeObject(packet);
            objectOutputStream.flush();
            System.out.println("Packet passed: " + ipAddress);
            System.out.println("Sent packet: " + packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
