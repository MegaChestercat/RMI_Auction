import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


public class Server implements Producer{
    Hashtable<String, Consumer> usuarios;
    Hashtable<String, InformationProduct> productos;
    Hashtable<String, InformationOffer> ofertas;
    public Server(){
        usuarios = new Hashtable<String, Consumer>();
        productos = new Hashtable<String, InformationProduct>();
        ofertas = new Hashtable<String, InformationOffer>();
    }

    public void registro(Consumer o, String u){
        usuarios.put(u, o);
        Enumeration<String> it = usuarios.keys();
        while(it.hasMoreElements()){
            String key = it.nextElement();
            try{
                usuarios.get(key).update("The user " + u + " has just connected.");
            }catch(Exception ex){
                usuarios.remove(u, o);
                System.out.println("Client exception: " + ex.toString());
            }
        }
    }

    public void baja(Consumer o, String u){
        usuarios.remove(u, o);
        Enumeration<String> it = usuarios.keys();
        while(it.hasMoreElements()){
            String key = it.nextElement();
            try{
            usuarios.get(key).update("The user " + u + " has disconnected.");
           }catch(Exception ex){
             usuarios.remove(u, o);
            System.out.println("Client exception: " + ex.toString());
           }
        }
    }

    public void agregaProductoALaVenta(String user, String product, String desc, float price){
        if(!productos.containsKey(product)){
            productos.put(product, new InformationProduct(user, product, desc, price));
            Enumeration<String> it = usuarios.keys();
            while(it.hasMoreElements()){
                String key = it.nextElement();
                try{
                    usuarios.get(key).update("The user " + user + " has published a new product in auction: " + product);
                }catch(Exception ex){
                    System.out.println("Client exception: " + ex.toString());
                }
            }
        }
    }

    public void agregaOferta( String comprador, String producto, float monto ) {
        if(productos.containsKey(producto)){
            InformationProduct infoProduct;
            infoProduct = (InformationProduct) productos.get(producto);
            if(infoProduct.actualizaPrecio(monto)){
                ofertas.put(producto + comprador, new InformationOffer(comprador, producto, monto));
            }
            else{
                try{
                    usuarios.get(comprador).alert("La cantidad a ofrecer es menor a la cantidad actual del producto en la subasta");
                }catch(Exception ex){
                    System.out.println("Client exception: " + ex.toString());
                }
                
            }
        }
    }

    public Vector<InformationProduct> obtieneCatalogo(){
        Vector<InformationProduct> result = new Vector<InformationProduct>(productos.values());
        return result;
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
