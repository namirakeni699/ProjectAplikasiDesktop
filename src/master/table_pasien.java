/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import icon.tableCustom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author COMPUTER
 */

public class table_pasien extends javax.swing.JFrame {
DefaultTableModel table = new DefaultTableModel();
    /**
     * Creates new form table_pasien
     */
    public table_pasien() {
        initComponents();
            icon.tableCustom.apply(jScrollPane1, tableCustom.TableType.DEFAULT);
        table_pasien.setModel(table);
        table.addColumn("No Rekam Medis");
        table.addColumn("Nama");
        table.addColumn("Usia");
        table.addColumn("Jenis Kelamin");
        table.addColumn("Tanggal Masuk");
        table.addColumn("Nomor Telepon");
        table.addColumn("Alamat");
        tampilData(); 
    }
     public void tampilData(){
        //untuk mengahapus baris setelah input
        int row = table_pasien.getRowCount();
        for(int a = 0 ; a < row ; a++){
            table.removeRow(0);
        }
String query = "SELECT id_pasien,nama_pasien,usia,jenis_kelamin,tgl_masuk,telp_pasien,alamat FROM data_pasien";


        try{
            Connection connect = koneksi.getKoneksi();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(query);//menjalanakn query
            
            while (rslt.next()){
                //menampung data sementara
                   
                    String nip = rslt.getString("id_pasien");
                    String nama = rslt.getString("nama_pasien");
                    String spesialis = rslt.getString("usia");
                    String notelp = rslt.getString("jenis_kelamin");
                    String alamat = rslt.getString("tgl_masuk");                   
                    String jadwal = rslt.getString("telp_pasien");
                    String alamat2 = rslt.getString("alamat");

                //masukan semua data kedalam array
                String[] data = {nip,nama,spesialis,notelp,alamat,jadwal,alamat2};
                //menambahakan baris sesuai dengan data yang tersimpan diarray
                
                table.addRow(data);
            }
                //mengeset nilai yang ditampung agar muncul di table
                table_pasien.setModel(table);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        finally{
            
        }
       
    }
private void setDataFromSelectedRowToForm() {
    int selectedRow = table_pasien.getSelectedRow();

    if (selectedRow >= 0) {
        String id_pasien = table_pasien.getValueAt(selectedRow, 0).toString(); // Mengambil ID pasien dari tabel
        // Query untuk mengambil data dari database berdasarkan ID pasien yang dipilih
        String query = "SELECT * FROM data_pasien WHERE id_pasien = ?";

        try {
            Connection connection = koneksi.getKoneksi();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id_pasien); // Mengganti parameter pertama dengan ID pasien yang dipilih
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                data_pasien formPasien = new data_pasien();
                
                formPasien.txt_idpasien.setText(resultSet.getString("id_pasien"));
                formPasien.txt_namapasien.setText(resultSet.getString("nama_pasien"));
                formPasien.txt_agama.setText(resultSet.getString("agama"));
                formPasien.txt_tgllahir.setText(resultSet.getString("tgl_lahir"));
                formPasien.txt_riwayatsakit1.setText(resultSet.getString("riwayat_penyakit"));
                formPasien.txt_namapasangan.setText(resultSet.getString("nama_pasangan"));
                formPasien.txt_usia.setText(resultSet.getString("usia"));
                formPasien.txt_alamat.setText(resultSet.getString("alamat"));
                formPasien.txt_pekerjaan.setText(resultSet.getString("pekerjaan"));
                formPasien.txt_pendidikan.setText(resultSet.getString("pendidikan"));
                formPasien.txt_goldarah.setText(resultSet.getString("gol_darah"));
                formPasien.txt_telportu.setText(resultSet.getString("telp_ortu"));
                formPasien.txt_kerjaortu.setText(resultSet.getString("pekerjaan_ortu"));
                formPasien.txt_telpon.setText(resultSet.getString("telp_pasien"));
                formPasien.txt_keluhan.setText(resultSet.getString("keluhan"));
                formPasien.txt_alergi.setText(resultSet.getString("alergi_obat"));
                formPasien.txt_kodedokter.setText(resultSet.getString("nip"));
                
                formPasien.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
private void deleteData() {
    int selectedRow = table_pasien.getSelectedRow();

    if (selectedRow >= 0) {
        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data terpilih?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String id_pasien = table_pasien.getValueAt(selectedRow, 0).toString(); // Mengambil ID pasien dari tabel
            // Query untuk menghapus data dari database berdasarkan ID pasien yang dipilih
            String query = "DELETE FROM data_pasien WHERE id_pasien = ?";

            try {
                Connection connection = koneksi.getKoneksi();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id_pasien); // Mengganti parameter pertama dengan ID pasien yang dipilih
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");

                // Setelah menghapus data, Anda bisa me-refresh tabel dengan mengambil data terbaru dari database
               tampilData();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
   
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_tambah = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_pasien = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_tambah.setBackground(new java.awt.Color(255, 255, 255));
        btn_tambah.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_tambah.setText(".\n");
        btn_tambah.setBorderPainted(false);
        btn_tambah.setContentAreaFilled(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 140, 50));

        btn_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_edit.setText(".\n");
        btn_edit.setBorderPainted(false);
        btn_edit.setContentAreaFilled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 140, 50));

        btn_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_hapus.setText(".\n");
        btn_hapus.setBorderPainted(false);
        btn_hapus.setContentAreaFilled(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 190, 130, 50));

        btn_print.setBackground(new java.awt.Color(255, 255, 255));
        btn_print.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        btn_print.setText(".\n");
        btn_print.setBorderPainted(false);
        btn_print.setContentAreaFilled(false);
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 180, 130, 50));

        table_pasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_pasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pasienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_pasien);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 1160, 480));

        jButton1.setText(".");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\COMPUTER\\Downloads\\DataPasien.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        master.data_pasien h = new master.data_pasien();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
      setDataFromSelectedRowToForm();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        try {
            // Lokasi file .jasper
            String file = "/Report/dataPasienReport.jasper";

            // Mengambil koneksi ke database (pastikan Anda memiliki kelas 'koneksi' yang sesuai)
            Connection conn = koneksi.getKoneksi();

            // Mengisi laporan tanpa parameter
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file), null, conn);

            // Menampilkan laporan menggunakan JasperViewer
            JasperViewer.viewReport(print, false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void table_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pasienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_pasienMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        master.menu_utama h = new master.menu_utama();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_btn_hapusActionPerformed

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
            java.util.logging.Logger.getLogger(table_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(table_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(table_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(table_pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new table_pasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_pasien;
    // End of variables declaration//GEN-END:variables
}
