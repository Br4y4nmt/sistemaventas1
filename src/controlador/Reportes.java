package controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Brayan
 */
public class Reportes {

    public void ReportesClientes() {
        Document documento = new Document();
        try {
            
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\medin\\OneDrive\\Documentos/Reportes _Clientes.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nBrayan Medina ©BrayanMedina\n\n ");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Cliente \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("DNI");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select idCliente, concat(nombre, ' ', apellido) as nombres, cedula, telefono, direccion from tb_cliente ");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));

                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);

        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void ReportesProductos() {
        Document documento = new Document();
        try {
            
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\medin\\OneDrive\\Documentos/Reportes _Productos.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nBrayan Medina ©BrayanMedina\n\n ");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Productos\n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 5, 4, 5, 7, 5, 6};
            

            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("Cant.");
            tabla.addCell("Precio");
            tabla.addCell("Descripcion");
            tabla.addCell("IGV");
            tabla.addCell("Categoria");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion , p.porcentajeIva, c.descripcion as categoria, p.estado from tb_producto as p, tb_categoria as c where p.idCategoria = c.idCategoria;");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));

                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);

        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void ReportesCategorias() {
        Document documento = new Document();
        try {
            
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\medin\\OneDrive\\Documentos/Reportes _Categorias.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nBrayan Medina ©BrayanMedina\n\n ");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Categorias\n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);
            
           
            

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Descripcion");
            tabla.addCell("Estado");
            

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select * from tb_categoria");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        

                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);

        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void ReportesVentas() {
        Document documento = new Document();
        try {
            
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\medin\\OneDrive\\Documentos/Reportes _Ventas.pdf"));
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            //formato al texto
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nBrayan Medina ©BrayanMedina\n\n ");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas \n\n");

            documento.open();
            //agregamos los datos
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 9, 4, 5, 3};
            

            PdfPTable tabla = new PdfPTable(columnsWidths);
            tabla.addCell("Codigo");
            tabla.addCell("Cliente ");
            tabla.addCell("Tot. Pagar");
            tabla.addCell("Fecha de Venta");
            tabla.addCell("Estado");
          

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select cv.idCabeceraVenta as id, concat(c.nombre, ' ', c.apellido) as cliente, "
                        + "cv.valorPagar as total, cv.fechaVenta as fecha, cv.estado "
                        + "from tb_cabecera_venta as cv, tb_cliente as c "
                        + "where cv.idCliente = c.idCliente; ");
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        

                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.out.println("Error 4 en: " + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Creado");

        } catch (DocumentException e) {
            System.out.println("Error 1 en: " + e);

        } catch (FileNotFoundException ex) {
            System.out.println("Error 2 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error 3 en: " + ex);
            //Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
