import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class AuctionView{
    private JFrame userWindow;
    private JPanel panel;
    JTextField user;
    JTextField product;
    JTextField initialPrice;
    JTextField amount;


    public AuctionView(){
        JFrame.setDefaultLookAndFeelDecorated( true );

        userWindow = new JFrame("Auction Client Program");

        panel = new JPanel(new GridLayout(0, 3));
        panel.add();



        userWindow.setContentPane(panel);
        userWindow.setSize( 400, 400 );
	    userWindow.setVisible( true );
    }


}