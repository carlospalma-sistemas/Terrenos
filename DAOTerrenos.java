import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOTerrenos
{
    private int idInsertado = 0;
    private ArrayList<Terreno> listaConsultada;
    
    public int getIdInsertado()
    {
        return this.idInsertado;
    }
    
    
    public ArrayList<Terreno> getListaConsultada()
    {
        return this.listaConsultada;
    }
    
    
    public boolean insertarTerreno(Terreno t)
    {
        String tipo = t.getClass().getSimpleName();
        double largo2 = tipo.toLowerCase().equals("terrenotrapezoidal") ? ((TerrenoTrapezoidal)t).getLargo2() : 0;
        String sql = "INSERT INTO TTerrenos (direccion, ciudad, sector, tipo, largo, ancho, largoadicional, area, precio) " + 
                     "VALUES ('"+ t.getDireccion() + "', '" + t.getCiudad() + "', '" + t.getSector() + "', '" + tipo + "', " +
                     t.getLargo() + ", " + t.getAncho() + ", " + largo2 + ", " + t.getArea() + ", " + t.getPrecio() +")";
        
        ConexionBD con = new ConexionBD();
        con.conectar();
        try
        {
            ResultSet rs = con.ejecutarUpdate(sql);
            idInsertado = rs.next() ? rs.getInt(1) : 0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        con.desconectar();
        return true;
    }
    
    
    public boolean consultarTerrenos()
    {
        listaConsultada = new ArrayList<Terreno>();
        String sql = "SELECT id, direccion, ciudad, sector, tipo, largo, ancho, largoadicional, area, precio "+
                     "FROM TTerrenos";
        ConexionBD con = new ConexionBD();
        con.conectar();
        try
        {
            ResultSet rs = con.ejecutarQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String sector = rs.getString("sector");
                String tipo = rs.getString("tipo");
                double largo = rs.getDouble("largo");
                double ancho = rs.getDouble("ancho");
                double largo2 = rs.getDouble("largoadicional");
                Terreno t = null;
                if (tipo.toLowerCase().equals("terrenorectangular"))
                {
                    t = new TerrenoRectangular(id, direccion, ciudad, sector, largo, ancho);
                }
                else if (tipo.toLowerCase().equals("terrenotriangular"))
                {
                    t = new TerrenoTriangular(id, direccion, ciudad, sector, largo, ancho);
                }
                else if (tipo.toLowerCase().equals("terrenotrapezoidal"))
                {
                    t = new TerrenoTrapezoidal(id, direccion, ciudad, sector, largo, ancho, largo2);
                }
                listaConsultada.add(t);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        con.desconectar();
        return true;
    }
}
 