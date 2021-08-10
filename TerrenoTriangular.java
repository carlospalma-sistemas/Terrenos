import java.text.DecimalFormat;

public class TerrenoTriangular extends Terreno
{
    public TerrenoTriangular(double largo, double ancho, String sector)
    {
        super(largo, ancho, sector);
    }
    
    
    public TerrenoTriangular(String direccion, String ciudad, String sector, double largo, double ancho)
    {
        super(direccion, ciudad, sector, largo, ancho);
    }
    
    
    public TerrenoTriangular(int id, String direccion, String ciudad, String sector, double largo, double ancho)
    {
        super(direccion, ciudad, sector, largo, ancho);
        this.id = id;
    }
    
    
    /**
     * Calcular el área del terreno triangular
     */
    public double getArea()
    {
        double area = largo * ancho / 2;
        return area;
    }
    
    
    /**
     * Genera información del terreno triangular
     */
    public String toString()
    {
        DecimalFormat formato = new DecimalFormat("#,###.00");
        String mensaje = "Terreno triangular" + "\n" +
                         "Área: " + getArea() + " m2" + "\n" +
                         "Valor: $" + formato.format(getPrecio()) +"\n";
        return mensaje;
    }
}