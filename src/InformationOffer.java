import java.io.Serializable;

public class InformationOffer implements Serializable{
    String comprador;
    String producto;
    float monto;

    public InformationOffer( String c, String p, float m ) {
        comprador = c;
        producto = p;
        monto = m;
   }
}
