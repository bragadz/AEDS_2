import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {






    public static void main(String[] args) {
        
        Queue<Pacote> filaDePacotes = new ArrayDeque<>();
        int numeroDePacotes = 10;
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < numeroDePacotes; i++){
            System.out.print("Qual a numeracao do pacote de numero " + i + ": ");
            int numeracaoRecebida = sc.nextInt();
            Pacote pacote = new Pacote(numeracaoRecebida);


            if(pacote.getNumeracao() > 0 && pacote.getNumeracao() < 4){
                filaDePacotes.add(pacote);
            }
        }


        Stack<Pacote> pilha1 = new Stack<>();
        Stack<Pacote> pilha2 = new Stack<>();
        Stack<Pacote> pilha3 = new Stack<>();



        while (!filaDePacotes.isEmpty()) {
            Pacote pacote = filaDePacotes.poll();

            if(pacote.getNumeracao() == 1){
                pilha1.push(pacote);
            }

            if(pacote.getNumeracao() == 2){
                pilha2.push(pacote);
            }

            if(pacote.getNumeracao() == 3){
                pilha3.push(pacote);
            }

        }

        System.out.println("Pilha 1");
        Impressora.imprimirPilha(pilha1);

        System.out.println("Pilha 2");
        Impressora.imprimirPilha(pilha2);

        System.out.println("Pilha 3");
        Impressora.imprimirPilha(pilha3);

        sc.close();
    }
}
