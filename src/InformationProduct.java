public class InformationProduct {
    String vendedor;
    String producto;
    String description;
    float precioInicial;
    float precioActual;

    public InformationProduct( String v, String p, String desc, float price ) {

        vendedor = v;
        producto = p;
        description = desc;
        precioInicial = price;
        precioActual = price;
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
}
