import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Consumer extends Remote {
    void update(String mensaje, AuctionView gui) throws RemoteException;
}
