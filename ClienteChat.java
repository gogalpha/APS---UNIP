
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClienteChat {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter saida;
    private JFrame frame = new JFrame("Chat Ambiental");
    private JTextArea areaTexto = new JTextArea(15, 50);
    private JTextField campoTexto = new JTextField(50);

    public ClienteChat() {
        campoTexto.setEditable(false);
        areaTexto.setEditable(false);
        frame.getContentPane().add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        frame.getContentPane().add(campoTexto, BorderLayout.SOUTH);
        frame.pack();

        campoTexto.addActionListener(e -> {
            saida.println(campoTexto.getText());
            campoTexto.setText("");
        });
    }

    private String obterEnderecoServidor() {
        return JOptionPane.showInputDialog(frame, "Digite o endereço IP do servidor:", "Conectar ao Servidor", JOptionPane.QUESTION_MESSAGE);
    }

    private void iniciar() throws IOException {
        String enderecoServidor = obterEnderecoServidor();
        socket = new Socket(enderecoServidor, 12345);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        saida = new PrintWriter(socket.getOutputStream(), true);

        campoTexto.setEditable(true);

        new Thread(() -> {
            try {
                String mensagem;
                while ((mensagem = entrada.readLine()) != null) {
                    areaTexto.append(mensagem + "\n");
                }
            } catch (IOException e) {
                areaTexto.append("Conexão encerrada.\n");
            }
        }).start();
    }

    public static void main(String[] args) throws Exception {
        ClienteChat cliente = new ClienteChat();
        cliente.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cliente.frame.setVisible(true);
        cliente.iniciar();
    }
}
