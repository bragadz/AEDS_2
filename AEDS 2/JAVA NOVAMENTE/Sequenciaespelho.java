import java.util.Scanner;

public class Sequenciaespelho {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            // System.out.println("Digite o numero inicial: ");
            int inicio = sc.nextInt();
            // System.out.println("Digite o numero final: ");
            int fim = sc.nextInt();

            StringBuilder sequenciaCrescente = new StringBuilder();
            for (int i = inicio; i < fim + 1; i++) {
                sequenciaCrescente.append(i);
            }

            StringBuilder sequenciaEspelho = new StringBuilder(sequenciaCrescente).reverse();

            String resultadoFinal = sequenciaCrescente.toString() + sequenciaEspelho.toString();

            System.out.println(resultadoFinal);

        }

        sc.close();

    }
}