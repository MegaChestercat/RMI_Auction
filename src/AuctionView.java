import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatIntelliJLaf; //It is important to reference/add the flatlaf library that is located in the lib folder

public class AuctionView{
    JFrame userWindow;
    private JPanel panel, uPanel, dPanel, lPanel, mPanel, rPanel, productDetPanel, chatPanel;
    private JTextField user;
    private JTextField product;
    private JTextField initialPrice;
    private JTextField amount;
    private JTextField desc;
    private ImageIcon winIMG;
    private JLabel pname, pPrice, descVal;
    private JScrollPane msgPanel, listScroll;
    private JTextArea msgArea;
    private JButton connect, exit, publish, retrieve, offer;
    JButton refreshBtn;
    DefaultComboBoxModel<String> productos;
    JList<String> lista;


    public AuctionView() throws UnsupportedLookAndFeelException{
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        UIManager.put("TextComponent.arc", 40);
        UIManager.put("Button.arc", 60);
        JFrame.setDefaultLookAndFeelDecorated( true );

        userWindow = new JFrame("Auction Client Program");

        productos = new DefaultComboBoxModel<String>();
	    lista = new JList<String>(productos); //data has type Object[]

        panel = new JPanel(new GridLayout(3, 1));
        uPanel = new JPanel(new GridLayout(0, 2));
        mPanel = new JPanel(null);
        dPanel = new JPanel(new GridLayout(0, 2));
        
        productDetPanel = new JPanel(null);
        chatPanel = new JPanel(null);
        msgArea = new JTextArea();
        msgArea.setEditable(true);
        msgPanel = new JScrollPane(msgArea);

        lPanel = new JPanel(null);
        rPanel = new JPanel(null);
        user = new JTextField();
        
        panel.add(uPanel);
        panel.add(mPanel);
        panel.add(dPanel);

        uPanel.add(lPanel);

        JLabel us = new JLabel("Username:");
        us.setBounds(20, 25, 100, 30);
        lPanel.add(us);
        user = new JTextField();
        user.setBounds(120, 25, 160, 30);
        lPanel.add(user);

        connect = new JButton("Connect");
        connect.setBounds(35, 80, 100, 30);

        exit = new JButton("Exit");
        exit.setBounds(155, 80, 100, 30);

        publish = new JButton("Publish");

        retrieve = new JButton("Retrieve List");
        retrieve.setBounds(60, 50, 200, 60);
        offer = new JButton("Make Offer");
        amount = new JTextField();

        lPanel.add(connect);
        lPanel.add(exit);

        mPanel.add(retrieve);
        lista.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
	    lista.setLayoutOrientation( JList.VERTICAL );
        listScroll = new JScrollPane(lista);
        listScroll.setBounds(330, 40, 300, 80);
        mPanel.add(listScroll);

        dPanel.add(productDetPanel);
        dPanel.add(chatPanel);

        uPanel.add(rPanel);

        JLabel notTitle = new JLabel("Application Notifications:");
        notTitle.setBounds(10, 5, 150, 30);
        msgPanel.setBounds(10, 35, 310, 110);
        chatPanel.add(notTitle);
        chatPanel.add(msgPanel);

        refreshBtn = new JButton("");
        refreshBtn.setIcon(new ImageIcon("src\\images\\refresh.png"));
        refreshBtn.setBorder(BorderFactory.createEmptyBorder());
        refreshBtn.setContentAreaFilled(false);
        refreshBtn.setFocusable(false);
        refreshBtn.setBounds(240, 5, 24, 24);
        productDetPanel.add(refreshBtn);

        JLabel pTitle = new JLabel("Product: ");
        pTitle.setBounds(10, 5, 100, 30);
        productDetPanel.add(pTitle);
        pname = new JLabel("");
        pname.setBounds(80, 5, 300, 30);
        productDetPanel.add(pname);

        JLabel priTitle = new JLabel("Price: ");
        priTitle.setBounds(10, 30, 100, 30);
        productDetPanel.add(priTitle);
        pPrice = new JLabel("000000.00");
        pPrice.setBounds(80, 30, 300, 30);
        productDetPanel.add(pPrice);
        JLabel descTitle = new JLabel("Description: ");
        descTitle.setBounds(10, 60, 100, 30);
        descVal = new JLabel("");
        descVal.setBounds(80, 60, 300, 30);
        productDetPanel.add(descTitle);
        productDetPanel.add(descVal);
        offer.setBounds(10, 100, 100, 50);
        amount.setBounds(120, 100, 150, 50);
        productDetPanel.add(offer);
        productDetPanel.add(amount);

        JLabel a = new JLabel("Product to offer:");
        JLabel b = new JLabel("Initial Price:");
        a.setBounds(50, 15, 100, 30);
        rPanel.add(a);
        product = new JTextField();
        product.setBounds(170, 20, 160, 20);
        rPanel.add(product);
        b.setBounds(50, 50, 100, 30);
        rPanel.add(b);
        initialPrice = new JTextField();
        initialPrice.setBounds(170, 55, 160, 20);
        rPanel.add(initialPrice);
        JLabel c = new JLabel("Description:");
        c.setBounds(50, 80, 100, 30);
        rPanel.add(c);
        desc = new JTextField();
        desc.setBounds(170, 85, 160, 20);
        rPanel.add(desc);
        publish.setBounds(150, 120, 100, 30);
        rPanel.add(publish);


        userWindow.setContentPane(panel);
        winIMG = new ImageIcon("src\\images\\icon.png");
        userWindow.setIconImage(winIMG.getImage());
        userWindow.setSize( 700, 500 );
        userWindow.setResizable(false);
	    userWindow.setVisible( true );
        userWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void asignarActionListener(ActionListener controlador) {

        connect.addActionListener( controlador );
        exit.addActionListener( controlador );
        publish.addActionListener( controlador );
        retrieve.addActionListener( controlador );
        offer.addActionListener( controlador );
        refreshBtn.addActionListener(controlador);
    }

    public void asignarListSelectionListener( ListSelectionListener controlador ) {

        lista.addListSelectionListener( controlador );
    }

    public String getUsuario() {

        return user.getText();
    }

    public String getProducto() {

        return product.getText();
    }

    public String getDescription() {

        return desc.getText();
    }

    public float getPrecioInicial() {

        float resultado = 0.0f;

        try {

            resultado = Float.parseFloat( initialPrice.getText() );

        } catch( Exception e ) {

            System.out.println( "There are issues with the initial price." );
        }

        return resultado;
    }

    public void reinicializaListaProductos() {

        productos.removeAllElements();
    }

    public void agregaProducto( String prod ) {

        productos.addElement( prod );
    }

    public void desplegarNombre( String nombre ) {

        pname.setText( nombre );
    }

    public void showMsg(String msg){
        msgArea.append(msg + "\n");
    }

    public void desplegarPrecio( String precio ) {

        pPrice.setText( precio );
    }

    public void desplegarDescripcion( String desc ) {

        descVal.setText( desc );
    }

    public float getMontoOfrecido() {

        float resultado = 0.0f;

        try {

            resultado = Float.parseFloat( amount.getText() );

        } catch( Exception e ) {

            System.out.println( "There are issues with the amount offered." );
        }

        return resultado;
    }

    public String getProductoSeleccionado() {

        return (String)lista.getSelectedValue();
    }

}