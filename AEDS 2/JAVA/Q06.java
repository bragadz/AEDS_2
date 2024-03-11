import java.util.Scanner;

public class Q06 {

    public static boolean vogais(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        if (!palavra.matches("[aeiouAEIOU]+")) {
            return false;
        }

        return true;
    }

    public static boolean consoantes(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        if (!palavra.matches("[^aeiouAEIOU]+")) {
            return false;
        }

        return true;
    }

    public static boolean numeroInteiro(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        if (!palavra.matches("[1234567890]+")) {
            return false;
        }

        return true;
    }

    public static boolean numeroReal(String palavra) {
        if (palavra.isEmpty()) {
            return false;
        }

        if (!palavra.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)")) {
            return false;
        }

        return true;
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