import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AuctionController implements ActionListener, ListSelectionListener{
    AuctionView vista;
    Producer modelo;
    Consumer client;
    Hashtable<String, InformationProduct> listaConPrecios;
    String productN;
    String item;

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

            try{
                modelo.agregaProductoALaVenta(user, product, desc, price);
                vista.showMsg("Offering the product: " + product);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(vista.userWindow, "Client exception: " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(event.getActionCommand().equals("Retrieve List")){
            try{
                Vector<?> lista = modelo.obtieneCatalogo();
                Enumeration<?> it;
                InformationProduct info;
                listaConPrecios = modelo.getProductList();
                vista.reinicializaListaProductos();
                it = lista.elements();
                while (it.hasMoreElements()) {
                    info = (InformationProduct) it.nextElement();    
                    /*        
                    listaConPrecios.put( info.producto,
                                         info);*/
                    vista.agregaProducto( info.producto );
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(vista.userWindow, "Exception: " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(event.getActionCommand().equals("Make Offer")){
            user = vista.getUsuario();
            product = productN;
            price = vista.getMontoOfrecido();
            try{
                modelo.agregaOferta(user, product, price);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(vista.userWindow, "Exception: " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(event.getSource() == vista.refreshBtn){
            try{
                listaConPrecios = modelo.getProductList();
                productN = listaConPrecios.get(item).producto;
                String descr = listaConPrecios.get(item).description;
                String precio = String.valueOf(listaConPrecios.get(item).precioActual);
                vista.desplegarNombre(productN);
                vista.desplegarDescripcion(descr);
                vista.desplegarPrecio( precio );
            }catch(Exception ex){
                JOptionPane.showMessageDialog(vista.userWindow, "Exception: " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void valueChanged(ListSelectionEvent e){
        if(e.getValueIsAdjusting() == false){
            JList<?> lista = (JList<?>)e.getSource();
            item = (String)lista.getSelectedValue();
            if (item != null) {
                /*
                try{
                    Hashtable<String, InformationProduct> productList = modelo.getProductList();
                    String productN = productList.get(item).producto;
                    String desc = productList.get(item).description;
                    String precio = Float.toString(productList.get(item).precioActual);
                    vista.desplegarNombre(productN);
                    vista.desplegarDescripcion(desc);
                    vista.desplegarPrecio( precio );
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(vista.userWindow, "Exception: " + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }*/
	            //System.out.println(item);
                
                productN = listaConPrecios.get(item).producto;
                String desc = listaConPrecios.get(item).description;
                String precio = String.valueOf(listaConPrecios.get(item).precioActual);
                vista.desplegarNombre(productN);
                vista.desplegarDescripcion(desc);
                vista.desplegarPrecio( precio );
            }
        }
    }
}
