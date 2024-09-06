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

public class ClienteForm extends JFrame {
    private JTextField nomeField;
    private JTextField rgField;
    private JTextField enderecoField;
    private JTextField bairroField;
    private JTextField cidadeField;
    private JTextField estadoField;
    private JTextField cepField;
    private JTextField nascimentoField;
    private JButton salvarButton;

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteForm(ClienteTable clienteTable) {
        setTitle("Cadastro de Cliente");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nomeField = new JTextField(20);
        rgField = new JTextField(20);
        enderecoField = new JTextField(20);
        bairroField = new JTextField(20);
        cidadeField = new JTextField(20);
        estadoField = new JTextField(20);
        cepField = new JTextField(20);
        nascimentoField = new JTextField(20);
        salvarButton = new JButton("Salvar");

        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("RG:"));
        add(rgField);
        add(new JLabel("Endereço:"));
        add(enderecoField);
        add(new JLabel("Bairro:"));
        add(bairroField);
        add(new JLabel("Cidade:"));
        add(cidadeField);
        add(new JLabel("Estado:"));
        add(estadoField);
        add(new JLabel("CEP:"));
        add(cepField);
        add(new JLabel("Nascimento:"));
        add(nascimentoField);
        add(salvarButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                clienteTable.refreshData();
            }
        });
    }

    private void salvarCliente() {
        String nome = nomeField.getText();
        String rg = rgField.getText();
        String endereco = enderecoField.getText();
        String bairro = bairroField.getText();
        String cidade = cidadeField.getText();
        String estado = estadoField.getText();
        String cep = cepField.getText();
        String nascimento = nascimentoField.getText();

        // Validate date format
        if (!nascimento.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(null, "Data de nascimento deve estar no formato yyyy-MM-dd");
            return;
        }

        Cliente cliente = new Cliente(nome, rg, endereco, bairro, cidade, estado, cep, nascimento);
        clientes.add(cliente);

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Cliente (nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, CEPCliente, nascimentoCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, rg);
                statement.setString(3, endereco);
                statement.setString(4, bairro);
                statement.setString(5, cidade);
                statement.setString(6, estado);
                statement.setString(7, cep);
                statement.setDate(8, java.sql.Date.valueOf(nascimento));
                statement.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso no banco de dados!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente no banco de dados: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ClienteTable clienteTable = new ClienteTable();
        ClienteForm form = new ClienteForm(clienteTable);
        form.setVisible(true);
    }
}