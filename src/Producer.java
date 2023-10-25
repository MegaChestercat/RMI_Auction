import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

public interface Producer extends Remote {
    void registro(Consumer o, String u) throws RemoteException;
    void baja(Consumer o, String u) throws RemoteException;
    Vector<InformationProduct> obtieneCatalogo()throws RemoteException;
    void agregaProductoALaVenta(String user, String product, String desc, float price) throws RemoteException;
    void agregaOferta( String comprador, String producto, float monto ) throws RemoteException;
    Hashtable<String, InformationProduct> getProductList() throws RemoteException;
}
