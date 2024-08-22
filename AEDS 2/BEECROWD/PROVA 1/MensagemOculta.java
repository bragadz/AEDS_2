import java.util.Scanner;

public class MensagemOculta {

    public static String extrairMensagemOculta(String palavra) {
        StringBuilder msg = new StringBuilder();

        // Remover espaços extras e dividir o texto em palavras
        String[] palavras = palavra.trim().split("\\s+");

        // Iterar sobre as palavras para extrair a primeira letra de cada uma
        for (String texto : palavras) {
            msg.append(texto.charAt(0));
        }
        return msg.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            String palavra = sc.nextLine();
            String mensagemOculta = extrairMensagemOculta(palavra);
            System.out.println(mensagemOculta);

        }
        sc.close();
    }
}