import java.util.Scanner;

public class Q15 {

    public static boolean vogais(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        char primeiraLetra = palavra.charAt(0);

        if (!Character.toString(primeiraLetra).matches("[aeiouAEIOU+]")) {
            return false;
        }

        return vogais(palavra.substring(1));
    }

    public static boolean consoantes(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        char primeiraLetra = palavra.charAt(0);

        if (!Character.toString(primeiraLetra).matches("[^aeiouAEIOU]+")) {
            return false;
        }

        return consoantes(palavra.substring(1));
    }

    public static boolean numeroInteiro(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        char primeiraLetra = palavra.charAt(0);

        if (!Character.toString(primeiraLetra).matches("[1234567890]+")) {
            return false;
        }

        return numeroInteiro(palavra.substring(1));
    }

    public static boolean numeroReal(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        char primeiraLetra = palavra.charAt(0);

        if (!Character.toString(primeiraLetra).matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)")) {
            return false;
        }

        return numeroReal(palavra.substring(1));
    }

    public static void imprimirResultado(boolean resultado) {
        System.out.print(resultado ? "SIM " : "NAO ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String palavra = sc.nextLine();


        while (!palavra.equals("FIM")) {
            int count = 0;

            imprimirResultado(vogais(palavra));
            imprimirResultado(consoantes(palavra));
            imprimirResultado(numeroInteiro(palavra));
            imprimirResultado(numeroReal(palavra));

            count++;
            if(count == 1){
                System.out.println();
            }

            palavra = sc.nextLine();
        }

        sc.close();
    }
}