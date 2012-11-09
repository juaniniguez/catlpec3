/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoc.tds.pec3.client;

import edu.uoc.tds.pec3.common.GestorEstocInterface;
import edu.uoc.tds.pec3.i18n.TDSLanguageUtils;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author C005352
 */
public class ClienteMenu extends javax.swing.JFrame {

    private GestorEstocInterface remoto;
    private final String URL = "localhost";
    private final int PORT = 1099;
    private final String JNDI_NAME = "GestorEstoc";
    
    /**
     * Creates new form ClienteMenu
     */
    public ClienteMenu() {
        initComponents();
        TDSLanguageUtils.setDefaultLanguage("i18n/messages");
        this.setTitle(TDSLanguageUtils.getMessage("Application.title"));
        jLbTitle.setText(TDSLanguageUtils.getMessage("Application.title"));
        jMenuFile.setText(TDSLanguageUtils.getMessage("Cliente.menuFile.text"));
        jMenuOptions.setText(TDSLanguageUtils.getMessage("Cliente.menuOptions.text"));
        jMenuItem2.setText(TDSLanguageUtils.getMessage("Cliente.menuItem1_2.text"));
        jMenuItem1.setText(TDSLanguageUtils.getMessage("Cliente.menuItem1_1.text"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbTitle = new javax.swing.JLabel();
        jMenuBar14 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuOptions = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLbTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLbTitle.setForeground(new java.awt.Color(0, 51, 153));
        jLbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLbTitle.setText("jLabel1");

        jMenuFile.setText("File");

        jMenuItem2.setText("jMenuItem2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItem2);

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItem1);

        jMenuBar14.add(jMenuFile);

        jMenuOptions.setText("Edit");

        jMenuItem4.setText("jMenuItem4");
        jMenuOptions.add(jMenuItem4);

        jMenuItem5.setText("jMenuItem5");
        jMenuOptions.add(jMenuItem5);

        jMenuItem6.setText("jMenuItem6");
        jMenuOptions.add(jMenuItem6);

        jMenuBar14.add(jMenuOptions);

        setJMenuBar(jMenuBar14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        
        //Iniciar conexion con el seridor
        conectarCliente();
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        //Salir
        this.dispose();
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public void conectarCliente() {
        try {
            System.out.println("Conectando al servidor desde cliente...");
            Registry registry = LocateRegistry.getRegistry(URL, PORT);
            remoto = (GestorEstocInterface) registry.lookup(JNDI_NAME);
            JOptionPane.showMessageDialog(this, TDSLanguageUtils.getMessage("Cliente.mensajeConectadoServidor"));
        } catch (NotBoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
        
    private void realizarTest() {
        System.out.println("Realizando test...");
        try {
            String respuesta = remoto.test();
            JOptionPane.showMessageDialog(this, respuesta);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
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
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLbTitle;
    private javax.swing.JMenuBar jMenuBar14;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenu jMenuOptions;
    // End of variables declaration//GEN-END:variables
}
