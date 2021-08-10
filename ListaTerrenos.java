import java.util.ArrayList;
import java.text.DecimalFormat;

public class ListaTerrenos
{
    private ArrayList<Terreno> lista = new ArrayList<Terreno>();
    
    
    public ArrayList<Terreno> getLista()
    {
        return lista;
    }
    
    
    public Object[][] getMatrizTerrenos()
    {
        Object [][] datos = new Object [lista.size()][getArrayColumnas().length];
        for (int i=0; i<lista.size(); i++)
        {
            datos[i] = getTerrenoComoArray(i);
        }
        return datos;
    }
    
    
    private void setLista(ArrayList<Terreno> lista)
    {
        this.lista = lista;
    }
    
    
    public Terreno getTerreno(int index)
    {
        return lista.get(index);
    }
    
    
    public String [] getArrayColumnas()
    {
        String [] columnas = {"Id", "Tipo", "Dirección", "Ciudad", "Area", "Precio"};
        return columnas;
    }
    
    
    public Object[] getTerrenoComoArray(int index)
    {
        DecimalFormat formateador = new DecimalFormat("$ #,###.##");
        Terreno t = lista.get(index);
        String tipo = t.getClass().getSimpleName();
        Object[] terrenoArray = {t.getId(), tipo, t.getDireccion(), t.getCiudad(), t.getArea(), formateador.format(t.getPrecio())};
        return terrenoArray;
    }
    
    
    private void setTerreno(int index, Terreno terreno)
    {
        this.lista.set(index, terreno);
    }
    
    
    private void addTerreno(Terreno terreno)
    {
        this.lista.add(terreno);
    }
    
    
    public void cargarTerrenosDeBD()
    {
        DAOTerrenos dao = new DAOTerrenos();
        if (dao.consultarTerrenos()) 
        {
            setLista(dao.getListaConsultada());    
        }
    }
    
    
    public void guardarTerrenoEnBD(Terreno t)
    {
        DAOTerrenos dao = new DAOTerrenos();
        if (dao.insertarTerreno(t)) 
        {
            t.setId(dao.getIdInsertado());
            addTerreno(t);    
        }
    }
}
