import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    private RelayNode relayNode;

    public Server(String ipAddress, int port, int nrOfClients) throws IOException {
        this.serverSocket = new ServerSocket(port, nrOfClients, InetAddress.getByName(ipAddress));
        System.out.println("Server started at " + ipAddress + ":" + port);
        this.relayNode = null;
        startListening();
    }

    public void setRelayNode(RelayNode relayNode) {
        this.relayNode = relayNode;
    }

    private void startListening() {
        new Thread(() -> {
            try {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                    Packet packetData = (Packet) objectInputStream.readObject();
                    System.out.println(getIPAddress() + ": Received packet data: " + packetData);

                    if (relayNode != null) {
                        relayNode.receivePacket(packetData);
                    }

                    objectInputStream.close();
                    clientSocket.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getIPAddress() {
        return serverSocket.getInetAddress().getHostAddress();
    }
}
