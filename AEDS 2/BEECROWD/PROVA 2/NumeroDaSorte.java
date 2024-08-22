import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumeroDaSorte {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();
        String entrada = sc.nextLine();
        String[] split = entrada.split(" ");

        if (N != split.length) {
            System.out.println("Entrada invalida");
        }

        List<Integer> lista = new ArrayList<>();
        for (String s : split) {
            lista.add(Integer.parseInt(s));
        }

        int LuckyNumber = sc.nextInt();
        boolean isLuckyNumber = false;

        int k = 2;

        while (lista.contains(LuckyNumber)) {

            for (int i = k - 1; i < lista.size(); i += k - 1) {
                lista.remove(i);
            }

            if (lista.contains(LuckyNumber) && lista.indexOf(LuckyNumber) < k - 1) {
                isLuckyNumber = true;
                break;
            }

            k++;
        }

        if (isLuckyNumber) {
            System.out.println("Y");
        } else {
            System.out.println("N");
        }

        sc.close();
    }
}