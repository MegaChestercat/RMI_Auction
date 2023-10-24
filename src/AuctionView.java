import javax.swing.BoxLayout;
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
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;

public class AuctionView {
    private JFrame userWindow;
    private JPanel panel, uPanel, dPanel, lPanel, mPanel, rPanel, productDetPanel, chatPanel;
    private JTextField user;
    private JTextField product;
    private JTextField initialPrice;
    private JTextField amount;
    private JTextField desc;
    private ImageIcon winIMG;
    private JLabel pname, pPrice;
    private JScrollPane msgPanel, listScroll;
    private JTextArea msgArea;
    private JButton connect, exit, publish, retrieve, offer;
    DefaultComboBoxModel<String> productos;
    JList<String> lista;


    public AuctionView(){
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

        JLabel pTitle = new JLabel("Product: ");
        pTitle.setBounds(10, 5, 100, 30);
        productDetPanel.add(pTitle);
        pname = new JLabel("sadasdassadasdassadasdas");
        pname.setBounds(80, 5, 300, 30);
        productDetPanel.add(pname);

        JLabel priTitle = new JLabel("Price: ");
        priTitle.setBounds(10, 30, 100, 30);
        productDetPanel.add(priTitle);
        pPrice = new JLabel("000000.00");
        pPrice.setBounds(80, 30, 300, 30);
        productDetPanel.add(pPrice);
        offer.setBounds(10, 70, 100, 50);
        amount.setBounds(120, 70, 150, 50);
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
        winIMG = new ImageIcon("./images/chapo.png");
        userWindow.setIconImage(winIMG.getImage());
        userWindow.setSize( 700, 500 );
        userWindow.setResizable(false);
	    userWindow.setVisible( true );
        userWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}