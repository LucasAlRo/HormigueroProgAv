/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Server;

import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class InterfazPrincipal extends javax.swing.JFrame {

    private boolean botonPulsado = false;
    private Colonia c;

    public InterfazPrincipal() {
        initComponents();
        c = new Colonia(jExterior, jColonia, jObreras, jSoldados, jCrias, jInstruccion,
                jDescanso, jAlmacen, jContAlmacen,
                jLlevando, jComedor, jContComedor, jDefendiendo, jRefugio,
                jBuscando);
        Generador generador = new Generador(c);
        generador.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jExterior = new javax.swing.JTextField();
        jColonia = new javax.swing.JTextField();
        jInstruccion = new javax.swing.JTextField();
        jDescanso = new javax.swing.JTextField();
        jBotonPausa = new javax.swing.JButton();
        jAlmacen = new javax.swing.JTextField();
        jContAlmacen = new javax.swing.JTextField();
        jLlevando = new javax.swing.JTextField();
        jComedor = new javax.swing.JTextField();
        jContComedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDefendiendo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jRefugio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jAmenazaInsecto = new javax.swing.JToggleButton();
        jSoldados = new javax.swing.JTextField();
        jCrias = new javax.swing.JTextField();
        jObreras = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jBuscando = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jExterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExteriorActionPerformed(evt);
            }
        });

        jColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jColoniaActionPerformed(evt);
            }
        });

        jInstruccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInstruccionActionPerformed(evt);
            }
        });

        jBotonPausa.setText("Detener");
        jBotonPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonPausaActionPerformed(evt);
            }
        });

        jAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlmacenActionPerformed(evt);
            }
        });

        jContAlmacen.setText("0");
        jContAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jContAlmacenActionPerformed(evt);
            }
        });

        jLlevando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLlevandoActionPerformed(evt);
            }
        });

        jComedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComedorActionPerformed(evt);
            }
        });

        jContComedor.setText("0");

        jLabel2.setText("Hormigas repeliendo un insecto invasor:");

        jDefendiendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDefendiendoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Exterior de la colonia:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Interior de la colonia:");

        jLabel5.setText("Almacén:");

        jLabel6.setText("Comedor:");

        jLabel7.setText("Instrucción:");

        jLabel8.setText("Descanso:");

        jLabel9.setText("Llevando:");

        jLabel10.setText("Comida en el almacén:");

        jLabel11.setText("Comida en el comedor:");

        jRefugio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefugioActionPerformed(evt);
            }
        });

        jLabel12.setText("Refugio:");

        jLabel13.setText("Opciones:");

        jAmenazaInsecto.setText("Generar Amenaza Insecto Invasor");
        jAmenazaInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAmenazaInsectoActionPerformed(evt);
            }
        });

        jSoldados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSoldadosActionPerformed(evt);
            }
        });

        jLabel14.setText("Obreras:");

        jLabel15.setText("Soldados:");

        jLabel16.setText("Crias:");

        jLabel17.setText("Hormigas buscando comida:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(33, 33, 33)
                        .addComponent(jExterior))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jInstruccion, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                                .addComponent(jAlmacen)
                                .addComponent(jLlevando)
                                .addComponent(jDescanso))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComedor)
                            .addComponent(jRefugio)
                            .addComponent(jSoldados)
                            .addComponent(jCrias)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBotonPausa)
                                .addGap(18, 18, 18)
                                .addComponent(jAmenazaInsecto)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jObreras)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jContAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jContComedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBuscando)
                            .addComponent(jDefendiendo))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jExterior, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDefendiendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jBuscando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLlevando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jInstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDescanso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jContAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jContComedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jComedor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRefugio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBotonPausa)
                    .addComponent(jLabel13)
                    .addComponent(jAmenazaInsecto))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jObreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSoldados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCrias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jColoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jColoniaActionPerformed

    private void jInstruccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInstruccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jInstruccionActionPerformed

    private void jBotonPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonPausaActionPerformed
        if (!botonPulsado) //Si no se ha pulsado
        {
            botonPulsado = true;
            jBotonPausa.setText("Reanudar");
            c.pausar();
            c.consolaLog("Se ha pausado la ejecucion del programa");
        } else //Si ya se había pulsado
        {
            botonPulsado = false;
            jBotonPausa.setText("Detener");
            c.reanudar();
            c.consolaLog("Se ha reanudado la ejecucion del programa");
        }
    }//GEN-LAST:event_jBotonPausaActionPerformed

    private void jAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlmacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAlmacenActionPerformed

    private void jContAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jContAlmacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jContAlmacenActionPerformed

    private void jLlevandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLlevandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLlevandoActionPerformed

    private void jComedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComedorActionPerformed

    private void jExteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jExteriorActionPerformed

    private void jDefendiendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDefendiendoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDefendiendoActionPerformed

    private void jAmenazaInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAmenazaInsectoActionPerformed
        // TODO add your handling code here:
        if (c.comprobarAmenaza()) {
            JOptionPane.showMessageDialog(null, "¡La colonia no aguantará dos insectos!");
        } else if (c.getSoldadosTotales().getTamano() == 0) {
            JOptionPane.showMessageDialog(null, "¡Aún no hay soldados! ¡No podrán defenderse!");
        } else {
            c.generarInsectoInvasor();
        }
    }//GEN-LAST:event_jAmenazaInsectoActionPerformed

    private void jSoldadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSoldadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSoldadosActionPerformed

    private void jRefugioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefugioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRefugioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAlmacen;
    private javax.swing.JToggleButton jAmenazaInsecto;
    private javax.swing.JButton jBotonPausa;
    private javax.swing.JTextField jBuscando;
    private javax.swing.JTextField jColonia;
    private javax.swing.JTextField jComedor;
    private javax.swing.JTextField jContAlmacen;
    private javax.swing.JTextField jContComedor;
    private javax.swing.JTextField jCrias;
    private javax.swing.JTextField jDefendiendo;
    private javax.swing.JTextField jDescanso;
    private javax.swing.JTextField jExterior;
    private javax.swing.JTextField jInstruccion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLlevando;
    private javax.swing.JTextField jObreras;
    private javax.swing.JTextField jRefugio;
    private javax.swing.JTextField jSoldados;
    // End of variables declaration//GEN-END:variables
}
