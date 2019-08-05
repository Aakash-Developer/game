package gameengine;

import api.Constant.GameType;
import api.Constant.Turn;
import gameui.GridMap;
import gameui.PlayerView;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ship;
import utils.PrettyPrint;
import utils.Tuple;

/**
* The Controller class in the controller in the MVC architecture
* of the application. It is responsible of coordinating the view 
* and model
*/
public class Controller
{ 
    GameType gameType;
    public PlayerView mainWindowView;
    public boolean computerTurn = false;
    public final OponentMap oponent;
    public final PlayerMap player;
    public GridMap oponentView, playerView;
    public Turn turn;
    public ComputerPlayer ai;
    public int udp_port; // 1111 for player1, 1112 for player2
    public int udp_receieve_port; // 1113 for player1, 1114 for player2
    public BlockingQueue<String> oponentMoveQueue;
    public String oponentShipDeployInfo="";
    public String selfShipDeployInfo="ship";
    /**
    * Initializes one controller per game
    */
    public Controller(){
   
        
            this.turn = Turn.None;
            
            this.player = new PlayerMap(this);
            this.playerView = player.mapView;
            
            this.oponent = new OponentMap(this);
            this.oponentView = oponent.mapView;
            
            //this.oponent.placeShipsRandomly();
            this.ai = new ComputerPlayer(this.player.mapModel,this);
            this.oponentMoveQueue = new LinkedBlockingQueue<>();
            CreateView();
    }
     
    /**
     * initializes the JavaFX framework with the
     * view of the game
     */
    private void CreateView(){        
        
        this.mainWindowView  = new PlayerView(this);
    }
    
    public void udpSendMsg(String message) {
            DatagramSocket aSocket = null;
            try {
                    aSocket = new DatagramSocket();
                    byte[] m = message.getBytes();
                    //InetAddress aHost = InetAddress.getByName("230.1.1.5");
                    InetAddress aHost = InetAddress.getByName("localhost");
                    DatagramPacket request = new DatagramPacket(m, m.length, aHost, udp_port);
                    aSocket.send(request);
                    
                    byte[] buffer_receieve = new byte[1000];
                    DatagramPacket replyMsg = new DatagramPacket(buffer_receieve, buffer_receieve.length);
                    mainWindowView.displayMessage(5);
                    aSocket.receive(replyMsg);
                    mainWindowView.displayMessage(3);
                    String repliedString = new String(replyMsg.getData(), "UTF-8");
                    //System.out.println("Reply from server: "+repliedString);
                    if(repliedString.charAt(0)=='s'){
                        oponentShipDeployInfo = repliedString;
                        String receievedStr = oponentShipDeployInfo.substring(5);
                        String[] p2ShipInfo = receievedStr.split("-");
                        // computer's turn of placing ships
                        System.out.println("GetPlayer2ShipPlaceInfo()...");
                        int type = 5;
                        while (type > 0) {

                            System.out.println("receieved ship: "+p2ShipInfo[5-type]);
                            Tuple possibleCoordinate = new Tuple(Integer.parseInt(p2ShipInfo[5-type].split(" ")[0]),Integer.parseInt(p2ShipInfo[5-type].split(" ")[1]));
                            boolean shipVertical = p2ShipInfo[5-type].split(" ")[2].trim().equals("true");
                            Ship ship = new Ship(oponent.ShipsSizeOrderedList[type-1] , shipVertical );
                            boolean shipPlacement = oponentView.TryToPlaceShipOnModel(ship, possibleCoordinate.t1, possibleCoordinate.t2);
                            boolean shipPlacementOnMap = oponentView.TryToPlaceShipOnMap(ship, possibleCoordinate.t1, possibleCoordinate.t2);
                            System.out.println("last ship placement: "+shipPlacement);
                            System.out.println("last ship placement on Map: "+shipPlacementOnMap);
                            System.out.println("ship.length: "+ship.length);
                            System.out.println("possibleCoordinate.t1: "+possibleCoordinate.t1);
                            System.out.println("possibleCoordinate.t2: "+possibleCoordinate.t2);
                            if (shipPlacementOnMap) {                        
                                
                                //try {
                                //    player2ShipDeployQueue.put(p2ShipInfo[5-type]);
                                //    System.out.println("Putting receieved ship: "+(5-type+1));
                                //} catch (InterruptedException ex) {
                                //    Logger.getLogger(NetworkingHub.class.getName()).log(Level.SEVERE, null, ex);
                                //}
                                
                                type--;
                            }else{ 

                                System.exit(0);}

                        }
                        PrettyPrint.shipsInModel("Oponent", oponent.mapModel);        
                        oponent.mapView.applyModelToView();
                        oponent.mapView.finishIni = true;
                    
                    }else{
                        try {
                            oponentMoveQueue.put(repliedString);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(this.udp_port+": receievedP2Move: "+ repliedString);
                    }
                    
            } catch (IOException e) {
                    e.printStackTrace();
            }

    }
}
