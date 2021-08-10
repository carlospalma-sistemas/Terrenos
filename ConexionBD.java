import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConexionBD
{
    private Connection con;
    private Statement ejec;
    private String conectorJDBC;
    private String rutaBaseDatos;
    
    
    public ConexionBD()
    {
        conectorJDBC = "jdbc:sqlite:";
        rutaBaseDatos = "data\\BDTerrenos.db";
    }
    
    
    public void conectar()
    {
        String cadenaConexion = conectorJDBC+rutaBaseDatos;
        try
        {
            con = DriverManager.getConnection(cadenaConexion);
            ejec = con.createStatement();
            ejec.setQueryTimeout(30);
            System.out.println("Conexión creada: "+con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public ResultSet ejecutarQuery(String sql)
    {
        ResultSet rs = null;
        try
        {
            System.out.println("Consulta: "+sql);
            rs = ejec.executeQuery(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;    
    }
    
    
    public ResultSet ejecutarUpdate(String sql)
    {
        ResultSet rs = null;
        try
        {
            int cant = ejec.executeUpdate(sql);
            if (cant > 0) {
                rs = ejec.getGeneratedKeys();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;   
    }
    
    
    public void desconectar()
    {
        try {
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }    
    }
}
