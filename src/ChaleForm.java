import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChaleForm extends JFrame {
    private JTextField localizacaoField;
    private JTextField capacidadeField;
    private JTextField valorAltaEstacaoField;
    private JTextField valorBaixaEstacaoField;
    private JButton salvarButton;

    private List<Chale> chales = new ArrayList<>();

    public ChaleForm(ChaleTable chaleTable) {
        setTitle("Cadastro de Chalé");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        localizacaoField = new JTextField(20);
        capacidadeField = new JTextField(20);
        valorAltaEstacaoField = new JTextField(20);
        valorBaixaEstacaoField = new JTextField(20);
        salvarButton = new JButton("Salvar");

        add(new JLabel("Localização:"));
        add(localizacaoField);
        add(new JLabel("Capacidade:"));
        add(capacidadeField);
        add(new JLabel("Valor Alta Estação:"));
        add(valorAltaEstacaoField);
        add(new JLabel("Valor Baixa Estação:"));
        add(valorBaixaEstacaoField);
        add(salvarButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarChale();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                chaleTable.refreshData();
            }
        });
    }

    private void salvarChale() {
        String localizacao = localizacaoField.getText();
        int capacidade = Integer.parseInt(capacidadeField.getText());
        double valorAltaEstacao = Double.parseDouble(valorAltaEstacaoField.getText());
        double valorBaixaEstacao = Double.parseDouble(valorBaixaEstacaoField.getText());

        Chale chale = new Chale(localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao);
        chales.add(chale);

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, localizacao);
                statement.setInt(2, capacidade);
                statement.setDouble(3, valorAltaEstacao);
                statement.setDouble(4, valorBaixaEstacao);
                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Chalé salvo com sucesso no banco de dados!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar chalé no banco de dados: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ChaleTable chaleTable = new ChaleTable();
        ChaleForm form = new ChaleForm(chaleTable);
        form.setVisible(true);
    }
}