
package InterfazClient;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Entradas extends javax.swing.JFrame {

    public Entradas() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jBRegistroCleinte = new javax.swing.JButton();
        jBNormal = new javax.swing.JButton();
        jBCompleta = new javax.swing.JButton();
        jBPremium = new javax.swing.JButton();
        jLFondo = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(960, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBRegistroCleinte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistroCleinteActionPerformed(evt);
            }
        });
        getContentPane().add(jBRegistroCleinte, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 150, 30));

        jBNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNormalActionPerformed(evt);
            }
        });
        getContentPane().add(jBNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 150, 30));

        jBCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCompletaActionPerformed(evt);
            }
        });
        getContentPane().add(jBCompleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 160, 30));

        jBPremium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPremiumActionPerformed(evt);
            }
        });
        getContentPane().add(jBPremium, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 150, 30));

        jLFondo.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2.jpg"))); // NOI18N
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBRegistroCleinteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistroCleinteActionPerformed

        Regis nuevaInterfaz = new Regis();
        nuevaInterfaz.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBRegistroCleinteActionPerformed

    private void jBNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNormalActionPerformed
        // TODO add your handling code here:
        String InterfazRegistroCleinte = jBNormal.getText().toString();
        InterfazRegistroCliente IRC = new InterfazRegistroCliente();
        IRC.setVisible(true);  
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Precio1.json"))) {
        // Escribir datos
            writer.write("29.99 $");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.dispose();
    }//GEN-LAST:event_jBNormalActionPerformed

    private void jBCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCompletaActionPerformed
        String InterfazRegistroCleinte = jBPremium.getText().toString();
        InterfazRegistroCliente IRC = new InterfazRegistroCliente();
        IRC.setVisible(true);  
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Precio1.json"))) {
        // Escribir datos
            writer.write("49.99 $");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dispose();
    }//GEN-LAST:event_jBCompletaActionPerformed

    private void jBPremiumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPremiumActionPerformed
        // TODO add your handling code here:
        String InterfazRegistroCleinte = jBPremium.getText().toString();
        InterfazRegistroCliente IRC = new InterfazRegistroCliente();
        IRC.setVisible(true);  
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Precio1.json"))) {
        // Escribir datos
            writer.write("59.99 $");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dispose();
    }//GEN-LAST:event_jBPremiumActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Entradas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCompleta;
    private javax.swing.JButton jBNormal;
    private javax.swing.JButton jBPremium;
    private javax.swing.JButton jBRegistroCleinte;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLFondo;
    // End of variables declaration//GEN-END:variables
}
