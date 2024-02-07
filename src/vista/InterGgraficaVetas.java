package vista;

import conexion.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brayan
 */
public class InterGgraficaVetas extends javax.swing.JInternalFrame {

    ArrayList<Integer> listaCantidad = new ArrayList<>();
    ArrayList<String> listaFecha = new ArrayList<>();

    int cantidadResultados = 0;
    String[] vector_fechaVenta;
    int[] vector_estatus_cantidad;

    public InterGgraficaVetas() {
        initComponents();
        this.setSize(new Dimension(550, 650));
        this.setTitle("Historial de Ventas");
        this.MetodoContador();
        vector_fechaVenta = new String[cantidadResultados];
        vector_estatus_cantidad = new int[cantidadResultados];
        this.MetodoAlmacenaDatos();

    }

    //metodo para determinar la cantidad de resultados a graficar 
    private int MetodoContador() {

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select fechaVenta, count(fechaVenta) as Venta from tb_cabecera_venta where fechaVenta BETWEEN '" + InterGraficas.fecha_inicio + "' and  '" + InterGraficas.fecha_fin + "' group by fechaVenta;");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cantidadResultados++;

            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error en:" + e);
        }
        return cantidadResultados;
    }

    //metodopara almacenar en las listas los datos a graficar 
    private void MetodoAlmacenaDatos() {

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select fechaVenta, count(fechaVenta) as Venta from tb_cabecera_venta where fechaVenta BETWEEN '" + InterGraficas.fecha_inicio + "' and  '" + InterGraficas.fecha_fin + "' group by fechaVenta;");
            ResultSet rs = pst.executeQuery();
            int contador = 0;
            while(rs.next()){
              vector_fechaVenta[contador] = rs.getString("fechaVenta");
              listaFecha.add(vector_fechaVenta[contador]);
              vector_estatus_cantidad[contador] = rs.getInt("Venta");
              listaCantidad.add(vector_estatus_cantidad[contador]);
              
              
              
              contador++;
             
              
                
            }
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("Error en: " + e);
        }
    }
    
    //METODO PARA DETERMINAR CUAL ES EL DIA DE MAYOR CANTIDAD DE VENTAS 
    
    public int MetodoMayroVenta(ArrayList<Integer> listaCantidad){
        int mayor = 0;
        for(int i = 0; i < listaCantidad.size(); i++){
            if(listaCantidad.get(i) > mayor){
                mayor = listaCantidad.get(i);
                
            }
        }
        return mayor;
   
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccione Fechas para Graficar ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables


    //Metodo que dibuja la grafica
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        int mayorVenta = MetodoMayroVenta(listaCantidad);
        int largo_NuevoIngreso = 0;
        int parametro1 = 133;
        int parametroFecha = 118;
        int parametro3 = 100;
        
        
        for(int i = 0; i<listaCantidad.size(); i++){
            largo_NuevoIngreso = listaCantidad.get(i) * 400 / mayorVenta;
            if(i == 0 ){
                g.setColor(new Color(140, 0, 75));
            }else if(i == 1){
                g.setColor(new Color(255, 0, 0));
            }else if(i == 2){
                g.setColor(new Color(0, 0, 0));
            }else if(i == 3){
                g.setColor(new Color(255, 255, 255));
            }else if(i == 4){
                g.setColor(new Color(0, 85, 0));
            }else if(i == 5){
                g.setColor(new Color(0, 0, 255));
            }else if(i == 6){
                g.setColor(new Color(255, 127, 0));
            }else{
                g.setColor(new Color(17, 251, 216));
            }
            
            g.fillRect(100, parametro3, largo_NuevoIngreso, 40);
            g.drawString(listaFecha.get(i), 10, parametroFecha);
            g.drawString("Cantidad: " + listaCantidad.get(i), 10, parametro1);
            parametro1 += 50;
            parametroFecha += 50;
            parametro3 += 50;
            
            
        }
        
    }    


}
