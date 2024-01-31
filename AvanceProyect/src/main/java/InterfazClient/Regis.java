
package InterfazClient;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import java.awt.GridLayout;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.bson.Document;
import javax.swing.table.DefaultTableModel;

public class Regis extends javax.swing.JFrame {
    
    public Regis() {
        initComponents();
        setLocationRelativeTo(null);
        actualizarTabla();
    }
     private void actualizarTabla() {
         try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
        MongoDatabase database = mongoClient.getDatabase("PaintBoll");
        MongoCollection<Document> collection = database.getCollection("ClienteRegistro");

        // Verificar la existencia de la colección
        if (collection == null) {
            JOptionPane.showMessageDialog(this, "La colección no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        // Realizar la consulta solo si la colección existe
        FindIterable<Document> result = collection.find();
        MongoCursor<Document> cursor = result.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            // Verificar y obtener los campos del documento
            String nombre = doc.getString("Nombres");
            String cedula = doc.getString("Cedula");
            String telefono = doc.getString("Telefono");
            String correo = doc.getString("Correo");
            String nacimiento = doc.getString("Nacimiento");
            String equipo = doc.getString("Equipo");
            String pago = doc.getString("Pago");

            model.addRow(new Object[]{nombre, cedula, telefono, correo, nacimiento, equipo, pago});
        }
        model.fireTableDataChanged();
        JOptionPane.showMessageDialog(this, "Datos Cargados", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (MongoException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarPorNombre = new javax.swing.JToggleButton();
        BuscarPorCedula = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BuscarPorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarPorNombreActionPerformed(evt);
            }
        });
        getContentPane().add(BuscarPorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 130, 20));

        BuscarPorCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarPorCedulaActionPerformed(evt);
            }
        });
        getContentPane().add(BuscarPorCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 130, 20));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombres", "Cedula", "Telefono", "Correo", "Nacimiento", "Equipo", "Pago"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 730, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarPorCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarPorCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarPorCedulaActionPerformed

    private void BuscarPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarPorNombreActionPerformed
        // TODO add your handling code here:
       // Obtener el nombre ingresado por el usuario
        String nombreBusqueda = JOptionPane.showInputDialog(this, "Ingrese el nombre a buscar:", "Buscar por Nombre", JOptionPane.PLAIN_MESSAGE);
        // Verificar si el usuario ingresó un nombre
        if (nombreBusqueda != null && !nombreBusqueda.isEmpty()) {
            // Realizar la búsqueda en la base de datos
            buscarPorNombre(nombreBusqueda);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BuscarPorNombreActionPerformed
    private void buscarPorNombre(String nombre) {
    try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
        MongoDatabase database = mongoClient.getDatabase("PaintBoll");
        MongoCollection<Document> collection = database.getCollection("ClienteRegistro");
        // Realizar la búsqueda por nombre
        BasicDBObject query = new BasicDBObject();
        query.put("Nombres", java.util.regex.Pattern.compile(nombre, Pattern.CASE_INSENSITIVE));
        FindIterable<Document> result = collection.find(query);

        // Mostrar los resultados en un JOptionPane
        StringBuilder resultMessage = new StringBuilder("Resultados de la búsqueda:\n");
        for (Document document : result) {
            resultMessage.append("Nombres: ").append(document.getString("Nombres")).append("\n");
            resultMessage.append("Cedula: ").append(document.getString("Cedula")).append("\n");
            resultMessage.append("Telefono: ").append(document.getString("Telefono")).append("\n");
            resultMessage.append("Correo: ").append(document.getString("Correo")).append("\n");
            resultMessage.append("Nacimiento: ").append(document.getString("Nacimiento")).append("\n");
            resultMessage.append("Equipo: ").append(document.getString("Equipo")).append("\n");
            resultMessage.append("Pago: ").append(document.getString("Pago")).append("\n");
            resultMessage.append("\n");
        }

        if (resultMessage.toString().equals("Resultados de la búsqueda:\n")) {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados para el nombre proporcionado.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Pregunta al usuario si desea editar o eliminar
            String[] options = {"Editar", "Eliminar"};
            int choice = JOptionPane.showOptionDialog(this, resultMessage.toString(), "Resultados de la Búsqueda", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // Realiza la acción correspondiente
            if (choice == 0) { // Editar
                // Obtener el primer documento (suponiendo que solo hay un resultado)
                Document document = result.first();
                // Mostrar un cuadro de diálogo para la edición de campos
                editarRegistro(document);
            } else if (choice == 1) { // Eliminar
                int confirmDelete = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    // Elimina el registro de la base de datos
                    collection.deleteOne(query);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al buscar en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void editarRegistro(Document document) {
    // Crear un panel para el cuadro de diálogo de edición
    JPanel panel = new JPanel(new GridLayout(5, 2));
    
    // Agregar campos y valores actuales al panel
    panel.add(new JLabel("Nombres:"));
    JTextField nombresField = new JTextField(document.getString("Nombres"));
    panel.add(nombresField);

    panel.add(new JLabel("Cédula:"));
    JTextField cedulaField = new JTextField(document.getString("Cedula"));
    panel.add(cedulaField);

    panel.add(new JLabel("Teléfono:"));
    JTextField telefonoField = new JTextField(document.getString("Telefono"));
    panel.add(telefonoField);

    panel.add(new JLabel("Correo:"));
    JTextField correoField = new JTextField(document.getString("Correo"));
    panel.add(correoField);

    panel.add(new JLabel("Nacimiento:"));
    JTextField nacimientoField = new JTextField(document.getString("Nacimiento"));
    panel.add(nacimientoField);

    panel.add(new JLabel("Equipo:"));
    JTextField equipoField = new JTextField(document.getString("Equipo"));
    panel.add(equipoField);

    panel.add(new JLabel("Pago:"));
    JTextField pagoField = new JTextField(document.getString("Pago"));
    panel.add(pagoField);

    // Mostrar el cuadro de diálogo para la edición
    int result = JOptionPane.showConfirmDialog(this, panel, "Editar Registro", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        // Obtener los nuevos valores ingresados por el usuario
        String nuevosNombres = nombresField.getText();
        String nuevaCedula = cedulaField.getText();
        String nuevoTelefono = telefonoField.getText();
        String nuevoCorreo = correoField.getText();
        String nuevoNacimiento = nacimientoField.getText();
        String nuevoEquipo = equipoField.getText();
        String nuevoPago = pagoField.getText();

        // Actualizar el documento en la base de datos con los nuevos valores
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("PaintBoll");
            MongoCollection<Document> collection = database.getCollection("ClienteRegistro");

            BasicDBObject filter = new BasicDBObject("Cedula", document.getString("Cedula"));
            BasicDBObject updateFields = new BasicDBObject();
            updateFields.append("Nombres", nuevosNombres)
                       .append("Cedula", nuevaCedula)
                       .append("Telefono", nuevoTelefono)
                       .append("Correo", nuevoCorreo)
                       .append("Nacimiento", nuevoNacimiento)
                       .append("Equipo", nuevoEquipo)
                       .append("Pago", nuevoPago);
            BasicDBObject updateQuery = new BasicDBObject("$set", updateFields);

            collection.updateOne(filter, updateQuery);

            JOptionPane.showMessageDialog(this, "Registro actualizado exitosamente.", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        } catch (MongoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Regis().setVisible(true);
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarPorCedula;
    private javax.swing.JToggleButton BuscarPorNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
