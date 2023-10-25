import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AuctionController implements ActionListener, ListSelectionListener{
    AuctionView vista;
    Producer modelo;
    Consumer client;
    Hashtable<String, String> listaConPrecios;

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

        if(event.getActionCommand().equals("Exit") || event.getActionCommand().equals(new WindowEvent(vista.userWindow, WindowEvent.WINDOW_CLOSING))){
            user = vista.getUsuario();
            try{
                modelo.baja(client, user);
                System.exit(1);
            }catch(Exception e){
                JOptionPane.showMessageDialog(vista.userWindow, "Client exception: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else if(event.getActionCommand().equals("Connect")){
            user = vista.getUsuario();
            try{
                modelo.registro(client, user);
            }catch(Exception e){
                JOptionPane.showMessageDialog(vista.userWindow, "Client exception: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else if(event.getActionCommand().equals("Publish")){
            user = vista.getUsuario();
            product = vista.getProducto();
            desc = vista.getDescription();
            price = vista.getPrecioInicial();

            vista.showMsg("Offering the product: " + product);

        }
        else if(event.getActionCommand().equals("Retrieve List")){
            try{
                Vector lista = modelo.obtieneCatalogo();
                Enumeration it;
                InformationProduct info;
                listaConPrecios = new Hashtable<String,String>();
                vista.reinicializaListaProductos();
                it = lista.elements();
                while (it.hasMoreElements()) {
                    info = (InformationProduct) it.nextElement();            
                    listaConPrecios.put( info.producto,
                                        String.valueOf(info.precioActual) );
                    vista.agregaProducto( info.producto );
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(vista.userWindow, "Exception: " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(event.getActionCommand().equals("Make Offer")){
            
        }
    }

    public void valueChanged(ListSelectionEvent e){
        if(e.getValueIsAdjusting() == false){
            JList<?> lista = (JList<?>)e.getSource();
            String item = (String)lista.getSelectedValue();
            if (item != null) {
	        System.out.println(item);
                String precio = (String)listaConPrecios.get(item);
                vista.desplegarPrecio( precio );
            }
        }
    }
}
