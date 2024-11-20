import java.util.Scanner;

public class maratona {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] bolas = new int[N];
        for(int i = 0; i < N; i++){
            bolas[i] = sc.nextInt();
        }

        // Simulação da construção do triângulo
        while (N > 1) {
            for (int i = 0; i < N - 1; i++) {
                if (bolas[i] == bolas[i + 1]) {
                    bolas[i] = 1;  // Mesma cor -> Preta
                } else {
                    bolas[i] = -1; // Cores diferentes -> Branca
                }
            }
            N--; // Reduz o número de bolas da próxima fileira
        }

        if (bolas[0] == 1) {
            System.out.println("preta");
        } else {
            System.out.println("branca");
        }
        sc.close();
    }
}