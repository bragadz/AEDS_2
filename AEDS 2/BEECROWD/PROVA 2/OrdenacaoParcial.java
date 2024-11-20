import java.util.Arrays;
import java.util.Scanner;

public class OrdenacaoParcial {

    public static int[] encontrarMenores(int[] array, int k) {
        int[] menores = new int[k];

        for (int i = 0; i < k; i++) {
            menores[i] = array[i];
        }

        Arrays.sort(menores);

        for (int i = k; i < array.length; i++) {
            if (array[i] < menores[k - 1]) {
                menores[k - 1] = array[i];
                Arrays.sort(menores);
            }
        }
        return menores;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Coleta o tamanho do array e os elementos do usuário
        System.out.print("Digite o tamanho do array: ");
        int tamanho = sc.nextInt();
        int[] array = new int[tamanho];

        System.out.println("Digite os elementos do array:");
        for (int i = 0; i < tamanho; i++) {
            array[i] = sc.nextInt();
        }

        // Coleta o valor de 'k' do usuário
        System.out.print("Digite o valor de k (quantos menores elementos você quer): ");
        int k = sc.nextInt();

        // Encontra os 'k' menores elementos
        int[] menores = encontrarMenores(array, k);

        // Exibe os 'k' menores elementos
        System.out.println("Os " + k + " menores elementos são: " + Arrays.toString(menores));

        sc.close();
    }
}
