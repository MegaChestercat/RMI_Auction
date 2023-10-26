import java.io.Serializable;

public class InformationProduct implements Serializable{
    String vendedor;
    String producto;
    String description;
    String dueDate;
    float precioInicial;
    float precioActual;

    public InformationProduct( String v, String p, String desc, float price, String dateT ) {

        vendedor = v;
        producto = p;
        description = desc;
        precioInicial = price;
        precioActual = price;
        dueDate = dateT;
    }

    public boolean actualizaPrecio( float monto ) {

        if( monto > precioActual ) {
            precioActual = monto;
            return true;
        } else
            return false;
    }

    public String getNombreProducto() {

        return producto;
    }

    public float getPrecioActual() {

        return precioActual;
    }

    public String getDescription() {

        return description;
    }

    public String getDueDate() {

        return dueDate;
    }
}
