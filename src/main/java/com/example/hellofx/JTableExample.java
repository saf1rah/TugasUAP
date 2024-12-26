package com.example.hellofx;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Form Tabel dengan Input Pengguna");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        String[] columns = {"ID", "Nama Depan", "Nama Belakang", "Usia"};
        String[][] data = {
                {"1", "Budi", "Santoso", "25"},
                {"2", "Siti", "Nurhaliza", "30"},
                {"3", "Ahmad", "Suhendra", "40"},
                {"4", "Dewi", "Putri", "35"},
                {"5", "Sony", "Wakwau", "20"} // Menambahkan data Sony Wakwau
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2)); // Menambah 1 baris lagi untuk tombol
        inputPanel.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nama Depan:"));
        JTextField firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Nama Belakang:"));
        JTextField lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("Usia:"));
        JTextField ageField = new JTextField();
        inputPanel.add(ageField);

        JButton addButton = new JButton("Tambah Data");
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = idField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String age = ageField.getText();

                String[] newRow = {id, firstName, lastName, age};
                tableModel.addRow(newRow);

                // Membersihkan field input setelah data ditambahkan
                idField.setText("");
                firstNameField.setText("");
                lastNameField.setText("");
                ageField.setText("");
            }
        });

        frame.setVisible(true);
    }
}
