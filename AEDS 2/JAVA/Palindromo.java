import java.util.Scanner;

public class Palindromo {

    public static boolean ehPalindromo(String palavra) {
        int tamanho = palavra.length();
        for (int i = 0; i < tamanho; i++) {
            if (palavra.charAt(i) != palavra.charAt(tamanho - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String palavra = sc.nextLine();

        while (!palavra.equals("FIM")) {

            ehPalindromo(palavra);

            if (ehPalindromo(palavra) == true) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            palavra = sc.nextLine();
        }

        sc.close();
    }
}