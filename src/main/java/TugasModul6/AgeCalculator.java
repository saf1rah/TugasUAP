package TugasModul6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AgeCalculator extends JFrame {

    private JLabel lblJudul;
    private JLabel lblHariLahir;
    private JLabel lblBulanLahir;
    private JLabel lblTahunLahir;
    private JTextField txtHariLahir;
    private JTextField txtBulanLahir;
    private JTextField txtTahunLahir;
    private JButton btnHitung;
    private JLabel lblHasil;
    private JTable tabel;
    private DefaultTableModel modelTabel;

    private ArrayList<String[]> inputPengguna;

    public AgeCalculator() {
        inputPengguna = new ArrayList<>();

        setTitle("Kalkulator Usia");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Tampilan di tengah
        setLayout(new BorderLayout()); // Mengatur layout

        lblJudul = new JLabel("Hitung Usia Anda", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Arial", Font.BOLD, 16));
        lblHariLahir = new JLabel("Masukkan Tanggal Lahir Anda:");
        lblBulanLahir = new JLabel("Masukkan Bulan Lahir Anda:");
        lblTahunLahir = new JLabel("Masukkan Tahun Lahir Anda:");
        txtHariLahir = new JTextField();
        txtBulanLahir = new JTextField();
        txtTahunLahir = new JTextField();
        btnHitung = new JButton("Hitung Usia");
        lblHasil = new JLabel("", SwingConstants.CENTER);

        btnHitung.setBackground(new Color(128, 0, 0));
        btnHitung.setForeground(Color.WHITE);

        String[] namaKolom = {"Tanggal Lahir", "Bulan Lahir", "Tahun Lahir", "Hasil Umur"};
        modelTabel = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(modelTabel);
        JScrollPane scrollPane = new JScrollPane(tabel);

        // Panel untuk bidang input
        JPanel panelInput = new JPanel(new GridLayout(5, 2));
        panelInput.add(lblJudul);
        panelInput.add(new JLabel("")); // untuk tata letak simetris
        panelInput.add(lblHariLahir);
        panelInput.add(txtHariLahir);
        panelInput.add(lblBulanLahir);
        panelInput.add(txtBulanLahir);
        panelInput.add(lblTahunLahir);
        panelInput.add(txtTahunLahir);
        panelInput.add(btnHitung);
        panelInput.add(lblHasil);


        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungUmur();
            }
        });
    }
    private void hitungUmur() {
        String teksHariLahir = txtHariLahir.getText().trim();
        String teksBulanLahir = txtBulanLahir.getText().trim();
        String teksTahunLahir = txtTahunLahir.getText().trim();

        if (teksHariLahir.isEmpty() || teksBulanLahir.isEmpty() || teksTahunLahir.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan Tanggal Lahir lengkap!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {

                int hariLahir = Integer.parseInt(teksHariLahir);
                int bulanLahir = Integer.parseInt(teksBulanLahir);
                int tahunLahir = Integer.parseInt(teksTahunLahir);

                LocalDate tanggalLahir = LocalDate.of(tahunLahir, bulanLahir, hariLahir);
                LocalDate tanggalSekarang = LocalDate.now();


                if (tanggalLahir.isAfter(tanggalSekarang)) {
                    lblHasil.setText("Tanggal lahir tidak valid!");
                } else {

                    Period umur = Period.between(tanggalLahir, tanggalSekarang);
                    String hasilUmur = umur.getYears() + " tahun, " +
                            umur.getMonths() + " bulan, dan " + umur.getDays() + " hari.";
                    lblHasil.setText("Umur Anda adalah: " + hasilUmur);

                    String[] baris = {teksHariLahir, teksBulanLahir, teksTahunLahir, hasilUmur};
                    inputPengguna.add(baris);

                    modelTabel.addRow(baris);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Input Tidak Valid. Masukkan Angka Yang Benar!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Tanggal Tidak Valid. Periksa Kembali Input Anda!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgeCalculator().setVisible(true);
            }
        });
    }
}
