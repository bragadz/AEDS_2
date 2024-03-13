import java.util.Scanner;

public class PalindromoRec {

    public static boolean ehPalindromo(String palavra, int inicio, int fim) {
        if (inicio >= fim) {
            return true;
        }

        if (palavra.charAt(inicio) != palavra.charAt(fim)) {
            return false;
        }

        return ehPalindromo(palavra, inicio + 1, fim - 1);
    }

    public static boolean ehPalindromoRec(String palavra) {
        return ehPalindromo(palavra, 0, palavra.length() - 1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String palavra = sc.nextLine();

        while (!palavra.equals("FIM")) {

            ehPalindromoRec(palavra);

            if (ehPalindromoRec(palavra) == true) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            palavra = sc.nextLine();
        }

        sc.close();
    }
}