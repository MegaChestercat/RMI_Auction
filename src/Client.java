import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class Client implements Consumer{
    private Client(){}
    static AuctionView view;
    public void update(String mensaje){
		view.showMsg(mensaje);
	}

    public void alert(String mensaje){
        JOptionPane.showMessageDialog(view.userWindow, mensaje, "Warning", JOptionPane.INFORMATION_MESSAGE );
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException{

        String host = (args.length < 1) ? null : args[0];
        AuctionController controller;
        view = new AuctionView();
        try{
            Client obj = new Client();
	        Consumer client_stub = (Consumer) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry(host);
	        Producer stub = (Producer) registry.lookup("Producer");
            controller = new AuctionController(view, stub, client_stub);
            view.asignarActionListener(controller);
            view.asignarListSelectionListener(controller);

        } catch(Exception e){
            JOptionPane.showMessageDialog(view.userWindow, "Client exception: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
            System.exit(1);
        }
    }
}
