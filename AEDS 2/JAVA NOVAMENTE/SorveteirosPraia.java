import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SorveteirosPraia {

    // Classe auxiliar para representar os intervalos dos sorveteiros
    static class Intervalo implements Comparable<Intervalo> {
        int inicio, fim;

        Intervalo(int inicio, int fim) {
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        public int compareTo(Intervalo outro) {
            if (this.inicio != outro.inicio) {
                return Integer.compare(this.inicio, outro.inicio);
            } else {
                return Integer.compare(this.fim, outro.fim);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int teste = 1;

        while (true) {
            // Leitura de P (comprimento da praia) e S (número de sorveteiros)
            int P = scanner.nextInt();
            int S = scanner.nextInt();

            if (P == 0 && S == 0) {
                break; // Termina a leitura quando P = 0 e S = 0
            }

            List<Intervalo> intervalos = new ArrayList<>();

            // Leitura dos intervalos dos sorveteiros
            for (int i = 0; i < S; i++) {
                int U = scanner.nextInt();
                int V = scanner.nextInt();
                intervalos.add(new Intervalo(U, V));
            }

            // Ordenar os intervalos pelo ponto inicial
            Collections.sort(intervalos);

            // Unir intervalos sobrepostos
            List<Intervalo> resultado = new ArrayList<>();
            Intervalo atual = intervalos.get(0);

            for (int i = 1; i < intervalos.size(); i++) {
                Intervalo proximo = intervalos.get(i);

                // Verifica se os intervalos se sobrepõem
                if (proximo.inicio <= atual.fim) {
                    // Estende o intervalo atual
                    atual.fim = Math.max(atual.fim, proximo.fim);
                } else {
                    // Adiciona o intervalo atual à lista de resultados
                    resultado.add(atual);
                    atual = proximo;
                }
            }

            // Adicionar o último intervalo restante
            resultado.add(atual);

            // Imprimir o resultado
            System.out.println("Teste " + teste);
            for (Intervalo intervalo : resultado) {
                System.out.println(intervalo.inicio + " " + intervalo.fim);
            }
            System.out.println(); // Linha em branco ao final do teste

            teste++;
        }

        scanner.close();
    }
}
