import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Producer extends Remote {
    void registro(Consumer o, String u) throws RemoteException;
    void baja(Consumer o, String u) throws RemoteException;
    Vector<InformationProduct> obtieneCatalogo()throws RemoteException;
}
