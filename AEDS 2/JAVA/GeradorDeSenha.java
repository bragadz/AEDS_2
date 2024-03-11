import java.util.Random;
import java.util.Scanner;

public class GeradorDeSenha {

    public static String gerador(String palavra) {
        Random gerador = new Random();
        gerador.setSeed(4);

        char primeiraLetra = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        char segundaLetra = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

        String resultado = palavra.replace(primeiraLetra, segundaLetra);

        return resultado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String palavra = sc.nextLine();

        while (!palavra.equals("FIM")) {
            System.out.println(gerador(palavra));
            palavra = sc.nextLine();
        }

        sc.close();
    }
}