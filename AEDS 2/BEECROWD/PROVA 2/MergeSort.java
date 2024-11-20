public class MergeSort {

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // Se o array tiver 1 ou 0 elementos, já está ordenado
        }

        int mid = array.length / 2;
        int[] esquerda = new int[mid];
        int[] direita = new int[array.length - mid];

        for (int i = 0; i < mid; i++) {
            esquerda[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            direita[i - mid] = array[i];
        }

        mergeSort(esquerda);
        mergeSort(direita);

        merge(array, esquerda, direita);
    }

    private static void merge(int[] array, int[] esquerda, int[] direita) {
        int i = 0;
        int j = 0;
        int aux = 0;

        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] <= direita[j]) {
                array[aux++] = esquerda[i++];
            } else {
                array[aux++] = direita[j++];
            }
        }
        // Copia os elementos restantes, se tiver
        while (i < esquerda.length) {
            array[aux++] = esquerda[i++];
        }
        while (j < direita.length) {
            array[aux++] = direita[j++];
        }
    }

    public static void main(String[] args) {

        int[] array = { 38, 27, 43, 3, 9, 82, 10 };

        mergeSort(array);
        System.out.println("Array ordenado: ");
        for (int num : array) {
            System.out.print(num + " ");
        }

    }
}