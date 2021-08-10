import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EtchedBorder;

public class PantallaPrincipal extends JFrame implements ActionListener
{
    JTextField txtId, txtDireccion, txtCiudad, txtAncho, txtLargo, txtLargo2;
    JButton btnIngresar, btnModificar;
    DefaultTableModel dtm;
    JTable tabla;
    JComboBox cboTipo, cboSector;
    ListaTerrenos lista;
    JLabel lblLargo2;
    
    public PantallaPrincipal()
    {
        lista = new ListaTerrenos();
        initComponents();
        recargarDatosEnTabla();
    }
    
    public void initComponents()
    {
        setBounds(300, 100, 750, 600);
        setLayout(null);
        
        JPanel formulario = new JPanel();
        formulario.setBounds(10, 220, 690, 290);
        formulario.setLayout(null);
        formulario.setBorder(new EtchedBorder());
        add(formulario);
        
        JLabel lblId = new JLabel("Id:");
        addEtiqueta(lblId, 50, 30, 100, 20, formulario);
        
        txtId = new JTextField("");
        addCajaTexto(txtId, 150, 30, 200, 20, formulario);
        txtId.setEnabled(false);
        
        JLabel lblTipo = new JLabel("Tipo:");
        addEtiqueta(lblTipo, 50, 60, 100, 20, formulario);
        
        cboTipo = new JComboBox();
        cboTipo.setBounds(150, 60, 200, 20);
        cboTipo.addItem("");
        cboTipo.addItem("Rectangular");
        cboTipo.addItem("Triangular");
        cboTipo.addItem("Trapezoidal");
        cboTipo.addActionListener(this);
        formulario.add(cboTipo);
        
        JLabel lblDireccion = new JLabel("Dirección:");
        addEtiqueta(lblDireccion, 50, 90, 100, 20, formulario);
        
        txtDireccion = new JTextField("");
        addCajaTexto(txtDireccion, 150, 90, 200, 20, formulario);
        
        JLabel lblCiudad = new JLabel("Ciudad:");
        addEtiqueta(lblCiudad, 50, 120, 100, 20, formulario);
        
        JLabel lblSector = new JLabel("Sector:");
        addEtiqueta(lblSector, 50, 150, 100, 20, formulario);
        
        cboSector = new JComboBox();
        cboSector.setBounds(150, 150, 200, 20);
        cboSector.addItem("");
        cboSector.addItem("Urbano");
        cboSector.addItem("Rural");
        formulario.add(cboSector);
        
        txtCiudad = new JTextField("");
        addCajaTexto(txtCiudad, 150, 120, 200, 20, formulario);
        
        JLabel lblAncho = new JLabel("Ancho:");
        addEtiqueta(lblAncho, 50, 180, 100, 20, formulario);
        
        txtAncho = new JTextField("0");
        addCajaTexto(txtAncho, 150, 180, 200, 20, formulario);
        
        JLabel lblLargo = new JLabel("Largo:");
        addEtiqueta(lblLargo, 50, 210, 100, 20, formulario);
        
        txtLargo = new JTextField("0");
        addCajaTexto(txtLargo, 150, 210, 200, 20, formulario);
        
        lblLargo2 = new JLabel("Largo 2:");
        lblLargo2.setVisible(false);
        addEtiqueta(lblLargo2, 50, 240, 100, 20, formulario);
        
        txtLargo2 = new JTextField("0");
        txtLargo2.setVisible(false);
        addCajaTexto(txtLargo2, 150, 240, 200, 20, formulario);
        
        btnIngresar = new JButton("Ingresar nuevo terreno");
        addBoton(btnIngresar, 500, 100, 180, 30, formulario);
        
        btnModificar = new JButton("Modificar terreno");
        addBoton(btnModificar, 500, 150, 180, 30, formulario);
        
        JPanel panelTabla = new JPanel(new GridLayout(1,0));
        panelTabla.setBounds(10, 10, 690, 200);
        tabla = new JTable();
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int sel = tabla.getSelectedRow();
                cargarInfoEnFormulario(sel);
            }
        });
        JScrollPane scroll = new JScrollPane(tabla);
        panelTabla.add(scroll);
        add(panelTabla);
        
        setVisible(true);
    }
    
    
    private void recargarDatosEnTabla()
    {
        lista.cargarTerrenosDeBD();
        dtm = new DefaultTableModel(lista.getMatrizTerrenos(), lista.getArrayColumnas());
        tabla.setModel(dtm);
    }
    
    
    public void cargarInfoEnFormulario(int sel)
    {
        Terreno t = lista.getTerreno(sel);
        String tipoCombo = t.getClass().getSimpleName().replace("Terreno", "");
        cboTipo.setSelectedItem(tipoCombo);
        String id = t.getId()+"";
        String direccion = t.getDireccion();
        String ciudad = t.getCiudad();
        String sector = t.getSector();
        double largo = t.getLargo();
        double ancho = t.getAncho();
        txtId.setText(id);
        txtDireccion.setText(direccion);
        txtCiudad.setText(ciudad);
        cboSector.setSelectedItem(sector);
        txtLargo.setText(largo+"");
        txtAncho.setText(ancho+"");
    }
    
    
    private void addBoton(JButton boton, int x, int y, int width, int height, Container contenedor)
    {
        boton.setBounds(x, y, width, height);
        boton.addActionListener(this);
        contenedor.add(boton);
    }
    
    
    private void addEtiqueta(JLabel etiqueta, int x, int y, int width, int height, Container contenedor)
    {
        etiqueta.setBounds(x, y, width, height);
        contenedor.add(etiqueta);
    }
    
    
    private void addCajaTexto(JTextField cajaTexto, int x, int y, int width, int height, Container contenedor)
    {
        cajaTexto.setBounds(x, y, width, height);
        contenedor.add(cajaTexto);
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == cboTipo)
        {
            if (cboTipo.getSelectedItem().equals("Trapezoidal"))
            {
                lblLargo2.setVisible(true);
                txtLargo2.setVisible(true);
                txtLargo2.setText("0");
            }
            else
            {
                lblLargo2.setVisible(false);
                txtLargo2.setVisible(false);
                txtLargo2.setText("0");
            }
        }
        if (e.getSource() == btnIngresar)
        {
            Terreno t = null;
            String direccion = txtDireccion.getText();
            String ciudad = txtCiudad.getText();
            String sector = cboSector.getSelectedItem().toString();
            double ancho = Double.parseDouble(txtAncho.getText());
            double largo = Double.parseDouble(txtLargo.getText());
            double largo2 = Double.parseDouble(txtLargo2.getText());
            if (cboTipo.getSelectedItem().equals("Rectangular"))
            {
                t = new TerrenoRectangular(direccion, ciudad, sector, largo, ancho);
            }
            else if (cboTipo.getSelectedItem().equals("Triangular"))
            {
                t = new TerrenoTriangular(direccion, ciudad, sector, largo, ancho);
            }
            else if (cboTipo.getSelectedItem().equals("Trapezoidal"))
            {
                t = new TerrenoTrapezoidal(direccion, ciudad, sector, largo, ancho, largo2);
            }
            System.out.println(t);
            lista.guardarTerrenoEnBD(t);
            recargarDatosEnTabla();
        }
        
    }
}
