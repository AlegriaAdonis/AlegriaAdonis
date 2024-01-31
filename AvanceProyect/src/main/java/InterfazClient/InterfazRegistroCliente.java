
package InterfazClient;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.mycompany.avanceproyect.Clientes;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class InterfazRegistroCliente extends javax.swing.JFrame {
public static LinkedList contenedor=new LinkedList();
    private String Precio;
    public InterfazRegistroCliente() {
        initComponents();
        CargarDatos();
        CargarInterfaz();
        Precio1();
        this.setLocationRelativeTo(null);
       // jTFNombres.setBackground(new Color(0,0,0,64));
    }
    public void CargarDatos(){
        Clientes a;
        for(int i=0;i<contenedor.size();i++){
        a=(Clientes)contenedor.get(i);
        modelo.insertRow(contador,new Object[]{});
        modelo.setValueAt(a.getNombres(), contador, 0);
        modelo.setValueAt(a.getCedula(), contador, 1);
        modelo.setValueAt(a.getTelefono(), contador, 2);
        modelo.setValueAt(a.getCorreo(), contador, 3);

        }
    }
    private DefaultTableModel modelo;
    int contador=0;
    public void CargarInterfaz(){
    String datos[][]={};
    String columna[]={" Nombres "," Cedula "," Telefono "," Correo "," Nacimiento "," Equipo "," Pago "};
    modelo=new DefaultTableModel(datos,columna);
    jTableDatos.setModel(modelo);
    }
    private boolean ValidarDatos() {
        // valida nombres
        String nombres = jTFNombres.getText();
        if (!nombres.matches("[A-Z][a-z]* [A-Z][a-z]*")) {
            JOptionPane.showMessageDialog(this, "Error en nombres y apellidos. Por favor, escriba un nombre seguido por un apellido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // valida cedula
        String cedula = jTFCedula.getText();
        if (!cedula.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Error en cedula tiene que ser de 10 dijitos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // valida el telefono
        String telefono = jTFTelefono.getText();
        if (!telefono.matches("09\\d{8}")) {
            JOptionPane.showMessageDialog(this, "Error en telefono tiene que ser de 10 digitos y empezar con 09 ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // valida el 2 correos
        String correo = jTFCorreo.getText();
        if (!correo.matches(".+@(gmail\\.com|hotmail\\.com)")) {
            JOptionPane.showMessageDialog(this, "Error en correo tiene que terminar en @gmail.com o @hotmail.com", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // valida el si o no 
        if (!(jCBSi.isSelected() ^ jCBNo.isSelected())) {
        JOptionPane.showMessageDialog(this, "Error en equipo, debe marcar exactamente un recuadro 'Si' o 'No'", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        } else if (jCBSi.isSelected()) {
        // Verifica si el campo de nombre del equipo está vacío
        String nombreEquipo = jTFNombreEquipo.getText().trim();
        if (nombreEquipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error en equipo, debe ingresar el nombre del equipo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        }

        // valida nombre trajeta
        String nombreTarjeta = jTFNameCard.getText().toUpperCase();

        if (!nombreTarjeta.matches("[A-Z]+ [A-Z]+ [A-Z]+ [A-Z]+")) {
            JOptionPane.showMessageDialog(this, "Error en el formato del nombre de propietario de tarjeta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // valida numero tarjeta
       String numeroTarjeta = jTFNumerCard.getText();
       if (!numeroTarjeta.matches("\\d{16}")) {
           JOptionPane.showMessageDialog(this, "Error en número de tarjeta, debe contener solo 16 dígitos numericos", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
       }

       // valida pin 
       String pinTarjeta = jTFPinCrad.getText();
       if (!pinTarjeta.matches("\\d{3}")) {
           JOptionPane.showMessageDialog(this, "Error en pin de tarjeta, debe contener solo 3 dígitos numericos", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
       }
       
       // Validar fecha de nacimiento
        String birthDate = jTFNacimiento.getText();

        // Verificar el formato DD/MM/YY
        if (!birthDate.matches("\\d{2}/\\d{2}/\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Error en la fecha de nacimiento, tiene que ser en el formato DD/MM/YY", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Extraer los componentes de la fecha
        String[] dateParts = birthDate.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        // Verificar los límites de los componentes de la fecha de nacimiento
        if (day < 1 || day > 31 || month < 1 || month > 12 || year >= 24) {
            JOptionPane.showMessageDialog(this, "The entered birth date is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar fecha de expiración de la tarjeta
        String fechaExpiracion = jTFFechaCard.getText();

        // Verificar el formato MM/YY
        if (!fechaExpiracion.matches("\\d{2}/\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Error in expiration date, it must contain only numeric digits in the format MM/YY", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String[] partes = fechaExpiracion.split("/");
        int mes, anio;
        try {
            // Convertir las partes a enteros
            mes = Integer.parseInt(partes[0]);
            anio = Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error converting month and year values", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar el rango del mes de expiración
        if (mes < 1 || mes > 12) {
            JOptionPane.showMessageDialog(this, "Error in expiration date, the month must be in the range of 1 to 12", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar que el año de expiración sea mayor a 24
        if (anio <= 24) {
            JOptionPane.showMessageDialog(this, "Error in expiration date, the year must be greater than 24", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Si todas las validaciones pasan, las fechas son válidas
        return true;

    }
    
    private void AgregarTabla(){
         try {
            String[] info = new String[7];

            info[0] = jTFNombres.getText();
            info[1] = jTFCedula.getText();
            info[2] = jTFTelefono.getText();
            info[3] = jTFCorreo.getText();
            info[4] = jTFNacimiento.getText();
            info[5] = jTFNombreEquipo.getText();
            info[6] = jTFPago.getText();
            modelo.addRow(info);
            guardarDatosJSON("ClientesJSON.json", true);
            guardarDatosMongoDB();

            JOptionPane.showMessageDialog(null, "Datos guardados en MongoDB y JSON");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar datos en MongoDB o JSON");
        }
    }
     private void guardarDatosMongoDB() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("PaintBoll");
            MongoCollection<Document> collection = database.getCollection("ClienteRegistro");
            // Guardar datos en MongoDB
            for (int i = 0; i < jTableDatos.getRowCount(); i++) {
                Document document = new Document();
                for (int j = 0; j < jTableDatos.getColumnCount(); j++) {
                    String columnName = jTableDatos.getColumnName(j);
                    Object value = jTableDatos.getValueAt(i, j);
                    document.append(columnName, value);
                }
                collection.insertOne(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar datos en MongoDB");
        }
    }
    public void guardarDatosJSON(String nombreArchivo, boolean append) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo,true))) {

        // Escribir datos
        for (int i = 0; i < jTableDatos.getRowCount(); i++) {
            for (int j = 0; j < jTableDatos.getColumnCount(); j++) {
                writer.write(jTableDatos.getValueAt(i, j).toString());
                if (j < jTableDatos.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();
        jCBSi = new javax.swing.JCheckBox();
        jCBNo = new javax.swing.JCheckBox();
        jTFPinCrad = new javax.swing.JTextField();
        jTFFechaCard = new javax.swing.JTextField();
        jTFNumerCard = new javax.swing.JTextField();
        jTFNameCard = new javax.swing.JTextField();
        jTFNombres = new javax.swing.JTextField();
        jTFCedula = new javax.swing.JTextField();
        jTFTelefono = new javax.swing.JTextField();
        jTFCorreo = new javax.swing.JTextField();
        jTFPago = new javax.swing.JTextField();
        jTFNombreEquipo = new javax.swing.JTextField();
        jTFNacimiento = new javax.swing.JTextField();
        jTFDireccion = new javax.swing.JTextField();
        jBAgregarCliente = new javax.swing.JButton();
        jBlimpiarcampos1 = new javax.swing.JButton();
        jBRegresar = new javax.swing.JButton();
        jLFondo = new javax.swing.JLabel();

        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane1.setViewportView(jTableDatos);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCBSi.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jCBSi.setForeground(new java.awt.Color(51, 51, 255));
        jCBSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBSiActionPerformed(evt);
            }
        });
        getContentPane().add(jCBSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 20, 20));

        jCBNo.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jCBNo.setForeground(new java.awt.Color(51, 51, 255));
        getContentPane().add(jCBNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));

        jTFPinCrad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFPinCradActionPerformed(evt);
            }
        });
        getContentPane().add(jTFPinCrad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 50, -1));
        getContentPane().add(jTFFechaCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 80, 20));

        jTFNumerCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumerCardActionPerformed(evt);
            }
        });
        getContentPane().add(jTFNumerCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 160, 20));

        jTFNameCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNameCardActionPerformed(evt);
            }
        });
        getContentPane().add(jTFNameCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 290, 20));

        jTFNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombresActionPerformed(evt);
            }
        });
        getContentPane().add(jTFNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 220, 30));

        jTFCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCedulaActionPerformed(evt);
            }
        });
        getContentPane().add(jTFCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 120, 30));

        jTFTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(jTFTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 110, 20));

        jTFCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCorreoActionPerformed(evt);
            }
        });
        getContentPane().add(jTFCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 190, 30));

        jTFPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFPagoActionPerformed(evt);
            }
        });
        getContentPane().add(jTFPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 90, 30));

        jTFNombreEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombreEquipoActionPerformed(evt);
            }
        });
        getContentPane().add(jTFNombreEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 210, 20));

        jTFNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNacimientoActionPerformed(evt);
            }
        });
        getContentPane().add(jTFNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 100, 20));

        jTFDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(jTFDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 270, 20));

        jBAgregarCliente.setBackground(new java.awt.Color(102, 102, 255));
        jBAgregarCliente.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jBAgregarCliente.setText("Agregar Cliente");
        jBAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jBAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, 150, 30));

        jBlimpiarcampos1.setBackground(new java.awt.Color(204, 204, 255));
        jBlimpiarcampos1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jBlimpiarcampos1.setText("Limpiar campos");
        jBlimpiarcampos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarcampos1ActionPerformed(evt);
            }
        });
        getContentPane().add(jBlimpiarcampos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, 140, 30));

        jBRegresar.setText("<");
        jBRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(jBRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 60));

        jLFondo.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3.jpg"))); // NOI18N
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBSiActionPerformed

    private void jTFNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombresActionPerformed

    private void jTFCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCedulaActionPerformed

    private void jTFDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFDireccionActionPerformed

    private void jTFTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTelefonoActionPerformed

    private void jTFCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCorreoActionPerformed

    private void jBAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarClienteActionPerformed
        // TODO add your handling code here:
        if (ValidarDatos()) {
            AgregarTabla();
            JOptionPane.showMessageDialog(null, "Pago exitoso. Cliente agregado correctamente");
        }
    }//GEN-LAST:event_jBAgregarClienteActionPerformed

    private void jBlimpiarcampos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarcampos1ActionPerformed
        // TODO add your handling code here:
        // Limpia los campos de texto
        jTFNombres.setText("");
        jTFCedula.setText("");
        jTFDireccion.setText("");
        jTFTelefono.setText("");
        jTFCorreo.setText("");
        jTFNacimiento.setText("");
        jTFNombreEquipo.setText("");
        jTFPago.setText("");
        // Limpia los campos de texto
        jTFNameCard.setText("");
        jTFNumerCard.setText("");
        jTFPinCrad.setText("");
        jTFFechaCard.setText("");
        
    }//GEN-LAST:event_jBlimpiarcampos1ActionPerformed

    private void jTFNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNacimientoActionPerformed

    private void jTFNombreEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombreEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombreEquipoActionPerformed
    private void Precio1(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Precio1.json"))) {
            String linea;
            
            // Leer cada línea del archivo
            while ((linea = reader.readLine()) != null) {
                // Procesar la línea como sea necesario
                jTFPago.setText(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jTFPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFPagoActionPerformed
        // TODO add your handling code here:

        
    }//GEN-LAST:event_jTFPagoActionPerformed

    private void jBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegresarActionPerformed
        // TODO add your handling code here:
        /*try (BufferedWriter writer = new BufferedWriter(new FileWriter("Precio1.json"))) {
        // Escribir datos
            writer.write("");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        this.dispose();
        new Entradas().setVisible(true);
    }//GEN-LAST:event_jBRegresarActionPerformed

    private void jTFNameCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNameCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNameCardActionPerformed

    private void jTFNumerCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumerCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumerCardActionPerformed

    private void jTFPinCradActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFPinCradActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFPinCradActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazRegistroCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAgregarCliente;
    private javax.swing.JButton jBRegresar;
    private javax.swing.JButton jBlimpiarcampos1;
    private javax.swing.JCheckBox jCBNo;
    private javax.swing.JCheckBox jCBSi;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCedula;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFFechaCard;
    private javax.swing.JTextField jTFNacimiento;
    private javax.swing.JTextField jTFNameCard;
    private javax.swing.JTextField jTFNombreEquipo;
    private javax.swing.JTextField jTFNombres;
    private javax.swing.JTextField jTFNumerCard;
    private javax.swing.JTextField jTFPago;
    private javax.swing.JTextField jTFPinCrad;
    private javax.swing.JTextField jTFTelefono;
    private javax.swing.JTable jTableDatos;
    // End of variables declaration//GEN-END:variables
}
