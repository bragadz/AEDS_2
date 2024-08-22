import java.util.Scanner;

public class SobreNome {

    public static boolean eHconsoantes(String palavra) {
        String consoantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";

        int contador = 0;

        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);
            if (consoantes.indexOf(letra) != -1) {
                contador++;
                if (contador >= 3) {
                    return false;
                }
            } else {
                contador = 0;
            }
        }
        return true;
    }

    public static void imprimirResultado(boolean resultado, String palavra) {
        System.out.print(resultado ? palavra + " eh facil\n" : palavra + " nao eh facil\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            String palavra = sc.nextLine();
            imprimirResultado(eHconsoantes(palavra), palavra);
        }
        sc.close();
    }
}