package gameengine;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//import java.net.InetAddress;
//import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;


public class Network_UDPServer implements Runnable {
    private Thread thread;
    private static DatagramSocket aSocket_player1 = null;
    private static DatagramSocket aSocket_player2 = null;
    protected static String serviceName = "network_MsgServer";
    protected static String msgAddress = "localhost"; // or "230.1.1.5"
    protected static int serverUDP_player1_port=1111;
    protected static int serverUDP_player2_port=1112;

    public Controller controller;
    
    public BlockingQueue<String> player1ShipDeployQueue;
    public BlockingQueue<String> player2ShipDeployQueue;
    public BlockingQueue<String> player1MoveSaveQueue;
    public BlockingQueue<String> player2MoveSaveQueue;
    public BlockingQueue<String> player1MoveWorkQueue;
    public BlockingQueue<String> player2MoveWorkQueue;
        
    public Network_UDPServer(Controller injectedController){
        this.controller = injectedController;
        thread = new Thread(this, "PlayerThread");
        thread.start();
    }
    public void run() {
        serverReceievingMsg();
    }
    public void serverReceievingMsg() {
        //MulticastSocket aSocket_player1 = null;		
        try {

            //aSocket = new MulticastSocket(1313);
            //aSocket.joinGroup(InetAddress.getByName("230.1.1.5"));
            //byte[] buffer = new byte[1000];
            aSocket_player1 = new DatagramSocket(serverUDP_player1_port);
            aSocket_player2 = new DatagramSocket(serverUDP_player2_port);
            //InetAddress aHost = InetAddress.getByName(msgAddress);
            System.out.println("BattleShip Game message Server Started............");

            while (true) {
                byte[] buffer = new byte[1000];
                byte[] buffer2 = new byte[1000];    
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                DatagramPacket request_fromPlayer2 = new DatagramPacket(buffer2, buffer2.length);
                aSocket_player1.receive(request);
                aSocket_player2.receive(request_fromPlayer2);
                String player1RequestString = new String(request.getData(), "UTF-8");
                String player2RequestString = new String(request_fromPlayer2.getData(), "UTF-8");

                System.out.println("From p1: "+player1RequestString);
                System.out.println("From p2: "+player2RequestString);
                DatagramPacket replyToplayer2 = new DatagramPacket(request.getData(), request.getLength(), request_fromPlayer2.getAddress(),
                                request_fromPlayer2.getPort());
                DatagramPacket replyToplayer1 = new DatagramPacket(request_fromPlayer2.getData(), request_fromPlayer2.getLength(), request.getAddress(),
                                request.getPort());
                aSocket_player1.send(replyToplayer1);
                aSocket_player2.send(replyToplayer2);
            }

        } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
        } finally {
                if (aSocket_player1 != null)
                        aSocket_player1.close();
        }
    }
}