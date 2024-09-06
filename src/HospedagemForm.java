import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HospedagemForm extends JFrame {
    private JTextField codChaleField;
    private JTextField estadoField;
    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JTextField qtdePessoasField;
    private JTextField valorFinalField;
    private JTextField codClienteField;
    private JTextField descontoField; // Novo campo
    private JButton salvarButton;
    private List<Hospedagem> hospedagens;

    public HospedagemForm(HospedagemTable hospedagemTable) {
        setTitle("Cadastro de Hospedagem");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        codChaleField = new JTextField(20);
        estadoField = new JTextField(20);
        dataInicioField = new JTextField(20);
        dataFimField = new JTextField(20);
        qtdePessoasField = new JTextField(20);
        valorFinalField = new JTextField(20);
        codClienteField = new JTextField(20);
        descontoField = new JTextField(20); // Novo campo
        salvarButton = new JButton("Salvar");
        hospedagens = new ArrayList<>();

        add(new JLabel("Código do Chalé:"));
        add(codChaleField);
        add(new JLabel("Estado:"));
        add(estadoField);
        add(new JLabel("Data de Início:"));
        add(dataInicioField);
        add(new JLabel("Data de Fim:"));
        add(dataFimField);
        add(new JLabel("Quantidade de Pessoas:"));
        add(qtdePessoasField);
        add(new JLabel("Valor Final:"));
        add(valorFinalField);
        add(new JLabel("Código do Cliente:"));
        add(codClienteField);
        add(new JLabel("Desconto:")); // Novo campo
        add(descontoField);
        add(salvarButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarHospedagem();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                hospedagemTable.refreshData();
            }
        });
    }

    private void salvarHospedagem() {
        int codChale = Integer.parseInt(codChaleField.getText());
        String estado = estadoField.getText();
        String dataInicio = dataInicioField.getText();
        String dataFim = dataFimField.getText();
        int qtdePessoas = Integer.parseInt(qtdePessoasField.getText());
        double valorFinal = Double.parseDouble(valorFinalField.getText());
        int codCliente = Integer.parseInt(codClienteField.getText());
        double desconto = Double.parseDouble(descontoField.getText()); // Novo campo

        Hospedagem hospedagem = new Hospedagem(codChale, estado, dataInicio, dataFim, qtdePessoas, valorFinal, codCliente, desconto);
        hospedagens.add(hospedagem);

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Hospedagem (codChale, estado, dataInicio, dataFim, qtdPessoas, valorFinal, codCliente, desconto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, codChale);
                statement.setString(2, estado);
                statement.setDate(3, java.sql.Date.valueOf(dataInicio));
                statement.setDate(4, java.sql.Date.valueOf(dataFim));
                statement.setInt(5, qtdePessoas);
                statement.setDouble(6, valorFinal);
                statement.setInt(7, codCliente);
                statement.setDouble(8, desconto); // Novo campo
                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Hospedagem salva com sucesso no banco de dados!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar hospedagem no banco de dados: " + ex.getMessage());
        }
    }
}