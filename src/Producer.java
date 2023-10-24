import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Producer extends Remote {
    void registro(Consumer o, String u, AuctionView v) throws RemoteException;
    Vector obtieneCatalogo()throws RemoteException;
}
