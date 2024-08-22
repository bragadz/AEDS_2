import java.util.Scanner;

public class palavraDancante {

    public static String maisculaOuNao(String palavra) {
        StringBuilder resultado = new StringBuilder();

        boolean proxMaiuscula = true;

        for (char caractere : palavra.toCharArray()) {
            if (Character.isLetter(caractere)) {
                if (proxMaiuscula) {
                    resultado.append(Character.toUpperCase(caractere));
                } else {
                    resultado.append(Character.toLowerCase(caractere));
                }
                proxMaiuscula = !proxMaiuscula;
            } else {
                resultado.append(caractere);
            }
        }
        return resultado.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            String palavra = sc.nextLine();
            System.out.println(maisculaOuNao(palavra));
        }
        sc.close();
    }
}