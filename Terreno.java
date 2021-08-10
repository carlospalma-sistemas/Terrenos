public abstract class Terreno implements ContratoTerreno
{
    protected int id;
    protected String direccion;
    protected String ciudad;
    protected String sector;
    protected double largo;
    protected double ancho;
    
    
    public Terreno()
    {
        this.largo = 0;
        this.ancho = 0;
        this.sector = "";
    }
    
    
    public Terreno(double largo, double ancho, String sector)
    {
        this.largo = largo;
        this.ancho = ancho;
        this.sector = sector;
    }
    
    
    public Terreno(String direccion, String ciudad, String sector, double largo, double ancho)
    {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.sector = sector;
        this.largo = largo;
        this.ancho = ancho;
    }
    
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    
    public int getId()
    {
        return this.id;
    }
    
    
    public String getDireccion()
    {
        return this.direccion;
    }
    
    
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
    
    
    public String getCiudad()
    {
        return this.ciudad;
    }
    
    
    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }
    
    
    public void setSector(String sector)
    {
        this.sector = sector;
    }
    
    
    public String getSector()
    {
        return this.sector;
    }
    
    
    public void setLargo(double largo)
    {
        this.largo = largo;
    }
    
    
    public double getLargo()
    {
        return this.largo;
    }
    
    
    public void setAncho(double ancho)
    {
        this.ancho = ancho;
    }
    
    
    public double getAncho()
    {
        return this.ancho;
    }
    
    
    public abstract double getArea();
    
    
    public double getValorXm2()
    {
        double valorXm2 = sector.toLowerCase().equals("urbano") ? 3000000 : 1800000;
        return valorXm2;
    }
    
    
    public double getPrecio()
    {
        double precio = getValorXm2() * getArea();
        return precio;
    }
    
    
    public abstract String toString();
}
