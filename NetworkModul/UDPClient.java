import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) throws Exception {
		UDPClient.sendMessage(1313,"SenderA:shello");//calling send message function
	}
	// send message function
	public static String sendMessage(int serverPort,String text) {
		
		DatagramSocket aSocket = null;
        
		try {
           
			aSocket = new DatagramSocket();
            String mess=text;
            
			byte[] message = mess.getBytes();

			InetAddress aHost = InetAddress.getByName("230.1.1.5");

			DatagramPacket request = new DatagramPacket(message, message.length, aHost, serverPort);
			aSocket.send(request);

			System.out.println(" message sent is: "

					+ new String(request.getData()));
		} catch (SocketException e) {

			System.out.println("Socket: " + e.getMessage());

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("IO: " + e.getMessage());

		} finally {

			if (aSocket != null)

				aSocket.close();

		}
		return null;

	}
}


