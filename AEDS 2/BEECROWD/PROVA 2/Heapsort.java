public class Heapsort {

    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int maior = i; // Inicializa o maior como a raiz
        int esquerda = 2 * i + 1; // Filho esquerdo
        int direita = 2 * i + 2; // Filho direito

        // Se o filho da esquerda é maior que a raiz
        if (esquerda < n && array[esquerda] > array[maior]) {
            maior = esquerda;
        }

        // Se o filho da direita é maior que o maior atual
        if (direita < n && array[direita] > array[maior]) {
            maior = direita;
        }

        // Se o maior não é a raiz, troca e continua reorganizando
        if (maior != i) {
            swap(array, i, maior);
            heapify(array, n, maior);
        }
    }

    // Função para trocar dois elementos de posição
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {

        int[] array = { 4, 10, 3, 5, 1 };
        System.out.println("Array antes da ordenação:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        // Chama a função heapSort
        heapSort(array);

        System.out.println("\nArray depois da ordenação:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}