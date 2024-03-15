import java.io.RandomAccessFile;
import java.util.Scanner;

public class Arquivo {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        RandomAccessFile escrita = new RandomAccessFile("arquivo.txt", "rw");

        int n = sc.nextInt();
        double num;

        for (int i = 0; i < n; i++) {
            num = sc.nextDouble();
            escrita.writeDouble(num);
        }

        escrita.close();

        RandomAccessFile leitura = new RandomAccessFile("arquivo.txt", "r");

        long tamanho = leitura.length();

        while (tamanho > 0) {
            tamanho -= 8;
            leitura.seek(tamanho);
            num = leitura.readDouble();
            if (num % 1 == 0) {
                System.out.println((int) num);
            } else {
                System.out.println(num);
            }
        }
        sc.close();
        leitura.close();
    }
}