import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    private JButton clienteButton;
    private JButton chaleButton;
    private JButton hospedagemButton;

    public MainApp() {
        setTitle("Main Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        clienteButton = new JButton("Abrir Cliente");
        chaleButton = new JButton("Abrir Chale");
        hospedagemButton = new JButton("Abrir Hospedagem");

        add(clienteButton);
        add(chaleButton);
        add(hospedagemButton);

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteTable clienteTable = new ClienteTable();
                clienteTable.setVisible(true);
            }
        });

        chaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChaleTable chaleTable = new ChaleTable();
                chaleTable.setVisible(true);
            }
        });

        hospedagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HospedagemTable hospedagemTable = new HospedagemTable();
                hospedagemTable.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.setVisible(true);
    }
}