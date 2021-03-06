import java.text.DecimalFormat;

public class TerrenoRectangular extends Terreno
{
    public TerrenoRectangular(double largo, double ancho, String sector)
    {
        super(largo, ancho, sector);
    }
    
    
    public TerrenoRectangular(String direccion, String ciudad, String sector, double largo, double ancho)
    {
        super(direccion, ciudad, sector, largo, ancho);
    }
    
    public TerrenoRectangular(int id, String direccion, String ciudad, String sector, double largo, double ancho)
    {
        super(direccion, ciudad, sector, largo, ancho);
        this.id = id;
    }
    
    
    public double getArea()
    {
        double area = largo * ancho;
        return area;
    }
    
    
    public String toString()
    {
        DecimalFormat formato = new DecimalFormat("#,###.00");
        String mensaje = "Terreno rectangular" + "\n" +
                         "Área: " + getArea() + " m2" + "\n" +
                         "Valor: $" + formato.format(getPrecio()) +"\n";
        return mensaje;
    }
}
