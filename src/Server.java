import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Server implements Producer{
    private static List<Consumer> list = new LinkedList<Consumer>();
    public Server(){}

    public void registro(Consumer o, String u, AuctionView v){
        list.add(o);
        for(Consumer e: list){
            try{
                e.update("The user " + u + "has just connected.", v);
            }catch(Exception ex){
                list.remove(e);
                JOptionPane.showMessageDialog(v.userWindow, "Client exception: " + e.toString(), "Erase Client", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Vector obtieneCatalogo(){

    }
    public static void main(String[] args){
        try{
            Server obj = new Server();
	        Producer stub = (Producer) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
	        registry.bind("Producer", stub);
        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
