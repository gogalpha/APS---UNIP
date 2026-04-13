import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor iniciado na porta 12345...");

            List<PrintWriter> clientes = new ArrayList<>();

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Novo cliente conectado: " + cliente.getInetAddress());

                PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
                clientes.add(saida);

                new Thread(() -> {
                    try {
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                        String mensagem;
                        while ((mensagem = entrada.readLine()) != null) {
                            System.out.println("Recebido: " + mensagem);
                            for (PrintWriter c : clientes) {
                                c.println(mensagem);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Erro na comunicação com cliente.");
                    }
                }).start();
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}
