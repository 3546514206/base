package edu.zjnu.base.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ComplexList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public ComplexList() {
        setTitle("Complex List Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a table model and a JTable
        String[] columnNames = {"ID", "Name", "Age"};
        Object[][] data = {{"001", "Alice", 25}, {"002", "Bob", 30}, {"003", "Charlie", 20}, {"004", "David", 35}, {"005", "Eve", 28}};
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the JTable to a scroll pane and a panel
        JScrollPane tableScrollPane = new JScrollPane(table);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Create a list model and a JList
        String[] listData = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"};
        listModel = new DefaultListModel<>();
        for (String item : listData) {
            listModel.addElement(item);
        }
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add a mouse listener to the JList to update the selected row in the table
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                if (index >= 0) {
                    table.setRowSelectionInterval(index, index);
                }
            }
        });

        // Add the JList to a scroll pane and a panel
        JScrollPane listScrollPane = new JScrollPane(list);
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Add both panels to a split pane
        JPanel splitPanePanel = new JPanel(new BorderLayout());
        splitPanePanel.add(tablePanel, BorderLayout.CENTER);
        splitPanePanel.add(listPanel, BorderLayout.EAST);
        getContentPane().add(splitPanePanel, BorderLayout.CENTER);

        // Add a ListSelectionListener to the JTable to update the selected index in the JList
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    list.setSelectedIndex(row);
                    list.ensureIndexIsVisible(row);
                }
            }
        });

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new ComplexList();
    }
}