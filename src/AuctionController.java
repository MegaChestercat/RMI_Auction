import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AuctionController implements ActionListener, ListSelectionListener{
    AuctionView vista;
    Producer modelo;
    Consumer client;
    Hashtable listaConPrecios;

    public AuctionController( AuctionView v, Producer s, Consumer c ) {
        vista = v;
        modelo = s;
        client = c;
    }

    public void actionPerformed(ActionEvent event){

        String user;
        String product;
        String desc;
        float price;

        if(event.getActionCommand().equals("Exit")){
            System.exit(1);
        }
        else if(event.getActionCommand().equals("Connect")){
            user = vista.getUsuario();
            try{
                modelo.registro(client, user, vista);
            }catch(Exception e){
                JOptionPane.showMessageDialog(vista.userWindow, "Client exception: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else if(event.getActionCommand().equals("Publish")){
            user = vista.getUsuario();
            product = vista.getProducto();
            desc = vista.getDescription();
            price = vista.getPrecioInicial();

        }
        else if(event.getActionCommand().equals("Retrieve List")){
            Vector lista = modelo.obtieneCatalogo();
            Enumeration it;
            InformacionProducto info;
            listaConPrecios = new Hashtable();
            vista.reinicializaListaProductos();
            it = lista.elements();
            while (it.hasMoreElements()) {
                info = (InformacionProducto) it.nextElement();            
                listaConPrecios.put( info.producto,
                                     String.valueOf(info.precioActual) );
                vista.agregaProducto( info.producto );
            }
        }
        else if(event.getActionCommand().equals("Make Offer")){

        }
    }

    public void valueChanged(ListSelectionEvent e){
        if(e.getValueIsAdjusting() == false){

        }
    }
}
