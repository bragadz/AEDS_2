import java.util.Scanner;

public class NumeroApaixonados {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int N = sc.nextInt();
            if (N == 0) break; // Termina o loop se N for 0

            long[] numbers = new long[N];

            // Lê os números
            for (int i = 0; i < N; i++) {
                numbers[i] = sc.nextLong();
            }

            // Ordena os números utilizando o Bubblesort
            bubbleSort(numbers);

            // Encontra os números apaixonados
            long num1 = -1, num2 = -1;
            for (int i = 0; i < N - 1; i++) {
                if (numbers[i] == numbers[i + 1]) {
                    // Número repetido, avança duas posições
                    i++;
                } else {
                    // Número apaixonado encontrado
                    if (num1 == -1) {
                        num1 = numbers[i];
                    } else {
                        num2 = numbers[i];
                        break;
                    }
                }
            }

            // Se num2 ainda não foi definido, o último número é o segundo número apaixonado
            if (num2 == -1) {
                num2 = numbers[N - 1];
            }

            // Imprime os números apaixonados em ordem crescente
            System.out.println(Math.min(num1, num2) + " " + Math.max(num1, num2));
        }

        sc.close();
    }

    // Implementação do Bubblesort
    public static void bubbleSort(long[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Troca os elementos
                    long temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
