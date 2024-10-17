package operacoes;

public class divisao {
    public static double calcular(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Divisão por zero");
        }
        return num1 / num2;
    }
}