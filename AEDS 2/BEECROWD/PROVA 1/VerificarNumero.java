import java.math.BigInteger;
import java.util.Scanner;

public class VerificarNumero {
    public static boolean correspondem(BigInteger A, BigInteger B) {
        String strA = A.toString();
        String strB = B.toString();

        if (strA.length() < strB.length()) {
            return false;
        }

        int indexA = strA.length() - 1;
        int indexB = strB.length() - 1;

        // Percorre os dígitos de A e B a partir da direita
        while (indexA >= 0 && indexB >= 0) {
            if (strA.charAt(indexA) != strB.charAt(indexB)) {
                return false; // Se houver qualquer diferença, B não encaixa em A
            }
            indexA--;
            indexB--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            BigInteger A = sc.nextBigInteger();
            BigInteger B = sc.nextBigInteger();

            if (correspondem(A, B)) {
                System.out.println("encaixa");
            } else {
                System.out.println("nao encaixa");
            }
        }
        sc.close();
    }
}