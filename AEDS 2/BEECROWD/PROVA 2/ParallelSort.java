import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelSort {

    static class SortTask extends RecursiveAction {
        private int[] array;
        private int start, end;

        public SortTask(int[] arry, int start, int end) {
            this.array = arry;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= 1) {
                return;
            }

            int mid = (start + end) / 2;

            SortTask leftTask = new SortTask(array, start, mid);
            SortTask rightTask = new SortTask(array, mid, end);

            invokeAll(leftTask, rightTask);

            merge(start, mid, end);
        }

        private void merge(int start, int mid, int end) {
            int[] left = Arrays.copyOfRange(array, start, mid);
            int[] right = Arrays.copyOfRange(array, mid, end);
            int i = start, j = 0, k = 0;

            while (i < end && j < left.length && k < right.length) {
                if (left[j] <= right[k]) {
                    array[i++] = left[j++];
                } else {
                    array[i++] = right[k++];
                }
            }

            while (k < right.length) {
                array[i++] = right[k++];
            }
        }
    }

    public static void main(String[] args) {

        int[] array = { 5, 2, 9, 1, 5, 6 };
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new SortTask(array, 0, array.length));
        System.out.println("Array ordenado: " + Arrays.toString(array));

        pool.close();
    }
}