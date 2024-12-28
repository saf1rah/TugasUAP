package TugasUAP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * BMIcalc
 * Aplikasi Kalkulator BMI dengan GUI berbasis Java Swing.
 * Fitur:
 * -Menghitung BMI dari input pengguna.
 * -Menyimpan data ke dalam tabel.
 * -Menampilkan, memperbarui dan menghapus data yang tersimpan.
 */
public class BMIcalc {
    /* Frame utama untuk aplikasi */
    private static JFrame frame;

    /* Komponen input */
    private static JTextField nameField, heightField, weightField, ageField, searchField;

    /* Komponen hasil dan dropdown */
    private static JTextArea resultArea;
    private static JComboBox<String> genderComboBox, activityComboBox;

    /* Model tabel dan layout halaman */
    private static DefaultTableModel tableModel;
    private static JPanel cardsPanel;
    private static CardLayout cardLayout;

    /**
     * Metode main
     * Memulai aplikasi BMI Calculator.
     */
    public static void main(String[] args) {
        frame = new JFrame("BMI Calculator - Modern App");
        frame.setSize(1500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JLabel headerLabel = new JLabel("BMI Calculator", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(255, 105, 180));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Panel utama dengan CardLayout untuk mengelola banyak halaman
        cardsPanel = new JPanel();
        cardLayout = new CardLayout();
        cardsPanel.setLayout(cardLayout);
        frame.add(cardsPanel, BorderLayout.CENTER);

        JPanel inputPage = createInputPage();
        cardsPanel.add(inputPage, "Input Page");

        JPanel resultPage = createResultPage();
        cardsPanel.add(resultPage, "Result Page");

        frame.setVisible(true);
    }

    /* Metode untuk membuat halaman input */
    private static JPanel createInputPage() {
        JPanel inputPage = new JPanel();
        inputPage.setLayout(new BoxLayout(inputPage, BoxLayout.Y_AXIS));
        inputPage.setBackground(Color.WHITE);

        JPanel inputPanel = createInputPanel();
        inputPage.add(inputPanel);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Hasil Perhitungan"));
        inputPage.add(resultArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPage.add(buttonPanel);

        addButton(buttonPanel, "Hitung", e -> calculateBMI());
        addButton(buttonPanel, "Lihat Hasil", e -> cardLayout.show(cardsPanel, "Result Page"));

        return inputPage;
    }

    /* Metode untuk membuat halaman hasil */
    private static JPanel createResultPage() {
        JPanel resultPage = new JPanel();
        resultPage.setLayout(new BoxLayout(resultPage, BoxLayout.Y_AXIS));
        resultPage.setBackground(Color.WHITE);


        String[] columns = {"Nama", "BMI", "Kategori", "Saran"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        JScrollPane tableScrollPane = new JScrollPane(table);
        resultPage.add(tableScrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultPage.add(buttonPanel);

        addButton(buttonPanel, "Kembali", e -> cardLayout.show(cardsPanel, "Input Page"));
        addButton(buttonPanel, "Update", e -> updateData());
        addButton(buttonPanel, "Delete", e -> deleteData());

        return resultPage;
    }

    /* Metode untuk membuat panel input data */
    private static JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Masukkan Data Anda"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));


        inputPanel.add(new JLabel("Nama:"));
        nameField = new JTextField(15);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Tinggi Badan (cm):"));
        heightField = new JTextField(10);
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Berat Badan (kg):"));
        weightField = new JTextField(10);
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Usia (tahun):"));
        ageField = new JTextField(10);
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Jenis Kelamin (L/P):"));
        genderComboBox = new JComboBox<>(new String[]{"L", "P"});
        inputPanel.add(genderComboBox);

        inputPanel.add(new JLabel("Aktivitas:"));
        activityComboBox = new JComboBox<>(new String[]{"Rendah", "Sedang", "Tinggi"});
        inputPanel.add(activityComboBox);

        return inputPanel;
    }

    /* Metode untuk menambahkan tombol dengan action */
    private static void addButton(JPanel panel, String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(new Color(255, 105, 180));
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        panel.add(button);
    }

    /* Metode untuk menghitung BMI dan menampilkan hasil */
    private static void calculateBMI() {
        try {
            String name = nameField.getText();
            String heightStr = heightField.getText();
            String weightStr = weightField.getText();
            String ageStr = ageField.getText();

            // Validasi input
            if (name.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() || ageStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Harap isi semua kolom!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double height = Double.parseDouble(heightStr) / 100.0;
            double weight = Double.parseDouble(weightStr);
            double bmi = weight / (height * height);

            String category = getBMICategory(bmi);
            String suggestion = getSuggestion(bmi);

            resultArea.setText(String.format("Nama: %s\nBMI: %.2f\nKategori: %s\n\nSaran: %s",
                    name, bmi, category, suggestion));

            tableModel.addRow(new Object[]{name, bmi, category, suggestion});

            cardLayout.show(cardsPanel, "Result Page");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Harap masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /* Metode untuk menentukan kategori BMI */
    private static String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Kekurangan Berat Badan";
        } else if (bmi < 24.9) {
            return "Normal";
        } else if (bmi < 29.9) {
            return "Kelebihan Berat Badan";
        } else {
            return "Obesitas";
        }
    }

    /* Metode untuk memberikan saran berdasarkan BMI */
    private static String getSuggestion(double bmi) {
        if (bmi < 18.5) {
            return "Tingkatkan asupan kalori sehat untuk menambah berat badan.";
        } else if (bmi < 24.9) {
            return "Pertahankan pola makan dan gaya hidup sehat.";
        } else if (bmi < 29.9) {
            return "Kurangi konsumsi kalori dan tingkatkan aktivitas fisik.";
        } else {
            return "Konsultasikan dengan dokter untuk program penurunan berat badan.";
        }
    }

    /* Metode untuk memperbarui data pada tabel */
    private static void updateData() {
        String searchName = JOptionPane.showInputDialog(frame, "Masukkan Nama yang Ingin Diupdate:");
        if (searchName != null && !searchName.isEmpty()) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(searchName)) {
                    String newName = JOptionPane.showInputDialog(frame, "Nama Baru:", tableModel.getValueAt(i, 0));
                    String newHeight = JOptionPane.showInputDialog(frame, "Tinggi Baru (cm):", heightField.getText());
                    String newWeight = JOptionPane.showInputDialog(frame, "Berat Baru (kg):", weightField.getText());

                    double height = Double.parseDouble(newHeight) / 100.0; // Convert cm to meters
                    double weight = Double.parseDouble(newWeight);
                    double bmi = weight / (height * height);

                    tableModel.setValueAt(newName, i, 0);
                    tableModel.setValueAt(bmi, i, 1);
                    tableModel.setValueAt(getBMICategory(bmi), i, 2);
                    tableModel.setValueAt(getSuggestion(bmi), i, 3);

                    JOptionPane.showMessageDialog(frame, "Data berhasil diperbarui!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Nama tidak ditemukan!");
        }
    }

    /* Metode untuk menghapus data pada tabel */
    private static void deleteData() {
        String searchName = JOptionPane.showInputDialog(frame, "Masukkan Nama yang Ingin Dihapus:");
        if (searchName != null && !searchName.isEmpty()) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(searchName)) {
                    tableModel.removeRow(i);
                    JOptionPane.showMessageDialog(frame, "Data berhasil dihapus!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Nama tidak ditemukan!");
        }
    }
}
