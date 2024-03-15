import java.util.Scanner;

public class CiframentoRec {

    public static String cifraRec(String palavra) {
        return cifrar(palavra, 0, new StringBuilder());
    }

    public static String cifrar(String palavra, int index, StringBuilder resultado) {

        if (index == palavra.length()) {
            return resultado.toString();
        }

        char letra = palavra.charAt(index);

        if (Character.isLetter(letra)) {
            char base = (Character.isUpperCase(letra)) ? 'A' : 'a';
            letra = (char) (((letra - base + 3) % 127) + base);
            resultado.append(letra);
        } else if (letra == 65533) {
            resultado.append((char) letra);
        } else {
            resultado.append((char) (letra + 3));
        }
        return cifrar(palavra, index + 1, resultado);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String palavra = sc.nextLine();

        while (!palavra.equals("FIM")) {
            System.out.println(cifraRec(palavra));
            palavra = sc.nextLine();
        }
        sc.close();
    }
}