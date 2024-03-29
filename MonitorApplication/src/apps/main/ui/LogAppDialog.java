package apps.main.ui;

import api.connect.ControllerApi;
import database.connect.MySQLConnect;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author nathee
 */
public class LogAppDialog extends javax.swing.JDialog {

    private static final Logger LOGGER = Logger.getLogger(LogAppDialog.class);

    public LogAppDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        checkStatusLoad();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnShowLogFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbTargetLogFiles = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbPosDbConnect = new javax.swing.JLabel();
        lbMemberDbConnect = new javax.swing.JLabel();
        lbServiceMemberConnect = new javax.swing.JLabel();
        lbServiceRedeemConnect = new javax.swing.JLabel();
        btnCheckStatus = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setTitle("Log Applicataion Sync CRM v1.2");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnShowLogFile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnShowLogFile.setText("Open File");
        btnShowLogFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowLogFileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Select files");

        cbTargetLogFiles.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTargetLogFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnShowLogFile)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTargetLogFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowLogFile)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Database POS connect:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Database Member connect:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Service Member:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Service Redeem:");

        lbPosDbConnect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbPosDbConnect.setForeground(new java.awt.Color(204, 0, 0));
        lbPosDbConnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPosDbConnect.setText("Disconnect");
        lbPosDbConnect.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbMemberDbConnect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbMemberDbConnect.setForeground(new java.awt.Color(204, 0, 0));
        lbMemberDbConnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMemberDbConnect.setText("Disconnect");
        lbMemberDbConnect.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbServiceMemberConnect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbServiceMemberConnect.setForeground(new java.awt.Color(204, 0, 0));
        lbServiceMemberConnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbServiceMemberConnect.setText("Disconnect");
        lbServiceMemberConnect.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbServiceRedeemConnect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbServiceRedeemConnect.setForeground(new java.awt.Color(204, 0, 0));
        lbServiceRedeemConnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbServiceRedeemConnect.setText("Disconnect");
        lbServiceRedeemConnect.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPosDbConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbServiceRedeemConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(lbMemberDbConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbServiceMemberConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbPosDbConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbMemberDbConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbServiceMemberConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbServiceRedeemConnect))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnCheckStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCheckStatus.setText("Check Status");
        btnCheckStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckStatusActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem3.setText("Show Tables");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Hide");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem2.setText("Configuraction File");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheckStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnCheckStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ConfigDialog configDialog = new ConfigDialog(null, true);
        configDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnShowLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowLogFileActionPerformed
        try {
            Desktop.getDesktop().open(new File(cbTargetLogFiles.getSelectedItem().toString()));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }//GEN-LAST:event_btnShowLogFileActionPerformed

    private void btnCheckStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckStatusActionPerformed
        checkStatusLoad();
    }//GEN-LAST:event_btnCheckStatusActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        TableDataDialog tableDialog = new TableDataDialog(null, true);
        tableDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckStatus;
    private javax.swing.JButton btnShowLogFile;
    private javax.swing.JComboBox<String> cbTargetLogFiles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbMemberDbConnect;
    private javax.swing.JLabel lbPosDbConnect;
    private javax.swing.JLabel lbServiceMemberConnect;
    private javax.swing.JLabel lbServiceRedeemConnect;
    // End of variables declaration//GEN-END:variables

    private void loadFileLogs() {
        LOGGER.debug("loadFileLogs");
        File[] files = new File(".").listFiles();
        cbTargetLogFiles.removeAllItems();
        for (File f : files) {
            if (f.getName().lastIndexOf(".log") != -1) {
                cbTargetLogFiles.addItem(f.getName());
            }
        }
    }

    private void loadConnection() {
        LOGGER.debug("loadConnection");
        checkConnectionMySQL();
        checkConnectionService();
    }

    private void checkConnectionMySQL() {
        MySQLConnect mysql = new MySQLConnect();
        try {
            // pos db connect
            Connection conn = mysql.open("pos");
            if (conn != null) {
                lbPosDbConnect.setText("Connected.");
                lbPosDbConnect.setForeground(Color.BLUE);
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }

        try {
            // member db connect
            Connection conn = mysql.open("member");
            if (conn != null) {
                lbMemberDbConnect.setText("Connected.");
                lbMemberDbConnect.setForeground(Color.BLUE);
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    private void checkConnectionService() {
        ControllerApi api = new ControllerApi();
        if (api.getVersion()) {
            lbServiceMemberConnect.setText("Connected.");
            lbServiceRedeemConnect.setText("Connected.");
            lbServiceMemberConnect.setForeground(Color.BLUE);
            lbServiceRedeemConnect.setForeground(Color.BLUE);
        }
    }

    private void checkStatusLoad() {
        loadConnection();
        loadFileLogs();
    }
}
