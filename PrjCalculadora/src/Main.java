import operacoes.soma;
import operacoes.subtracao;
import operacoes.multiplicacao;
import operacoes.divisao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static StringBuilder primeiroNumero = new StringBuilder();
    private static StringBuilder segundoNumero = new StringBuilder();
    private static String operador = "";
    private static boolean operadorSelecionado = false;

    public static void main(String[] args) {
        // Criar a janela da calculadora
        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Criar o campo de resultado (visor)
        JTextField campoResultado = new JTextField();
        campoResultado.setEditable(false);
        campoResultado.setFont(new Font("Arial", Font.BOLD, 32));
        campoResultado.setHorizontalAlignment(JTextField.RIGHT);
        campoResultado.setPreferredSize(new Dimension(400, 80));
        frame.add(campoResultado, BorderLayout.NORTH);

        // Criar um painel para os botões de "Resultado" e "Limpar"
        JPanel painelResultadoLimpar = new JPanel();
        painelResultadoLimpar.setLayout(new GridLayout(1, 2, 10, 10)); // 1 linha, 2 colunas
        frame.add(painelResultadoLimpar, BorderLayout.CENTER);

        // Criar um painel para os botões numéricos e operadores
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 4, 5, 5)); // 4 linhas, 4 colunas
        frame.add(painelBotoes, BorderLayout.SOUTH);

        // Definir a altura padrão para os botões, com largura maior para os botões "Resultado" e "Limpar"
        Dimension buttonSize = new Dimension(100, 100);

        // Botões de "Resultado" e "Limpar"
        JButton botaoResultado = new JButton("Resultado");
        botaoResultado.setFont(new Font("Arial", Font.BOLD, 18));
        botaoResultado.setPreferredSize(new Dimension(200, buttonSize.height));
        botaoResultado.setBackground(Color.GREEN);
        botaoResultado.setForeground(Color.BLACK);
        botaoResultado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(primeiroNumero.toString());
                    double num2 = Double.parseDouble(segundoNumero.toString());
                    double resultado = 0;

                    switch (operador) {
                        case "+":
                            resultado = soma.calcular(num1, num2);
                            break;
                        case "-":
                            resultado = subtracao.calcular(num1, num2);
                            break;
                        case "*":
                            resultado = multiplicacao.calcular(num1, num2);
                            break;
                        case "/":
                            resultado = divisao.calcular(num1, num2);
                            break;
                    }
                    campoResultado.setText(String.valueOf(resultado));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira números válidos.");
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: Impossível dividir por zero.");
                    campoResultado.setText("0");
                }
            }
        });

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 18));
        botaoLimpar.setPreferredSize(new Dimension(200, buttonSize.height));
        botaoLimpar.setBackground(Color.RED);
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos(campoResultado);
            }
        });

        // Adicionar botões "Resultado" e "Limpar" ao painel
        painelResultadoLimpar.add(botaoResultado);
        painelResultadoLimpar.add(botaoLimpar);

        // Criar os botões de números
        JButton[] botoesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botoesNumeros[i] = new JButton(String.valueOf(i));
            botoesNumeros[i].setFont(new Font("Arial", Font.BOLD, 22));
            botoesNumeros[i].setBackground(Color.DARK_GRAY);
            botoesNumeros[i].setForeground(Color.WHITE);
            botoesNumeros[i].setPreferredSize(buttonSize);
            int numero = i;
            botoesNumeros[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!operadorSelecionado) {
                        primeiroNumero.append(numero);
                        campoResultado.setText(primeiroNumero.toString());
                    } else {
                        segundoNumero.append(numero);
                        campoResultado.setText(segundoNumero.toString());
                    }
                }
            });
        }

        // Criar os botões de operações
        JButton botaoSoma = new JButton("+");
        JButton botaoSubtrair = new JButton("-");
        JButton botaoMultiplicar = new JButton("*");
        JButton botaoDividir = new JButton("/");

        // Enfeitar botões de operadores
        JButton[] operadores = {botaoSoma, botaoSubtrair, botaoMultiplicar, botaoDividir};
        for (JButton operador : operadores) {
            operador.setFont(new Font("Arial", Font.BOLD, 22));
            operador.setBackground(Color.ORANGE);
            operador.setForeground(Color.BLACK);
            operador.setPreferredSize(buttonSize); // Definir altura para os operadores
            operador.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setOperador(operador.getText(), campoResultado);
                }
            });
        }

        // Adicionar os botões no painel de forma organizada
        painelBotoes.add(botoesNumeros[7]);
        painelBotoes.add(botoesNumeros[8]);
        painelBotoes.add(botoesNumeros[9]);
        painelBotoes.add(botaoSoma);

        painelBotoes.add(botoesNumeros[4]);
        painelBotoes.add(botoesNumeros[5]);
        painelBotoes.add(botoesNumeros[6]);
        painelBotoes.add(botaoSubtrair);

        painelBotoes.add(botoesNumeros[1]);
        painelBotoes.add(botoesNumeros[2]);
        painelBotoes.add(botoesNumeros[3]);
        painelBotoes.add(botaoMultiplicar);

        painelBotoes.add(new JLabel(""));
        painelBotoes.add(botoesNumeros[0]);
        painelBotoes.add(new JLabel(""));
        painelBotoes.add(botaoDividir);

        // Exibir a janela
        frame.setVisible(true);
    }

    // Método para definir o operador e preparar para o segundo número
    private static void setOperador(String op, JTextField campoResultado) {
        operadorSelecionado = true;
        operador = op;
        campoResultado.setText(""); // Limpar o campo para a segunda entrada
    }

    // Método para limpar os campos e reiniciar a calculadora
    private static void limparCampos(JTextField campoResultado) {
        primeiroNumero.setLength(0);
        segundoNumero.setLength(0);
        operador = "";
        operadorSelecionado = false;
        campoResultado.setText("");
    }
}