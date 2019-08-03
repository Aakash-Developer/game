import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class UDPServer {
	
	// receiver to receive the message which will always be active 
	public static void receive() {
		
		
		MulticastSocket aSocket = null;

			try {
				
		    	

				aSocket = new MulticastSocket(1313);

				aSocket.joinGroup(InetAddress.getByName("230.1.1.5"));

				System.out.println(" Started............");
				
				while (true) {
					
					byte[] buffer = new byte[1000];

					DatagramPacket request = new DatagramPacket(buffer, buffer.length);

					aSocket.receive(request);
					
					String stringdata=new String (request.getData());
					System.out.println(stringdata);
			
					DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(),

							request.getPort());

					aSocket.send(reply);
					
		
	}
			

	} catch (SocketException e) {
		System.out.println("Socket: " + e.getMessage());

	} catch (IOException e) {

		System.out.println("IO: " + e.getMessage());

	} finally {

		if (aSocket != null)
			System.out.println("xyz");
			aSocket.close();

	}
		//	return null;

	}
	public static void startReceiver() {

		try{
	    	
			
			Runnable task = () -> {

				receive();

			};
		
			Thread thread = new Thread(task);
			
			thread.start();
		
	}
		catch (Exception re) {
	    System.out.println("Exception : " + re);
	   // logger.info("Exception in ServerComp: " + re);
		} 
		}
	public static void main(String... s) {
		UDPServer.startReceiver();
	}
	}