import java.io.IOException;

public class RelayNode {
    private final Server server;
    private final Client client;


    public RelayNode(String myIP, int myPort, int nrOfClients, String nextHopIpAddress, int nextHopPortNr) throws IOException {
        this.server = new Server(myIP, myPort, nrOfClients);
        this.client = new Client(nextHopIpAddress, nextHopPortNr);
        server.setRelayNode(this);
    }

    public void sendPacket(Packet packetData) throws IOException {
        if (packetData.getTargetIP().equals(server.getIPAddress())) {
            System.out.println("Packet reached final destination: " + packetData);
        } else {
            packetData.incrementValue();
            client.sendPacket(packetData);
        }
    }

    public void receivePacket(Packet packetData) throws IOException {
        if (packetData.getTargetIP().equals(server.getIPAddress())) {
            System.out.println("Packet reached final destination: " + packetData);
        } else {
            client.sendPacket(packetData);
        }
    }
}
