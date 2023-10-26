import java.io.Serializable;

public class InformationOffer implements Serializable{
    String comprador;
    String producto;
    String offerDateTime;
    float monto;

    public InformationOffer( String c, String p, float m , String d) {
        comprador = c;
        producto = p;
        monto = m;
        offerDateTime = d;
   }
}
