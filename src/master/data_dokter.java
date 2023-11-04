/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import icon.tableCustom;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
/**
 *
 * @author COMPUTER
 */
public class data_dokter extends javax.swing.JFrame {

    /**
     * Creates new form data_dokter
     */
    DefaultTableModel table = new DefaultTableModel();
    public data_dokter() {
        initComponents();
     
        Locale indonesiaLocale = new Locale("in", "ID"); // Buat objek Locale untuk bahasa Indonesia
              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Mengatur ukuran form sesuai dengan ukuran layar
        setSize(screenSize.width, screenSize.height);
        // Mengatur posisi form di koordinat (0,0) untuk memastikan form berada di pojok kiri atas
        setLocation(0, 0);
      

        tampilData();
    }
    
    private void tampilData() {
        try {
            Connection connect = koneksi.getKoneksi();
            Statement sttmnt = connect.createStatement();
            String query = "SELECT * FROM data_dokter";
            ResultSet rslt = sttmnt.executeQuery(query);

            while (rslt.next()) {
                String nip = rslt.getString("nip");
                String namadokter = rslt.getString("nama_dokter");
                String spesialis = rslt.getString("spesialis");
                String notelp = rslt.getString("no_telpon");
                String alamat = rslt.getString("alamat_dokter");
                String jadwal = rslt.getString("jadwal");

                String[] data = {nip, namadokter, spesialis,notelp,alamat, jadwal};
                table.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void tambahData() {
    String NIP = nip.getText();
    String NAMA = nama_dokter.getText();
    String Spesialis = (String) spesialis.getSelectedItem();
    String NOhp = nohp.getText();
    String Alamat = alamat.getText();
    Locale locale = new Locale("id", "ID");
   SimpleDateFormat date = new SimpleDateFormat("EEEE,dd-MM-YYYY", locale);
    String Jadwal = jadwal.getDate() != null ? date.format(jadwal.getDate()) : null;


    // Memeriksa apakah ada input yang kosong
    if (NIP.isEmpty() || NAMA.isEmpty() || Spesialis.isEmpty() || NOhp.isEmpty() || Alamat.isEmpty() || Jadwal.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data Terlebih Dahulu!");
        return;
    }

    // Panggil koneksi
    Connection connect = koneksi.getKoneksi();
    
    // Query untuk memasukkan data
    String query = "INSERT INTO `data_dokter` (`nip`, `nama_dokter`, `spesialis`,`no_telpon`, `alamat_dokter`, `jadwal`) "
                 + "VALUES ('"+NIP+"', '"+NAMA+"', '"+Spesialis+"','"+NOhp+"', '"+Alamat+"', '"+Jadwal+"')";

    try {
        // Menyiapkan statement untuk dieksekusi
        PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
        ps.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        

    } catch(SQLException | HeadlessException e) {
        JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        System.out.print(e);
    } finally {
        //databarang1.tampilData();
    }
}


private void editData() {
   String NIP = nip.getText();
    String NAMA = nama_dokter.getText();
    String Spesialis = (String) spesialis.getSelectedItem();
    String NOhp = nohp.getText();
    String Alamat = alamat.getText();
    Locale locale = new Locale("id", "ID");
   SimpleDateFormat date = new SimpleDateFormat("EEEE,dd-MM-YYYY", locale);
    String Jadwal = jadwal.getDate() != null ? date.format(jadwal.getDate()) : null;


    // Memeriksa apakah ada input yang kosong
      if (NIP.isEmpty() || NAMA.isEmpty() || Spesialis.isEmpty() || NOhp.isEmpty() || Alamat.isEmpty() || Jadwal.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Harap Lengkapi Data Terlebih Dahulu!");
        return;
    }

    Connection connect = koneksi.getKoneksi();

    String query = "UPDATE `data_dokter` SET `nip` = '"+NIP+"', `nama_dokter` = '"+NAMA+"', `spesialis` = '"+Spesialis+"', `no_telpon` = '"+NOhp+"',`alamat_dokter` = '"+Alamat+"', `jadwal` = '"+Jadwal+"' "
            + "WHERE `data_dokter`.`nip` = '"+NIP+"';";

    try {
        PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
        ps.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
    } catch(SQLException | HeadlessException e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, "Data gagal diubah");
    } finally {
       // databarang1.tampilData();
        clearbrg();
    }
}
    public void clearbrg(){
        nip.setText(null);
        nama_dokter.setText(null);
        nohp.setText(null);
        alamat.setText(null);
      
        //kode_auto();
        //databarang1.tampilData(); 
        btn_tambah.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spesialis = new javax.swing.JComboBox<>();
        nama_dokter = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        nip = new javax.swing.JTextField();
        nohp = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        jadwal = new uz.ncipro.calendar.JDateTimePicker();
        btn_hapus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spesialis.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        spesialis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Umum", "Penyakit Dalam", "Anak", "Syaraf", "THT", "Bedah", "Kulit dan Kelamin", "Mata", " ", " " }));
        getContentPane().add(spesialis, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 420, 60));

        nama_dokter.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nama_dokter.setBorder(null);
        getContentPane().add(nama_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 390, 47));

        alamat.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        alamat.setBorder(null);
        alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatActionPerformed(evt);
            }
        });
        getContentPane().add(alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, 390, 47));

        nip.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nip.setBorder(null);
        getContentPane().add(nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 400, 47));

        nohp.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nohp.setBorder(null);
        getContentPane().add(nohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, 390, 47));

        btn_tambah.setText(".");
        btn_tambah.setBorderPainted(false);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 150, 50));

        btn_edit.setText(".");
        btn_edit.setBorderPainted(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 150, 50));

        jadwal.setBorder(null);
        jadwal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4/11/2023" }));
        jadwal.setDisplayFormat("d/M/yyyy");
        jadwal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        getContentPane().add(jadwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 420, 400, 50));

        btn_hapus.setText(".");
        btn_hapus.setBorderPainted(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 420, 150, 50));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\COMPUTER\\Downloads\\dataDokter (1).png")); // NOI18N
        jLabel1.setText("\n");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        tambahData();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        editData();
    }//GEN-LAST:event_btn_editActionPerformed

    private void alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          master.menu_utama h = new menu_utama();
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(data_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_dokter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_dokter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField alamat;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    public static uz.ncipro.calendar.JDateTimePicker jadwal;
    public static javax.swing.JTextField nama_dokter;
    public static javax.swing.JTextField nip;
    public static javax.swing.JTextField nohp;
    public static javax.swing.JComboBox<String> spesialis;
    // End of variables declaration//GEN-END:variables
}
