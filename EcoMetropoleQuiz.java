import javax.swing.*;

public class EcoMetropoleQuiz {
    private static String[] perguntas = {
        "Qual destes é reciclável?",
        "Qual ação ajuda a reduzir a poluição do ar?",
        "Qual é uma fonte de energia renovável?",
        "O que devemos fazer com pilhas usadas?",
        "Qual atitude ajuda a economizar água?",
        "Qual material demora mais para se decompor?",
        "Qual é o gás responsável pelo efeito estufa?",
        "Qual prática ajuda na compostagem?",
        "Qual é um exemplo de transporte sustentável?",
        "Qual atitude reduz o consumo de plástico?"
    };

    private static String[][] opcoes = {
        {"Papelão", "Restos de comida", "Óleo usado"},
        {"Plantar árvores", "Queimar lixo", "Usar carro"},
        {"Energia solar", "Carvão", "Petróleo"},
        {"Descartar no lixo comum", "Levar ao ponto de coleta", "Enterrar"},
        {"Lavar calçada com mangueira", "Reutilizar água da chuva", "Deixar torneira aberta"},
        {"Papel", "Vidro", "Frutas"},
        {"CO2", "O2", "H2O"},
        {"Misturar lixo orgânico com reciclável", "Usar restos de comida", "Usar plástico"},
        {"Bicicleta", "Carro", "Motocicleta"},
        {"Usar sacolas reutilizáveis", "Comprar garrafas PET", "Usar canudos plásticos"}
    };

    private static int[] respostasCorretas = {0, 0, 0, 1, 1, 1, 0, 1, 0, 0};
    private static int perguntaAtual = 0;
    private static int[] pontuacoes = {0, 0};
    private static String[] nomes = new String[2];
    private static int jogadorAtual = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> iniciarJogo());
    }

    private static void iniciarJogo() {
        nomes[0] = JOptionPane.showInputDialog(null, "Nome do Jogador 1:");
        nomes[1] = JOptionPane.showInputDialog(null, "Nome do Jogador 2:");
        mostrarPergunta();
    }

    private static void mostrarPergunta() {
        if (perguntaAtual >= perguntas.length) {
            mostrarResultadoFinal();
            return;
        }

        String pergunta = "Jogador: " + nomes[jogadorAtual] + "\n\n" + perguntas[perguntaAtual];
        int resposta = JOptionPane.showOptionDialog(null, pergunta, "Pergunta " + (perguntaAtual + 1),
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                opcoes[perguntaAtual], opcoes[perguntaAtual][0]);

        if (resposta == respostasCorretas[perguntaAtual]) {
            pontuacoes[jogadorAtual] += 10;
            JOptionPane.showMessageDialog(null, "Resposta correta! +10 pontos");
        } else {
            pontuacoes[jogadorAtual] -= 5;
            JOptionPane.showMessageDialog(null, "Resposta errada! -5 pontos");
        }

        perguntaAtual++;
        jogadorAtual = (jogadorAtual + 1) % 2;
        mostrarPergunta();
    }

    private static void mostrarResultadoFinal() {
        String resultado = "Pontuação Final:\n" +
                nomes[0] + ": " + pontuacoes[0] + " pontos\n" +
                nomes[1] + ": " + pontuacoes[1] + " pontos";
        JOptionPane.showMessageDialog(null, resultado, "Fim do Jogo", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

