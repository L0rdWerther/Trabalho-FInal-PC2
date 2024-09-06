import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class HospedagemTable extends JFrame {
    private JTable table;
    private JButton addButton;

    public HospedagemTable() {
        setTitle("Hospedagens");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        table = new JTable();
        addButton = new JButton("Adicionar Hospedagem");

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);

        loadData();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HospedagemForm hospedagemForm = new HospedagemForm(HospedagemTable.this);
                hospedagemForm.setVisible(true);
            }
        });
    }

    public void refreshData() {
        loadData();
    }

    private void loadData() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Hospedagem";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                Vector<String> columnNames = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                Vector<Vector<Object>> data = new Vector<>();
                while (resultSet.next()) {
                    Vector<Object> vector = new Vector<>();
                    for (int i = 1; i <= columnCount; i++) {
                        vector.add(resultSet.getObject(i));
                    }
                    data.add(vector);
                }

                table.setModel(new DefaultTableModel(data, columnNames));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage());
        }
    }
}